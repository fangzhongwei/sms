package com.lawsofnatrue.sms.service

import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.{Inject, Named}

import com.jxjxgo.common.exception.{ErrorCode, ServiceException}
import com.jxjxgo.common.helper.{HttpHelper, RegHelper, XMLHelper}
import com.jxjxgo.edcenter.rpc.domain.{DecryptResponse, EdServiceEndpoint}
import com.twitter.util.{Await, Future}

/**
  * Created by fangzhongwei on 2017/1/19.
  */
trait MWService {
  def sendVerifyCode(traceId: String, msgId: Long, mobile: String, content: String): Boolean
}

class MWServiceImpl @Inject()(@Named("mwkj.code.codeUrl")
                              codeUrl: String,
                              @Named("mwkj.code.userId")
                              userId: String,
                              @Named("mwkj.code.password")
                              password: String,
                              edClientService: EdServiceEndpoint[Future]) extends MWService {
  override def sendVerifyCode(traceId: String, msgId: Long, mobileTicket: String, content: String): Boolean = {
    val decryptResponse: DecryptResponse = Await.result(edClientService.decrypt(traceId, mobileTicket))

    decryptResponse.code match {
      case "0" =>
        val mobile = decryptResponse.raw
        if (!RegHelper.isMobile(mobile)) {
          throw ServiceException.make(ErrorCode.EC_UC_INVALID_MOBILE)
        }
        var params = Map[String, String]()
        params += ("userId" -> userId)
        params += ("password" -> password)
        params += ("pszMobis" -> mobile)
        params += ("pszMsg" -> URLEncoder.encode(content, StandardCharsets.UTF_8.displayName()))
        params += ("iMobiCount" -> "1")
        params += ("pszSubPort" -> "*")
        params += ("MsgId" -> msgId.toString)

        val result: String = HttpHelper.httpGet(uri = codeUrl, Some(params))
        val response: String = XMLHelper.fromString(result).text
        response.length >= 15 && response.length <= 25
      case _ => throw ServiceException.make(ErrorCode.get(decryptResponse.code))
    }

  }
}
