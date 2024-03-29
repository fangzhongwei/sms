/**
 * Generated by Scrooge
 *   version: 4.5.0
 *   rev: 014664de600267b36809bbc85225e26aec286216
 *   built at: 20160203-205352
 */
package com.jxjxgo.sms.rpc.domain

import com.twitter.scrooge.{
  LazyTProtocol,
  TFieldBlob, ThriftException, ThriftStruct, ThriftStructCodec3, ThriftStructFieldInfo,
  ThriftStructMetaData, ThriftUtil}
import org.apache.thrift.protocol._
import org.apache.thrift.transport.{TMemoryBuffer, TTransport}
import java.nio.ByteBuffer
import java.util.Arrays
import scala.collection.immutable.{Map => immutable$Map}
import scala.collection.mutable.Builder
import scala.collection.mutable.{
  ArrayBuffer => mutable$ArrayBuffer, Buffer => mutable$Buffer,
  HashMap => mutable$HashMap, HashSet => mutable$HashSet}
import scala.collection.{Map, Set}


object SendLoginVerificationCodeRequest extends ThriftStructCodec3[SendLoginVerificationCodeRequest] {
  private val NoPassthroughFields = immutable$Map.empty[Short, TFieldBlob]
  val Struct = new TStruct("SendLoginVerificationCodeRequest")
  val IpField = new TField("ip", TType.I64, 1)
  val IpFieldManifest = implicitly[Manifest[Long]]
  val DeviceTypeField = new TField("deviceType", TType.I32, 2)
  val DeviceTypeFieldManifest = implicitly[Manifest[Int]]
  val FingerPrintField = new TField("fingerPrint", TType.STRING, 3)
  val FingerPrintFieldManifest = implicitly[Manifest[String]]
  val MobileTicketField = new TField("mobileTicket", TType.STRING, 4)
  val MobileTicketFieldManifest = implicitly[Manifest[String]]
  val SmsTypeField = new TField("smsType", TType.I32, 5)
  val SmsTypeFieldManifest = implicitly[Manifest[Int]]
  val ResendField = new TField("resend", TType.BOOL, 6)
  val ResendFieldManifest = implicitly[Manifest[Boolean]]
  val LastChannelField = new TField("lastChannel", TType.I32, 7)
  val LastChannelFieldManifest = implicitly[Manifest[Int]]

  /**
   * Field information in declaration order.
   */
  lazy val fieldInfos: scala.List[ThriftStructFieldInfo] = scala.List[ThriftStructFieldInfo](
    new ThriftStructFieldInfo(
      IpField,
      false,
      false,
      IpFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      DeviceTypeField,
      false,
      false,
      DeviceTypeFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      FingerPrintField,
      false,
      false,
      FingerPrintFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      MobileTicketField,
      false,
      false,
      MobileTicketFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      SmsTypeField,
      false,
      false,
      SmsTypeFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      ResendField,
      false,
      false,
      ResendFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      LastChannelField,
      false,
      false,
      LastChannelFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    )
  )

  lazy val structAnnotations: immutable$Map[String, String] =
    immutable$Map.empty[String, String]

  /**
   * Checks that all required fields are non-null.
   */
  def validate(_item: SendLoginVerificationCodeRequest): Unit = {
  }

  def withoutPassthroughFields(original: SendLoginVerificationCodeRequest): SendLoginVerificationCodeRequest =
    new Immutable(
      ip =
        {
          val field = original.ip
          field
        },
      deviceType =
        {
          val field = original.deviceType
          field
        },
      fingerPrint =
        {
          val field = original.fingerPrint
          field
        },
      mobileTicket =
        {
          val field = original.mobileTicket
          field
        },
      smsType =
        {
          val field = original.smsType
          field
        },
      resend =
        {
          val field = original.resend
          field
        },
      lastChannel =
        {
          val field = original.lastChannel
          field
        }
    )

  override def encode(_item: SendLoginVerificationCodeRequest, _oproto: TProtocol): Unit = {
    _item.write(_oproto)
  }

