package com.lawsofnatrue.sms.service

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

import com.lawsofnatrue.domain.{SmsVerifyAggregation, SmsVerifyRecord, SmsVerifyTemplate}
import com.lawsofnatrue.sms.repo.SmsRepository
import com.jxjxgo.common.exception.{ErrorCode, ServiceException}
import com.jxjxgo.common.helper.VerifyCodeHelper
import com.jxjxgo.common.service.ConsumeService
import com.jxjxgo.sms.domain.mq.sms.SmsMessage
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

/**
  * Created by fangzhongwei on 2017/1/4.
  */
trait SmsSenderService extends ConsumeService {
  def sendSms(traceId: String, smsMessage: SmsMessage): Unit
}

class SmsSenderServiceImpl @Inject()(smsRepository: SmsRepository, smsService: SmsService, mwService: MWService) extends SmsSenderService {
  private[this] val logger: Logger = LoggerFactory.getLogger(getClass)

  override def consume(data: Array[Byte]): Future[Unit] = {
    val promise: Promise[Unit] = Promise[Unit]()
    Future {
      val smsMessage: SmsMessage = SmsMessage.parseFrom(data)
      sendSms(smsMessage.traceId, smsMessage)
      promise.success()
    }
    promise.future
  }

  def generateContent(contentTemplate: String, code: String) = {
    String.format(contentTemplate, code)
  }

  override def sendSms(traceId: String, smsMessage: SmsMessage): Unit = {
    val sendDate: String = new SimpleDateFormat("YYYY-MM-dd").format(new Date())
    val memberId: Long = smsMessage.memberId
    val smsType: Int = smsMessage.smsType

    val channel: Int = smsMessage.channel
    val mobileTicket: String = smsMessage.mobile

    val template: SmsVerifyTemplate = smsService.getTemplate(smsType)
    smsRepository.selectSmsAggregation(sendDate, memberId, smsType) match {
      case Some(smsAggregation) =>
        if (smsAggregation.totalCount >= template.dayLimit) throw ServiceException.make(ErrorCode.EC_SMS_DAY_LIMIT_ERROR)
        val seconds: Long = (System.currentTimeMillis() - smsAggregation.gmtUpdate.getTime) / 1000
        if (seconds < template.minIntervalSeconds) {
          throw ServiceException.make(ErrorCode.EC_SMS_SEND_TOO_OFTEN_ERROR)
        }
      case None =>
        //(id: Long, sendDate: String, memberId: Long, msgType: Int, totalCount: Int, gmtUpdate:Timestamp)
        smsRepository.createSmsAggregation(SmsVerifyAggregation(0, sendDate, memberId, smsType, 0, new Timestamp(System.currentTimeMillis())))
    }

    val code: String = VerifyCodeHelper.generateCode(4)
    val content = generateContent(template.contentTemplate, code)

    val msgId = System.currentTimeMillis()

    channel match {
      case 1 => mwService.sendVerifyCode(traceId, msgId, mobileTicket, content)
      case _ => logger.error(s"channel:$channel not config ! ")
    }
    val record: SmsVerifyRecord = SmsVerifyRecord(0, memberId, smsType, content, code, channel, msgId.toString, traceId, smsMessage.ip, smsMessage.deviceType.toByte, smsMessage.fingerPrint, 0, 0, 3, new Timestamp(System.currentTimeMillis() + 60 * 1000 * template.expireMinutes), smsMessage.resend, smsMessage.lastChannel)
    smsRepository.createSmsRecord(record, sendDate, memberId, smsType)
  }
}
