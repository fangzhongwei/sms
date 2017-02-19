package com.lawsofnatrue.sms.repo

import java.sql.Timestamp

import com.jxjxgo.mysql.connection.DBComponent
import com.lawsofnatrue.domain.{SmsVerifyAggregation, SmsVerifyRecord, SmsVerifyTemplate}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
  * Created by fangzhongwei on 2017/1/3.
  */
trait SmsRepository extends Tables {
  this: DBComponent =>

  import profile.api._

  protected def TmSmsAutoInc = TmSmsVerifyRecord returning TmSmsVerifyRecord.map(_.id)

  protected def TmSmsAggregationAutoInc = TmSmsVerifyAggregation returning TmSmsVerifyAggregation.map(_.id)

  implicit def templateRowToDomain(row: TmSmsVerifyTemplateRow): SmsVerifyTemplate = {
    SmsVerifyTemplate(row.smsType, row.minIntervalSeconds, row.contentTemplate, row.dayLimit, row.channel, row.expireMinutes)
  }

  implicit def smsRecordToRow(r: SmsVerifyRecord): TmSmsVerifyRecordRow = {
    val millis: Long = System.currentTimeMillis()
    TmSmsVerifyRecordRow(r.id, r.memberId, r.smsType, r.content, r.verifyCode, r.channel, r.traceId, r.extId, r.ip, r.deviceType, r.fingerPrint, r.status, r.verifyTimes, r.maxTryTimes, r.expireTime, r.resend, r.lastChannel, new Timestamp(millis), new Timestamp(millis))
  }

  implicit def rowToSmsVerifyRecord(r: TmSmsVerifyRecordRow): SmsVerifyRecord = {
    SmsVerifyRecord(r.id, r.memberId, r.smsType, r.content, r.verifyCode, r.channel, r.extId, r.traceId, r.ip, r.deviceType, r.fingerPrint, r.status, r.verifyTimes, r.maxTryTimes, r.expireTime, r.resend, r.lastChannel)
  }

  implicit def smsAggregationToRow(aggregation: SmsVerifyAggregation): TmSmsVerifyAggregationRow = {
    val millis: Long = System.currentTimeMillis()
    TmSmsVerifyAggregationRow(aggregation.id, aggregation.sendDate, aggregation.memberId, aggregation.msgType, aggregation.totalCount, new Timestamp(millis), new Timestamp(millis))
  }

  implicit def rowToSmsAggregation(row: TmSmsVerifyAggregationRow): SmsVerifyAggregation = {
    val millis: Long = System.currentTimeMillis()
    SmsVerifyAggregation(row.id, row.sendDate, row.memberId, row.smsType, row.totalCount, row.gmtUpdate)
  }

  def selectTemplate(smsType: Int): Option[SmsVerifyTemplate] = {
    Await.result(db.run {
      TmSmsVerifyTemplate.filter(_.smsType === smsType).result.headOption
    }, Duration.Inf) match {
      case Some(row) => Some(row)
      case None => None
    }
  }

  def createSmsRecord(smsRecord: SmsVerifyRecord) = db.run {
    TmSmsVerifyRecord += smsRecord
  }

  def createSmsAggregation(smsAggregation: SmsVerifyAggregation) = {
    Await.result(db.run {
      TmSmsVerifyAggregation += smsAggregation
    }, Duration.Inf)
  }

  def selectSmsAggregation(sendDate: String, memberId: Long, smsType: Int): Option[SmsVerifyAggregation] = {
    Await.result(db.run {
      TmSmsVerifyAggregation.filter {
        a => a.sendDate === sendDate && a.memberId === memberId && a.smsType === smsType
      }.result.headOption
    }, Duration.Inf) match {
      case Some(row) => Some(row)
      case None => None
    }
  }

  def selectLastVerifyRecord(memberId: Long, smsType: Int): Option[SmsVerifyRecord] = {
    Await.result(db.run {
      TmSmsVerifyRecord.sortBy(_.id).take(1).filter {
        r => r.memberId === memberId && r.smsType === smsType
      }.result.headOption
    }, Duration.Inf) match {
      case Some(row) => Some(row)
      case None => None
    }
  }

  def createSmsRecord(smsRecord: SmsVerifyRecord, sendDate: String, memberId: Long, smsType: Int) = {
    val a = (for {
      ns <- sqlu"""UPDATE tm_sms_verify_aggregation SET total_count = total_count + 1 WHERE send_date = $sendDate AND member_id = $memberId AND sms_type = $smsType """
      _ <- TmSmsVerifyRecord += smsRecord
    } yield ()).transactionally
    db.run(a)
  }

  def increateTryTimes(id: Long) = db.run {
    sqlu"""UPDATE tm_sms_verify_record SET verify_times = verify_times + 1 WHERE id = $id """
  }

  def updateRecordStatus(id: Long, status: Int) = db.run {
    sqlu"""UPDATE tm_sms_verify_record SET status = $status, verify_times = verify_times + 1 WHERE id = $id """
  }

  def getNextAggregationId: Long = {
    Await.result(db.run(sql"""select nextval('seq_sms_verify_aggregation_id')""".as[(Long)]), Duration.Inf).head
  }

  def getNextRecordId: Long = {
    Await.result(db.run(sql"""select nextval('seq_sms_verify_record_id')""".as[(Long)]), Duration.Inf).head
  }
}

class SmsRepositoryImpl extends SmsRepository with DBComponent