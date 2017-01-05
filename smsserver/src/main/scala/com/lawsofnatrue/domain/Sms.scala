package com.lawsofnatrue.domain

import java.sql.Timestamp

/**
  * Created by fangzhongwei on 2017/1/3.
  */
case class SmsVerifyTemplate(smsType: Int, minIntervalSeconds: Int, contentTemplate: String, dayLimit: Int, channel: Int, expireMinutes:Int)

case class SmsVerifyAggregation(id: Long, sendDate: String, memberId: Long, msgType: Int, totalCount: Int, gmtUpdate: Timestamp)

case class SmsVerifyRecord(id: Long, memberId: Long, smsType: Int, content: String, verifyCode: String, channel: Int, traceId: String, ip: Long, deviceType: Byte, fingerPrint: String, status:Byte, verifyTimes:Byte, maxTryTimes:Byte, expireTime:Timestamp, resend:Boolean, lastChannel:Int)