package com.lawsofnatrue.sms.service

import javax.inject.Inject

import com.jxjxgo.common.exception.{ErrorCode, ServiceException}
import com.jxjxgo.sms.rpc.domain._
import com.twitter.util.Future
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by fangzhongwei on 2017/1/3.
  */
class SmsServiceEndPointImpl @Inject()(smsService: SmsService) extends SmsServiceEndpoint[Future] {
  private[this] val logger: Logger = LoggerFactory.getLogger(this.getClass)

  override def sendLoginVerificationCode(traceId: String, request: SendLoginVerificationCodeRequest): Future[SendLoginVerificationCodeResponse] = {
    try {
      Future.value(SendLoginVerificationCodeResponse("0", smsService.sendLoginVerificationCode(traceId, request)))
    } catch {
      case ex: ServiceException =>
        logger.error(traceId, ex)
        Future.value(SendLoginVerificationCodeResponse(ex.getErrorCode.getCode, 0))
      case ex: Exception =>
        logger.error(traceId, ex)
        Future.value(SendLoginVerificationCodeResponse(ErrorCode.EC_SYSTEM_ERROR.getCode, 0))
    }
  }

  override def verifyLoginVerificationCode(traceId: String, request: VerifyLoginVerificationCodeRequest): Future[SmsBaseResponse] = {
    try {
      smsService.verifyLoginVerificationCode(traceId, request) match {
        case true => Future.value(SmsBaseResponse("0"))
        case false => Future.value(SmsBaseResponse(ErrorCode.EC_SMS_WRONG_CODE.getDesc))
      }
    } catch {
      case ex: ServiceException =>
        logger.error(traceId, ex)
        Future.value(SmsBaseResponse(ex.getErrorCode.getCode))
      case ex: Exception =>
        logger.error(traceId, ex)
        Future.value(SmsBaseResponse(ErrorCode.EC_SYSTEM_ERROR.getCode))
    }
  }
}
