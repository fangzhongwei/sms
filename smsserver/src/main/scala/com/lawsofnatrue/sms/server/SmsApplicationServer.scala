package com.lawsofnatrue.sms.server

import java.util

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice, TypeLiteral}
import com.jxjxgo.common.helper.ConfigHelper
import com.jxjxgo.common.kafka.template.{ConsumerTemplate, ConsumerTemplateImpl, ProducerTemplate, ProducerTemplateImpl}
import com.jxjxgo.common.mq.service.ConsumerService
import com.jxjxgo.edcenter.rpc.domain.EdServiceEndpoint
import com.jxjxgo.memberber.rpc.domain.MemberEndpoint
import com.jxjxgo.scrooge.thrift.template.{ScroogeThriftServerTemplate, ScroogeThriftServerTemplateImpl}
import com.lawsofnatrue.sms.repo.{SmsRepository, SmsRepositoryImpl}
import com.lawsofnatrue.sms.service._
import com.twitter.finagle.Thrift
import com.twitter.scrooge.ThriftService
import com.twitter.util.Future
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

/**
  * Created by fangzhongwei on 2017/1/4.
  */
object SmsApplicationServer extends App {
  private[this] var logger = LoggerFactory.getLogger(this.getClass)

  private[this] val injector = Guice.createInjector(new AbstractModule() {
    override def configure() {
      val map: util.HashMap[String, String] = ConfigHelper.configMap
      Names.bindProperties(binder(), map)

      val config: Config = ConfigFactory.load()

      bind(classOf[ProducerTemplate]).to(classOf[ProducerTemplateImpl]).asEagerSingleton()
      bind(classOf[ConsumerTemplate]).to(classOf[ConsumerTemplateImpl]).asEagerSingleton()
      bind(classOf[ConsumerService]).to(classOf[SmsSenderServiceImpl]).asEagerSingleton()

      bind(classOf[SmsRepository]).to(classOf[SmsRepositoryImpl]).asEagerSingleton()
      bind(classOf[SmsService]).to(classOf[SmsServiceImpl]).asEagerSingleton()
      bind(classOf[MWService]).to(classOf[MWServiceImpl]).asEagerSingleton()
      bind(classOf[SmsSenderService]).to(classOf[SmsSenderServiceImpl]).asEagerSingleton()

      bind(new TypeLiteral[EdServiceEndpoint[Future]]() {}).toInstance(Thrift.client.newIface[EdServiceEndpoint[Future]](config.getString("edcenter.thrift.host.port")))
      bind(new TypeLiteral[MemberEndpoint[Future]]() {}).toInstance(Thrift.client.newIface[MemberEndpoint[Future]](config.getString("member.thrift.host.port")))

      bind(classOf[ThriftService]).to(classOf[SmsServiceEndPointImpl]).asEagerSingleton()
      bind(classOf[ScroogeThriftServerTemplate]).to(classOf[ScroogeThriftServerTemplateImpl]).asEagerSingleton()
    }
  })

  val config: Config = ConfigFactory.load()
  val consumerTemplate: ConsumerTemplate = injector.getInstance(classOf[ConsumerTemplate])
  consumerTemplate.init
  consumerTemplate.consume(config.getString("kafka.topic.sms.login.code"))

  injector.getInstance(classOf[ScroogeThriftServerTemplate]).init
}
