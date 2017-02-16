package com.lawsofnatrue.sms.repo

import com.jxjxgo.mysql.connection.DBImpl


/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables extends DBImpl{
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = TmSmsVerifyAggregation.schema ++ TmSmsVerifyRecord.schema ++ TmSmsVerifyTemplate.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table TmSmsVerifyAggregation
   *  @param id Database column id SqlType(int8), PrimaryKey
   *  @param sendDate Database column send_date SqlType(varchar), Length(10,true)
   *  @param memberId Database column member_id SqlType(int8)
   *  @param smsType Database column sms_type SqlType(int4)
   *  @param totalCount Database column total_count SqlType(int4)
   *  @param gmtCreate Database column gmt_create SqlType(timestamp)
   *  @param gmtUpdate Database column gmt_update SqlType(timestamp) */
  case class TmSmsVerifyAggregationRow(id: Long, sendDate: String, memberId: Long, smsType: Int, totalCount: Int, gmtCreate: java.sql.Timestamp, gmtUpdate: java.sql.Timestamp)
  /** GetResult implicit for fetching TmSmsVerifyAggregationRow objects using plain SQL queries */
  implicit def GetResultTmSmsVerifyAggregationRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[java.sql.Timestamp]): GR[TmSmsVerifyAggregationRow] = GR{
    prs => import prs._
    TmSmsVerifyAggregationRow.tupled((<<[Long], <<[String], <<[Long], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table tm_sms_verify_aggregation. Objects of this class serve as prototypes for rows in queries. */
  class TmSmsVerifyAggregation(_tableTag: Tag) extends profile.api.Table[TmSmsVerifyAggregationRow](_tableTag, "tm_sms_verify_aggregation") {
    def * = (id, sendDate, memberId, smsType, totalCount, gmtCreate, gmtUpdate) <> (TmSmsVerifyAggregationRow.tupled, TmSmsVerifyAggregationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(sendDate), Rep.Some(memberId), Rep.Some(smsType), Rep.Some(totalCount), Rep.Some(gmtCreate), Rep.Some(gmtUpdate)).shaped.<>({r=>import r._; _1.map(_=> TmSmsVerifyAggregationRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int8), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)
    /** Database column send_date SqlType(varchar), Length(10,true) */
    val sendDate: Rep[String] = column[String]("send_date", O.Length(10,varying=true))
    /** Database column member_id SqlType(int8) */
    val memberId: Rep[Long] = column[Long]("member_id")
    /** Database column sms_type SqlType(int4) */
    val smsType: Rep[Int] = column[Int]("sms_type")
    /** Database column total_count SqlType(int4) */
    val totalCount: Rep[Int] = column[Int]("total_count")
    /** Database column gmt_create SqlType(timestamp) */
    val gmtCreate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("gmt_create")
    /** Database column gmt_update SqlType(timestamp) */
    val gmtUpdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("gmt_update")
  }
  /** Collection-like TableQuery object for table TmSmsVerifyAggregation */
  lazy val TmSmsVerifyAggregation = new TableQuery(tag => new TmSmsVerifyAggregation(tag))

  /** Entity class storing rows of table TmSmsVerifyRecord
   *  @param id Database column id SqlType(int8), PrimaryKey
   *  @param memberId Database column member_id SqlType(int8)
   *  @param smsType Database column sms_type SqlType(int4)
   *  @param content Database column content SqlType(varchar), Length(140,true)
   *  @param verifyCode Database column verify_code SqlType(varchar), Length(8,true)
   *  @param channel Database column channel SqlType(int4)
   *  @param extId Database column ext_id SqlType(varchar), Length(64,true)
   *  @param traceId Database column trace_id SqlType(varchar), Length(32,true)
   *  @param ip Database column ip SqlType(int8)
   *  @param deviceType Database column device_type SqlType(int2)
   *  @param fingerPrint Database column finger_print SqlType(varchar), Length(64,true)
   *  @param status Database column status SqlType(int2)
   *  @param verifyTimes Database column verify_times SqlType(int2)
   *  @param maxTryTimes Database column max_try_times SqlType(int2)
   *  @param expireTime Database column expire_time SqlType(timestamp)
   *  @param resend Database column resend SqlType(bool), Default(false)
   *  @param lastChannel Database column last_channel SqlType(int4)
   *  @param gmtCreate Database column gmt_create SqlType(timestamp)
   *  @param gmtUpdate Database column gmt_update SqlType(timestamp) */
  case class TmSmsVerifyRecordRow(id: Long, memberId: Long, smsType: Int, content: String, verifyCode: String, channel: Int, extId: String, traceId: String, ip: Long, deviceType: Short, fingerPrint: String, status: Short, verifyTimes: Short, maxTryTimes: Short, expireTime: java.sql.Timestamp, resend: Boolean = false, lastChannel: Int, gmtCreate: java.sql.Timestamp, gmtUpdate: java.sql.Timestamp)
  /** GetResult implicit for fetching TmSmsVerifyRecordRow objects using plain SQL queries */
  implicit def GetResultTmSmsVerifyRecordRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[String], e3: GR[Short], e4: GR[java.sql.Timestamp], e5: GR[Boolean]): GR[TmSmsVerifyRecordRow] = GR{
    prs => import prs._
    TmSmsVerifyRecordRow.tupled((<<[Long], <<[Long], <<[Int], <<[String], <<[String], <<[Int], <<[String], <<[String], <<[Long], <<[Short], <<[String], <<[Short], <<[Short], <<[Short], <<[java.sql.Timestamp], <<[Boolean], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table tm_sms_verify_record. Objects of this class serve as prototypes for rows in queries. */
  class TmSmsVerifyRecord(_tableTag: Tag) extends profile.api.Table[TmSmsVerifyRecordRow](_tableTag, "tm_sms_verify_record") {
    def * = (id, memberId, smsType, content, verifyCode, channel, extId, traceId, ip, deviceType, fingerPrint, status, verifyTimes, maxTryTimes, expireTime, resend, lastChannel, gmtCreate, gmtUpdate) <> (TmSmsVerifyRecordRow.tupled, TmSmsVerifyRecordRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(memberId), Rep.Some(smsType), Rep.Some(content), Rep.Some(verifyCode), Rep.Some(channel), Rep.Some(extId), Rep.Some(traceId), Rep.Some(ip), Rep.Some(deviceType), Rep.Some(fingerPrint), Rep.Some(status), Rep.Some(verifyTimes), Rep.Some(maxTryTimes), Rep.Some(expireTime), Rep.Some(resend), Rep.Some(lastChannel), Rep.Some(gmtCreate), Rep.Some(gmtUpdate)).shaped.<>({r=>import r._; _1.map(_=> TmSmsVerifyRecordRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15.get, _16.get, _17.get, _18.get, _19.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int8), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)
    /** Database column member_id SqlType(int8) */
    val memberId: Rep[Long] = column[Long]("member_id")
    /** Database column sms_type SqlType(int4) */
    val smsType: Rep[Int] = column[Int]("sms_type")
    /** Database column content SqlType(varchar), Length(140,true) */
    val content: Rep[String] = column[String]("content", O.Length(140,varying=true))
    /** Database column verify_code SqlType(varchar), Length(8,true) */
    val verifyCode: Rep[String] = column[String]("verify_code", O.Length(8,varying=true))
    /** Database column channel SqlType(int4) */
    val channel: Rep[Int] = column[Int]("channel")
    /** Database column ext_id SqlType(varchar), Length(64,true) */
    val extId: Rep[String] = column[String]("ext_id", O.Length(64,varying=true))
    /** Database column trace_id SqlType(varchar), Length(32,true) */
    val traceId: Rep[String] = column[String]("trace_id", O.Length(32,varying=true))
    /** Database column ip SqlType(int8) */
    val ip: Rep[Long] = column[Long]("ip")
    /** Database column device_type SqlType(int2) */
    val deviceType: Rep[Short] = column[Short]("device_type")
    /** Database column finger_print SqlType(varchar), Length(64,true) */
    val fingerPrint: Rep[String] = column[String]("finger_print", O.Length(64,varying=true))
    /** Database column status SqlType(int2) */
    val status: Rep[Short] = column[Short]("status")
    /** Database column verify_times SqlType(int2) */
    val verifyTimes: Rep[Short] = column[Short]("verify_times")
    /** Database column max_try_times SqlType(int2) */
    val maxTryTimes: Rep[Short] = column[Short]("max_try_times")
    /** Database column expire_time SqlType(timestamp) */
    val expireTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("expire_time")
    /** Database column resend SqlType(bool), Default(false) */
    val resend: Rep[Boolean] = column[Boolean]("resend", O.Default(false))
    /** Database column last_channel SqlType(int4) */
    val lastChannel: Rep[Int] = column[Int]("last_channel")
    /** Database column gmt_create SqlType(timestamp) */
    val gmtCreate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("gmt_create")
    /** Database column gmt_update SqlType(timestamp) */
    val gmtUpdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("gmt_update")
  }
  /** Collection-like TableQuery object for table TmSmsVerifyRecord */
  lazy val TmSmsVerifyRecord = new TableQuery(tag => new TmSmsVerifyRecord(tag))

  /** Entity class storing rows of table TmSmsVerifyTemplate
   *  @param smsType Database column sms_type SqlType(int4), PrimaryKey
   *  @param minIntervalSeconds Database column min_interval_seconds SqlType(int4)
   *  @param contentTemplate Database column content_template SqlType(varchar), Length(140,true)
   *  @param dayLimit Database column day_limit SqlType(int4)
   *  @param channel Database column channel SqlType(int4)
   *  @param expireMinutes Database column expire_minutes SqlType(int2)
   *  @param gmtCreate Database column gmt_create SqlType(timestamp)
   *  @param gmtUpdate Database column gmt_update SqlType(timestamp) */
  case class TmSmsVerifyTemplateRow(smsType: Int, minIntervalSeconds: Int, contentTemplate: String, dayLimit: Int, channel: Int, expireMinutes: Short, gmtCreate: java.sql.Timestamp, gmtUpdate: java.sql.Timestamp)
  /** GetResult implicit for fetching TmSmsVerifyTemplateRow objects using plain SQL queries */
  implicit def GetResultTmSmsVerifyTemplateRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Short], e3: GR[java.sql.Timestamp]): GR[TmSmsVerifyTemplateRow] = GR{
    prs => import prs._
    TmSmsVerifyTemplateRow.tupled((<<[Int], <<[Int], <<[String], <<[Int], <<[Int], <<[Short], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table tm_sms_verify_template. Objects of this class serve as prototypes for rows in queries. */
  class TmSmsVerifyTemplate(_tableTag: Tag) extends profile.api.Table[TmSmsVerifyTemplateRow](_tableTag, "tm_sms_verify_template") {
    def * = (smsType, minIntervalSeconds, contentTemplate, dayLimit, channel, expireMinutes, gmtCreate, gmtUpdate) <> (TmSmsVerifyTemplateRow.tupled, TmSmsVerifyTemplateRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(smsType), Rep.Some(minIntervalSeconds), Rep.Some(contentTemplate), Rep.Some(dayLimit), Rep.Some(channel), Rep.Some(expireMinutes), Rep.Some(gmtCreate), Rep.Some(gmtUpdate)).shaped.<>({r=>import r._; _1.map(_=> TmSmsVerifyTemplateRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column sms_type SqlType(int4), PrimaryKey */
    val smsType: Rep[Int] = column[Int]("sms_type", O.PrimaryKey)
    /** Database column min_interval_seconds SqlType(int4) */
    val minIntervalSeconds: Rep[Int] = column[Int]("min_interval_seconds")
    /** Database column content_template SqlType(varchar), Length(140,true) */
    val contentTemplate: Rep[String] = column[String]("content_template", O.Length(140,varying=true))
    /** Database column day_limit SqlType(int4) */
    val dayLimit: Rep[Int] = column[Int]("day_limit")
    /** Database column channel SqlType(int4) */
    val channel: Rep[Int] = column[Int]("channel")
    /** Database column expire_minutes SqlType(int2) */
    val expireMinutes: Rep[Short] = column[Short]("expire_minutes")
    /** Database column gmt_create SqlType(timestamp) */
    val gmtCreate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("gmt_create")
    /** Database column gmt_update SqlType(timestamp) */
    val gmtUpdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("gmt_update")
  }
  /** Collection-like TableQuery object for table TmSmsVerifyTemplate */
  lazy val TmSmsVerifyTemplate = new TableQuery(tag => new TmSmsVerifyTemplate(tag))
}
