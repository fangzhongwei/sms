package com.lawsofnatrue.sms.server

import java.util

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice, TypeLiteral}
import com.jxjxgo.common.helper.ConfigHelper
import com.jxjxgo.common.rabbitmq.{RabbitmqConsumerTemplate, RabbitmqConsumerTemplateImpl, RabbitmqProducerTemplate, RabbitmqProducerTemplateImpl}
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

      bind(classOf[RabbitmqProducerTemplate]).to(classOf[RabbitmqProducerTemplateImpl]).asEagerSingleton()
      bind(classOf[RabbitmqConsumerTemplate]).to(classOf[RabbitmqConsumerTemplateImpl]).asEagerSingleton()

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

  //  injector.getInstance(classOf[RabbitmqProducerTemplate]).connect
  //  private[this] val consumerTemplate: RabbitmqConsumerTemplate = injector.getInstance(classOf[RabbitmqConsumerTemplate])
  //  consumerTemplate.connect
  //  consumerTemplate.startConsume(ConfigFactory.load().getString("sms.mq.queue"), injector.getInstance(classOf[SmsSenderService]))

  injector.getInstance(classOf[ScroogeThriftServerTemplate]).init
}
