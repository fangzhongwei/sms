package com.lawsofnatrue.sms.service

import javax.inject.Inject

import Ice.Current
import RpcSms._
import com.lawsofnature.common.exception.{ErrorCode, ServiceException}
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by fangzhongwei on 2017/1/3.
  */
class SmsServiceEndPointImpl @Inject()(smsService: SmsService) extends _SmsServiceEndpointDisp {
  private[this] val logger: Logger = LoggerFactory.getLogger(this.getClass)

  override def sendLoginVerificationCode(traceId: String, request: SendLoginVerificationCodeRequest, current: Current): SendLoginVerificationCodeResponse = {
    try {
      new SendLoginVerificationCodeResponse("0", smsService.sendLoginVerificationCode(traceId, request))
    } catch {
      case ex: ServiceException =>
        logger.error(traceId, ex)
        new SendLoginVerificationCodeResponse(ex.getErrorCode.getCode, 0)
      case ex: Exception =>
        logger.error(traceId, ex)
        new SendLoginVerificationCodeResponse(ErrorCode.EC_SYSTEM_ERROR.getCode, 0)
    }
  }

  override def verifyLoginVerificationCode(traceId: String, verifyLoginVerificationCodeRequest: VerifyLoginVerificationCodeRequest, current: Current): BaseResponse = {
    try {
      smsService.verifyLoginVerificationCode(traceId, verifyLoginVerificationCodeRequest) match {
        case true => new BaseResponse("0")
        case false => new BaseResponse(ErrorCode.EC_SMS_WRONG_CODE.getDesc)
      }
    } catch {
      case ex: ServiceException =>
        logger.error(traceId, ex)
        new BaseResponse(ex.getErrorCode.getCode)
      case ex: Exception =>
        logger.error(traceId, ex)
        new BaseResponse(ErrorCode.EC_SYSTEM_ERROR.getCode)
    }
  }
}
