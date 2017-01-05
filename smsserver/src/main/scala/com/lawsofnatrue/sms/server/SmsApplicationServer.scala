package com.lawsofnatrue.sms.server

import java.util

import Ice.ObjectImpl
import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice}
import com.lawsofnatrue.common.ice._
import com.lawsofnatrue.sms.repo.{SmsRepository, SmsRepositoryImpl}
import com.lawsofnatrue.sms.service._
import com.lawsofnature.common.rabbitmq.{RabbitmqConsumerTemplate, RabbitmqConsumerTemplateImpl, RabbitmqProducerTemplate, RabbitmqProducerTemplateImpl}
import com.lawsofnature.edcenter.client.{EdClientService, EdClientServiceImpl}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

/**
  * Created by fangzhongwei on 2017/1/4.
  */
object SmsApplicationServer {
  private[this] var logger = LoggerFactory.getLogger(this.getClass)

  private[this] val injector = Guice.createInjector(new AbstractModule() {
    override def configure() {
      val map: util.HashMap[String, String] = ConfigHelper.configMap
      Names.bindProperties(binder(), map)
      bind(classOf[ObjectImpl]).to(classOf[SmsServiceEndPointImpl]).asEagerSingleton()
      bind(classOf[IcePrxFactory]).to(classOf[IcePrxFactoryImpl]).asEagerSingleton()
      bind(classOf[IceServerTemplate]).to(classOf[IceServerTemplateImpl]).asEagerSingleton()
      bind(classOf[EdClientService]).to(classOf[EdClientServiceImpl]).asEagerSingleton()
      bind(classOf[RabbitmqProducerTemplate]).to(classOf[RabbitmqProducerTemplateImpl]).asEagerSingleton()
      bind(classOf[RabbitmqConsumerTemplate]).to(classOf[RabbitmqConsumerTemplateImpl]).asEagerSingleton()

      bind(classOf[SmsRepository]).to(classOf[SmsRepositoryImpl]).asEagerSingleton()
      bind(classOf[SmsService]).to(classOf[SmsServiceImpl]).asEagerSingleton()
      bind(classOf[SmsSenderService]).to(classOf[SmsSenderServiceImpl]).asEagerSingleton()
    }
  })

  injector.getInstance(classOf[RabbitmqProducerTemplate]).connect
  injector.getInstance(classOf[EdClientService]).initClient
  injector.getInstance(classOf[IceServerTemplate]).startServer
  private[this] val consumerTemplate: RabbitmqConsumerTemplate = injector.getInstance(classOf[RabbitmqConsumerTemplate])
  consumerTemplate.connect
  consumerTemplate.startConsume(ConfigFactory.load().getString("sms.mq.queue"), injector.getInstance(classOf[SmsSenderService]))
}
