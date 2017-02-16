package com.jxjxgo.sms.test

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import com.jxjxgo.common.helper.{HttpHelper, XMLHelper}
import com.jxjxgo.sms.rpc.domain.{SendLoginVerificationCodeRequest, SmsServiceEndpoint}
import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
import com.typesafe.config.{Config, ConfigFactory}

import scala.xml.NodeSeq

/**
  * Created by fangzhongwei on 2017/1/18.
  */
object SmsTest {

  def main(args: Array[String]): Unit = {
    //    已开设 WBS 账号，
    //    账号：JC2118，密码: 568102
    //    IP:61.145.229.29   端口号：9003
    //    余额：145
    //    只能发：同事您好，感谢您对此次测试的配合。123456
    //    请按照测试管理规范来使用测试账号



// '0 待验证, 1重发失效，2尝试3次失效，3过期 ， 99验证成功'

//    //    userId=账号&password=密码&pszMobis=电话号码&pszMsg=同事您好， 感谢您对此次测试的配合。&iMobiCount=号码个数&pszSubPort=*&MsgId=0
//    var params = Map[String, String]()
//    params += ("userId" -> "JC2118")
//    params += ("password" -> "568102")
//    params += ("pszMobis" -> "15881126718")
//    params += ("pszMsg" -> URLEncoder.encode("同事您好，感谢您对此次测试的配合。147801", StandardCharsets.UTF_8.displayName()))
//    params += ("iMobiCount" -> "1")
//    params += ("pszSubPort" -> "*")
//    params += ("MsgId" -> System.currentTimeMillis().toString)
//    val result: String = HttpHelper.httpGet(uri = "http://61.145.229.29:9003/MWGate/wmgw.asmx/MongateSendSubmit", Some(params))
//    //    val result: String = HttpHelper.httpPost(uri = "http://www.baidu.com")
//    println(result)
//    val elem: scala.xml.Elem = XMLHelper.fromString(result)
//    println(s"elem:${elem.text}")


    testRpc()


  }

  def testRpc() = {

    val config: Config = ConfigFactory.load()
    val endpoint: SmsServiceEndpoint[Future] = Thrift.client.newIface[SmsServiceEndpoint[Future]](config.getString("finagle.thrift.host.port"))
    println(Await.result(endpoint.sendLoginVerificationCode("aaa", SendLoginVerificationCodeRequest(mobileTicket = "1"))))
  }

}