  private[this] def lazyDecode(_iprot: LazyTProtocol): SendLoginVerificationCodeRequest = {

    var ip: Long = 0L
    var deviceType: Int = 0
    var fingerPrintOffset: Int = -1
    var mobileTicketOffset: Int = -1
    var smsType: Int = 0
    var resend: Boolean = false
    var lastChannel: Int = 0

    var _passthroughFields: Builder[(Short, TFieldBlob), immutable$Map[Short, TFieldBlob]] = null
    var _done = false
    val _start_offset = _iprot.offset

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 =>
            _field.`type` match {
              case TType.I64 =>
    
                ip = readIpValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I64
                throw new TProtocolException(
                  "Received wrong type for field 'ip' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 2 =>
            _field.`type` match {
              case TType.I32 =>
    
                deviceType = readDeviceTypeValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'deviceType' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 3 =>
            _field.`type` match {
              case TType.STRING =>
                fingerPrintOffset = _iprot.offsetSkipString
    
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'fingerPrint' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 4 =>
            _field.`type` match {
              case TType.STRING =>
                mobileTicketOffset = _iprot.offsetSkipString
    
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'mobileTicket' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 5 =>
            _field.`type` match {
              case TType.I32 =>
    
                smsType = readSmsTypeValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'smsType' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 6 =>
            _field.`type` match {
              case TType.BOOL =>
    
                resend = readResendValue(_iprot)
              case _actualType =>
                val _expectedType = TType.BOOL
                throw new TProtocolException(
                  "Received wrong type for field 'resend' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 7 =>
            _field.`type` match {
              case TType.I32 =>
    
                lastChannel = readLastChannelValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'lastChannel' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case _ =>
            if (_passthroughFields == null)
              _passthroughFields = immutable$Map.newBuilder[Short, TFieldBlob]
            _passthroughFields += (_field.id -> TFieldBlob.read(_field, _iprot))
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    new LazyImmutable(
      _iprot,
      _iprot.buffer,
      _start_offset,
      _iprot.offset,
      ip,
      deviceType,
      fingerPrintOffset,
      mobileTicketOffset,
      smsType,
      resend,
      lastChannel,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  override def decode(_iprot: TProtocol): SendLoginVerificationCodeRequest =
    _iprot match {
      case i: LazyTProtocol => lazyDecode(i)
      case i => eagerDecode(i)
    }

  private[this] def eagerDecode(_iprot: TProtocol): SendLoginVerificationCodeRequest = {
    var ip: Long = 0L
    var deviceType: Int = 0
    var fingerPrint: String = ""
    var mobileTicket: String = ""
    var smsType: Int = 0
    var resend: Boolean = false
    var lastChannel: Int = 0
    var _passthroughFields: Builder[(Short, TFieldBlob), immutable$Map[Short, TFieldBlob]] = null
    var _done = false

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 =>
            _field.`type` match {
              case TType.I64 =>
                ip = readIpValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I64
                throw new TProtocolException(
                  "Received wrong type for field 'ip' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 2 =>
            _field.`type` match {
              case TType.I32 =>
                deviceType = readDeviceTypeValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'deviceType' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 3 =>
            _field.`type` match {
              case TType.STRING =>
                fingerPrint = readFingerPrintValue(_iprot)
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'fingerPrint' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 4 =>
            _field.`type` match {
              case TType.STRING =>
                mobileTicket = readMobileTicketValue(_iprot)
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'mobileTicket' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 5 =>
            _field.`type` match {
              case TType.I32 =>
                smsType = readSmsTypeValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'smsType' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 6 =>
            _field.`type` match {
              case TType.BOOL =>
                resend = readResendValue(_iprot)
              case _actualType =>
                val _expectedType = TType.BOOL
                throw new TProtocolException(
                  "Received wrong type for field 'resend' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 7 =>
            _field.`type` match {
              case TType.I32 =>
                lastChannel = readLastChannelValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'lastChannel' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case _ =>
            if (_passthroughFields == null)
              _passthroughFields = immutable$Map.newBuilder[Short, TFieldBlob]
            _passthroughFields += (_field.id -> TFieldBlob.read(_field, _iprot))
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    new Immutable(
      ip,
      deviceType,
      fingerPrint,
      mobileTicket,
      smsType,
      resend,
      lastChannel,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  def apply(
    ip: Long = 0L,
    deviceType: Int = 0,
    fingerPrint: String = "",
    mobileTicket: String = "",
    smsType: Int = 0,
    resend: Boolean = false,
    lastChannel: Int = 0
  ): SendLoginVerificationCodeRequest =
    new Immutable(
      ip,
      deviceType,
      fingerPrint,
      mobileTicket,
      smsType,
      resend,
      lastChannel
    )

  def unapply(_item: SendLoginVerificationCodeRequest): _root_.scala.Option[scala.Product7[Long, Int, String, String, Int, Boolean, Int]] = _root_.scala.Some(_item)


  @inline private def readIpValue(_iprot: TProtocol): Long = {
    _iprot.readI64()
  }

  @inline private def writeIpField(ip_item: Long, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(IpField)
    writeIpValue(ip_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeIpValue(ip_item: Long, _oprot: TProtocol): Unit = {
    _oprot.writeI64(ip_item)
  }

  @inline private def readDeviceTypeValue(_iprot: TProtocol): Int = {
    _iprot.readI32()
  }

  @inline private def writeDeviceTypeField(deviceType_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(DeviceTypeField)
    writeDeviceTypeValue(deviceType_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeDeviceTypeValue(deviceType_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeI32(deviceType_item)
  }

  @inline private def readFingerPrintValue(_iprot: TProtocol): String = {
    _iprot.readString()
  }

  @inline private def writeFingerPrintField(fingerPrint_item: String, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(FingerPrintField)
    writeFingerPrintValue(fingerPrint_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeFingerPrintValue(fingerPrint_item: String, _oprot: TProtocol): Unit = {
    _oprot.writeString(fingerPrint_item)
  }

  @inline private def readMobileTicketValue(_iprot: TProtocol): String = {
    _iprot.readString()
  }

  @inline private def writeMobileTicketField(mobileTicket_item: String, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(MobileTicketField)
    writeMobileTicketValue(mobileTicket_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeMobileTicketValue(mobileTicket_item: String, _oprot: TProtocol): Unit = {
    _oprot.writeString(mobileTicket_item)
  }

  @inline private def readSmsTypeValue(_iprot: TProtocol): Int = {
    _iprot.readI32()
  }

  @inline private def writeSmsTypeField(smsType_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(SmsTypeField)
    writeSmsTypeValue(smsType_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeSmsTypeValue(smsType_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeI32(smsType_item)
  }

  @inline private def readResendValue(_iprot: TProtocol): Boolean = {
    _iprot.readBool()
  }

  @inline private def writeResendField(resend_item: Boolean, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(ResendField)
    writeResendValue(resend_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeResendValue(resend_item: Boolean, _oprot: TProtocol): Unit = {
    _oprot.writeBool(resend_item)
  }

  @inline private def readLastChannelValue(_iprot: TProtocol): Int = {
    _iprot.readI32()
  }

  @inline private def writeLastChannelField(lastChannel_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(LastChannelField)
    writeLastChannelValue(lastChannel_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeLastChannelValue(lastChannel_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeI32(lastChannel_item)
  }


  object Immutable extends ThriftStructCodec3[SendLoginVerificationCodeRequest] {
    override def encode(_item: SendLoginVerificationCodeRequest, _oproto: TProtocol): Unit = { _item.write(_oproto) }
    override def decode(_iprot: TProtocol): SendLoginVerificationCodeRequest = SendLoginVerificationCodeRequest.decode(_iprot)
    override lazy val metaData: ThriftStructMetaData[SendLoginVerificationCodeRequest] = SendLoginVerificationCodeRequest.metaData
  }

  /**
   * The default read-only implementation of SendLoginVerificationCodeRequest.  You typically should not need to
   * directly reference this class; instead, use the SendLoginVerificationCodeRequest.apply method to construct
   * new instances.
   */
  class Immutable(
      val ip: Long,
      val deviceType: Int,
      val fingerPrint: String,
      val mobileTicket: String,
      val smsType: Int,
      val resend: Boolean,
      val lastChannel: Int,
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends SendLoginVerificationCodeRequest {
    def this(
      ip: Long = 0L,
      deviceType: Int = 0,
      fingerPrint: String = "",
      mobileTicket: String = "",
      smsType: Int = 0,
      resend: Boolean = false,
      lastChannel: Int = 0
    ) = this(
      ip,
      deviceType,
      fingerPrint,
      mobileTicket,
      smsType,
      resend,
      lastChannel,
      Map.empty
    )
  }

  /**
   * This is another Immutable, this however keeps strings as lazy values that are lazily decoded from the backing
   * array byte on read.
   */
  private[this] class LazyImmutable(
      _proto: LazyTProtocol,
      _buf: Array[Byte],
      _start_offset: Int,
      _end_offset: Int,
      val ip: Long,
      val deviceType: Int,
      fingerPrintOffset: Int,
      mobileTicketOffset: Int,
      val smsType: Int,
      val resend: Boolean,
      val lastChannel: Int,
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends SendLoginVerificationCodeRequest {

    override def write(_oprot: TProtocol): Unit = {
      _oprot match {
        case i: LazyTProtocol => i.writeRaw(_buf, _start_offset, _end_offset - _start_offset)
        case _ => super.write(_oprot)
      }
    }

    lazy val fingerPrint: String =
      if (fingerPrintOffset == -1)
        ""
      else {
        _proto.decodeString(_buf, fingerPrintOffset)
      }
    lazy val mobileTicket: String =
      if (mobileTicketOffset == -1)
        ""
      else {
        _proto.decodeString(_buf, mobileTicketOffset)
      }

    /**
     * Override the super hash code to make it a lazy val rather than def.
     *
     * Calculating the hash code can be expensive, caching it where possible
     * can provide significant performance wins. (Key in a hash map for instance)
     * Usually not safe since the normal constructor will accept a mutable map or
     * set as an arg
     * Here however we control how the class is generated from serialized data.
     * With the class private and the contract that we throw away our mutable references
     * having the hash code lazy here is safe.
     */
    override lazy val hashCode = super.hashCode
  }

  /**
   * This Proxy trait allows you to extend the SendLoginVerificationCodeRequest trait with additional state or
   * behavior and implement the read-only methods from SendLoginVerificationCodeRequest using an underlying
   * instance.
   */
  trait Proxy extends SendLoginVerificationCodeRequest {
    protected def _underlying_SendLoginVerificationCodeRequest: SendLoginVerificationCodeRequest
    override def ip: Long = _underlying_SendLoginVerificationCodeRequest.ip
    override def deviceType: Int = _underlying_SendLoginVerificationCodeRequest.deviceType
    override def fingerPrint: String = _underlying_SendLoginVerificationCodeRequest.fingerPrint
    override def mobileTicket: String = _underlying_SendLoginVerificationCodeRequest.mobileTicket
    override def smsType: Int = _underlying_SendLoginVerificationCodeRequest.smsType
    override def resend: Boolean = _underlying_SendLoginVerificationCodeRequest.resend
    override def lastChannel: Int = _underlying_SendLoginVerificationCodeRequest.lastChannel
    override def _passthroughFields = _underlying_SendLoginVerificationCodeRequest._passthroughFields
  }
}

trait SendLoginVerificationCodeRequest
  extends ThriftStruct
  with scala.Product7[Long, Int, String, String, Int, Boolean, Int]
  with java.io.Serializable
{
  import SendLoginVerificationCodeRequest._

  def ip: Long
  def deviceType: Int
  def fingerPrint: String
  def mobileTicket: String
  def smsType: Int
  def resend: Boolean
  def lastChannel: Int

  def _passthroughFields: immutable$Map[Short, TFieldBlob] = immutable$Map.empty

  def _1 = ip
  def _2 = deviceType
  def _3 = fingerPrint
  def _4 = mobileTicket
  def _5 = smsType
  def _6 = resend
  def _7 = lastChannel


  /**
   * Gets a field value encoded as a binary blob using TCompactProtocol.  If the specified field
   * is present in the passthrough map, that value is returned.  Otherwise, if the specified field
   * is known and not optional and set to None, then the field is serialized and returned.
   */
  def getFieldBlob(_fieldId: Short): _root_.scala.Option[TFieldBlob] = {
    lazy val _buff = new TMemoryBuffer(32)
    lazy val _oprot = new TCompactProtocol(_buff)
    _passthroughFields.get(_fieldId) match {
      case blob: _root_.scala.Some[TFieldBlob] => blob
      case _root_.scala.None => {
        val _fieldOpt: _root_.scala.Option[TField] =
          _fieldId match {
            case 1 =>
              if (true) {
                writeIpValue(ip, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.IpField)
              } else {
                _root_.scala.None
              }
            case 2 =>
              if (true) {
                writeDeviceTypeValue(deviceType, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.DeviceTypeField)
              } else {
                _root_.scala.None
              }
            case 3 =>
              if (fingerPrint ne null) {
                writeFingerPrintValue(fingerPrint, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.FingerPrintField)
              } else {
                _root_.scala.None
              }
            case 4 =>
              if (mobileTicket ne null) {
                writeMobileTicketValue(mobileTicket, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.MobileTicketField)
              } else {
                _root_.scala.None
              }
            case 5 =>
              if (true) {
                writeSmsTypeValue(smsType, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.SmsTypeField)
              } else {
                _root_.scala.None
              }
            case 6 =>
              if (true) {
                writeResendValue(resend, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.ResendField)
              } else {
                _root_.scala.None
              }
            case 7 =>
              if (true) {
                writeLastChannelValue(lastChannel, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeRequest.LastChannelField)
              } else {
                _root_.scala.None
              }
            case _ => _root_.scala.None
          }
        _fieldOpt match {
          case _root_.scala.Some(_field) =>
            val _data = Arrays.copyOfRange(_buff.getArray, 0, _buff.length)
            _root_.scala.Some(TFieldBlob(_field, _data))
          case _root_.scala.None =>
            _root_.scala.None
        }
      }
    }
  }

  /**
   * Collects TCompactProtocol-encoded field values according to `getFieldBlob` into a map.
   */
  def getFieldBlobs(ids: TraversableOnce[Short]): immutable$Map[Short, TFieldBlob] =
    (ids flatMap { id => getFieldBlob(id) map { id -> _ } }).toMap

  /**
   * Sets a field using a TCompactProtocol-encoded binary blob.  If the field is a known
   * field, the blob is decoded and the field is set to the decoded value.  If the field
   * is unknown and passthrough fields are enabled, then the blob will be stored in
   * _passthroughFields.
   */
  def setField(_blob: TFieldBlob): SendLoginVerificationCodeRequest = {
    var ip: Long = this.ip
    var deviceType: Int = this.deviceType
    var fingerPrint: String = this.fingerPrint
    var mobileTicket: String = this.mobileTicket
    var smsType: Int = this.smsType
    var resend: Boolean = this.resend
    var lastChannel: Int = this.lastChannel
    var _passthroughFields = this._passthroughFields
    _blob.id match {
      case 1 =>
        ip = readIpValue(_blob.read)
      case 2 =>
        deviceType = readDeviceTypeValue(_blob.read)
      case 3 =>
        fingerPrint = readFingerPrintValue(_blob.read)
      case 4 =>
        mobileTicket = readMobileTicketValue(_blob.read)
      case 5 =>
        smsType = readSmsTypeValue(_blob.read)
      case 6 =>
        resend = readResendValue(_blob.read)
      case 7 =>
        lastChannel = readLastChannelValue(_blob.read)
      case _ => _passthroughFields += (_blob.id -> _blob)
    }
    new Immutable(
      ip,
      deviceType,
      fingerPrint,
      mobileTicket,
      smsType,
      resend,
      lastChannel,
      _passthroughFields
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetField(_fieldId: Short): SendLoginVerificationCodeRequest = {
    var ip: Long = this.ip
    var deviceType: Int = this.deviceType
    var fingerPrint: String = this.fingerPrint
    var mobileTicket: String = this.mobileTicket
    var smsType: Int = this.smsType
    var resend: Boolean = this.resend
    var lastChannel: Int = this.lastChannel

    _fieldId match {
      case 1 =>
        ip = 0L
      case 2 =>
        deviceType = 0
      case 3 =>
        fingerPrint = ""
      case 4 =>
        mobileTicket = ""
      case 5 =>
        smsType = 0
      case 6 =>
        resend = false
      case 7 =>
        lastChannel = 0
      case _ =>
    }
    new Immutable(
      ip,
      deviceType,
      fingerPrint,
      mobileTicket,
      smsType,
      resend,
      lastChannel,
      _passthroughFields - _fieldId
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetIp: SendLoginVerificationCodeRequest = unsetField(1)

  def unsetDeviceType: SendLoginVerificationCodeRequest = unsetField(2)

  def unsetFingerPrint: SendLoginVerificationCodeRequest = unsetField(3)

  def unsetMobileTicket: SendLoginVerificationCodeRequest = unsetField(4)

  def unsetSmsType: SendLoginVerificationCodeRequest = unsetField(5)

  def unsetResend: SendLoginVerificationCodeRequest = unsetField(6)

  def unsetLastChannel: SendLoginVerificationCodeRequest = unsetField(7)


  override def write(_oprot: TProtocol): Unit = {
    SendLoginVerificationCodeRequest.validate(this)
    _oprot.writeStructBegin(Struct)
    writeIpField(ip, _oprot)
    writeDeviceTypeField(deviceType, _oprot)
    if (fingerPrint ne null) writeFingerPrintField(fingerPrint, _oprot)
    if (mobileTicket ne null) writeMobileTicketField(mobileTicket, _oprot)
    writeSmsTypeField(smsType, _oprot)
    writeResendField(resend, _oprot)
    writeLastChannelField(lastChannel, _oprot)
    if (_passthroughFields.nonEmpty) {
      _passthroughFields.values.foreach { _.write(_oprot) }
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    ip: Long = this.ip,
    deviceType: Int = this.deviceType,
    fingerPrint: String = this.fingerPrint,
    mobileTicket: String = this.mobileTicket,
    smsType: Int = this.smsType,
    resend: Boolean = this.resend,
    lastChannel: Int = this.lastChannel,
    _passthroughFields: immutable$Map[Short, TFieldBlob] = this._passthroughFields
  ): SendLoginVerificationCodeRequest =
    new Immutable(
      ip,
      deviceType,
      fingerPrint,
      mobileTicket,
      smsType,
      resend,
      lastChannel,
      _passthroughFields
    )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[SendLoginVerificationCodeRequest]

  override def equals(other: Any): Boolean =
    canEqual(other) &&
      _root_.scala.runtime.ScalaRunTime._equals(this, other) &&
      _passthroughFields == other.asInstanceOf[SendLoginVerificationCodeRequest]._passthroughFields

  override def hashCode: Int = _root_.scala.runtime.ScalaRunTime._hashCode(this)

  override def toString: String = _root_.scala.runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 7

  override def productElement(n: Int): Any = n match {
    case 0 => this.ip
    case 1 => this.deviceType
    case 2 => this.fingerPrint
    case 3 => this.mobileTicket
    case 4 => this.smsType
    case 5 => this.resend
    case 6 => this.lastChannel
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "SendLoginVerificationCodeRequest"
}