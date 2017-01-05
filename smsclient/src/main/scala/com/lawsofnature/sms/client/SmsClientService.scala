package com.lawsofnature.sms.client

import javax.inject.{Inject, Named}

import RpcSms._
import com.lawsofnatrue.common.ice.IcePrxFactory
import org.slf4j.LoggerFactory

/**
  * Created by fangzhongwei on 2016/12/27.
  */
trait SmsClientService {
  def initClient

  def sendLoginVerificationCode(traceId: String, request: SendLoginVerificationCodeRequest): SendLoginVerificationCodeResponse

  def verifyLoginVerificationCode(traceId: String, request: VerifyLoginVerificationCodeRequest): BaseResponse
}

class SmsClientServiceImpl @Inject()(@Named("sms.ice.client.init.config") iceInitConfig: String,
                                     @Named("sms.ice.client.init.size") iceInitSizeConfig: String,
                                     @Named("sms.ice.client.init.size-max") iceInitSizeMaxConfig: String,
                                     @Named("sms.ice.client.init.size-warn") iceInitSizeWarnConfig: String,
                                     @Named("sms.ice.client.proxy.config") proxyConfig: String,
                                     icePrxFactory: IcePrxFactory) extends SmsClientService {
  val logger = LoggerFactory.getLogger(this.getClass)

  var instance: SmsServiceEndpointPrx = _

  override def initClient: Unit = {
    instance = icePrxFactory.make[SmsServiceEndpointPrx](Array[String](iceInitConfig, iceInitSizeConfig, iceInitSizeMaxConfig, iceInitSizeWarnConfig), proxyConfig, SmsServiceEndpointPrxHelper.checkedCast)
  }

  override def sendLoginVerificationCode(traceId: String, request: SendLoginVerificationCodeRequest): SendLoginVerificationCodeResponse = instance.sendLoginVerificationCode(traceId, request)

  override def verifyLoginVerificationCode(traceId: String, request: VerifyLoginVerificationCodeRequest): BaseResponse = instance.verifyLoginVerificationCode(traceId, request)
}
