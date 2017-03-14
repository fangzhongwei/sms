package com.lawsofnatrue.sms.service

import java.sql.Timestamp
import javax.inject.Inject

import com.jxjxgo.common.exception.{ErrorCode, ServiceException}
import com.jxjxgo.common.kafka.template.ProducerTemplate
import com.jxjxgo.memberber.rpc.domain.{MemberBaseResponse, MemberEndpoint, MemberResponse}
import com.jxjxgo.sms.domain.mq.sms.SmsMessage
import com.jxjxgo.sms.rpc.domain.{SendLoginVerificationCodeRequest, VerifyLoginVerificationCodeRequest}
import com.lawsofnatrue.domain.SmsVerifyTemplate
import com.lawsofnatrue.sms.repo.SmsRepository
import com.twitter.util.{Await, Future}
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Promise

/**
  * Created by fangzhongwei on 2017/1/3.
  */
trait SmsService {
  def getTemplate(smsType: Int): SmsVerifyTemplate

  def sendLoginVerificationCode(traceId: String, sendLoginVerificationCodeRequest: SendLoginVerificationCodeRequest): Int

  def verifyLoginVerificationCode(traceId: String, verifyLoginVerificationCodeRequest: VerifyLoginVerificationCodeRequest): Boolean
}

class SmsServiceImpl @Inject()(producerTemplate: ProducerTemplate, smsRepository: SmsRepository, memberClientService: MemberEndpoint[Future]) extends SmsService {
  private[this] val logger: Logger = LoggerFactory.getLogger(getClass)
  private[this] val templateMap = Map[Int, SmsVerifyTemplate]()

  override def sendLoginVerificationCode(traceId: String, r: SendLoginVerificationCodeRequest): Int = {
    val smsTemplate: SmsVerifyTemplate = getTemplate(r.smsType)
    produceSmsMessage(SmsMessage(traceId, r.ip, r.deviceType, r.fingerPrint, getMemberIdByMobile(traceId, r.mobileTicket, r.deviceType), r.smsType, r.mobileTicket, smsTemplate.channel, r.resend, r.lastChannel, ""))
    smsTemplate.channel
  }

  def getMemberIdByMobile(traceId: String, mobileTicket: String, deviceType: Int): Long = {
    val memberResponse: MemberResponse = Await.result(memberClientService.getMemberByMobile(traceId, mobileTicket))
    memberResponse.code match {
      case "0" =>
        if (memberResponse.status == -1) throw ServiceException.make(ErrorCode.EC_UC_MEMBER_ACCOUNT_FREEZE)
        memberResponse.memberId
      case "EC_UC_MEMBER_NOT_EXISTS" => //ErrorCode.EC_UC_MEMBER_NOT_EXISTS
        val response: MemberBaseResponse = Await.result(memberClientService.register(traceId, mobileTicket, deviceType))
        response.code match {
          case "0" =>
            memberResponse.memberId
          case _ =>
            throw ServiceException.make(ErrorCode.get(response.code))
        }
      case _ =>
        throw ServiceException.make(ErrorCode.get(memberResponse.code))
    }
  }

  override def getTemplate(smsType: Int): SmsVerifyTemplate = {
    var smsTemplate: SmsVerifyTemplate = null
    templateMap.get(smsType) match {
      case Some(template) => smsTemplate = template
      case None => smsRepository.selectTemplate(smsType) match {
        case Some(template) => smsTemplate = template
        case None => throw ServiceException.make(ErrorCode.EC_SMS_TYPE_CONFIG_ERROR)
      }
    }
    smsTemplate
  }

  def produceSmsMessage(smsMessage: SmsMessage): scala.concurrent.Future[Unit] = {
    val promise: Promise[Unit] = Promise[Unit]
    scala.concurrent.Future {
      val config: Config = ConfigFactory.load()
      logger.info(s"send sms message:$smsMessage")
      producerTemplate.send(config.getString("kafka.topic.sms.login.code"), smsMessage.toByteArray)
      promise.success()
    }
    promise.future
  }

  override def verifyLoginVerificationCode(traceId: String, r: VerifyLoginVerificationCodeRequest): Boolean = {
    val memberResponse: MemberResponse = Await.result(memberClientService.getMemberByMobile(traceId, r.mobileTicket))
    memberResponse.code match {
      case "0" => smsRepository.selectLastVerifyRecord(memberResponse.memberId, r.smsType.toByte) match {
        case Some(record) =>
          record.status match {
            case 0 => record.expireTime.before(new Timestamp(System.currentTimeMillis())) match {
              case true =>
                smsRepository.updateRecordStatus(record.id, 3)
                throw ServiceException.make(ErrorCode.EC_SMS_CODE_EXPIRED_ERROR)
              case false => record.verifyTimes >= record.maxTryTimes match {
                case true => smsRepository.updateRecordStatus(record.id, 2)
                  throw ServiceException.make(ErrorCode.EC_SMS_CODE_EXPIRED_ERROR)
                case false =>
                  record.ip == r.ip && record.deviceType == r.deviceType && record.fingerPrint == r.fingerPrint match {
                    case true =>
                      record.verifyCode.equals(r.verifycationCode) match {
                        case true => smsRepository.updateRecordStatus(record.id, 99)
                          true
                        case false => smsRepository.increateTryTimes(record.id)
                          false
                      }
                    case false =>
                      logger.error(s"invalid ip or device.db:$record, request:$r")
                      false
                  }
              }
            }
            case _ => throw ServiceException.make(ErrorCode.EC_SMS_WRONG_CODE)
          }
        case None => false
      }
      case _ => throw ServiceException.make(ErrorCode.get(memberResponse.code))
    }
  }
}
