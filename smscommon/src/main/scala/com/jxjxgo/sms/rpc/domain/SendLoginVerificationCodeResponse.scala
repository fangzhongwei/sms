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


object SendLoginVerificationCodeResponse extends ThriftStructCodec3[SendLoginVerificationCodeResponse] {
  private val NoPassthroughFields = immutable$Map.empty[Short, TFieldBlob]
  val Struct = new TStruct("SendLoginVerificationCodeResponse")
  val CodeField = new TField("code", TType.STRING, 1)
  val CodeFieldManifest = implicitly[Manifest[String]]
  val ChannelField = new TField("channel", TType.I32, 2)
  val ChannelFieldManifest = implicitly[Manifest[Int]]

  /**
   * Field information in declaration order.
   */
  lazy val fieldInfos: scala.List[ThriftStructFieldInfo] = scala.List[ThriftStructFieldInfo](
    new ThriftStructFieldInfo(
      CodeField,
      false,
      false,
      CodeFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      ChannelField,
      false,
      false,
      ChannelFieldManifest,
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
  def validate(_item: SendLoginVerificationCodeResponse): Unit = {
  }

  def withoutPassthroughFields(original: SendLoginVerificationCodeResponse): SendLoginVerificationCodeResponse =
    new Immutable(
      code =
        {
          val field = original.code
          field
        },
      channel =
        {
          val field = original.channel
          field
        }
    )

  override def encode(_item: SendLoginVerificationCodeResponse, _oproto: TProtocol): Unit = {
    _item.write(_oproto)
  }

  private[this] def lazyDecode(_iprot: LazyTProtocol): SendLoginVerificationCodeResponse = {

    var codeOffset: Int = -1
    var channel: Int = 0

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
              case TType.STRING =>
                codeOffset = _iprot.offsetSkipString
    
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'code' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 2 =>
            _field.`type` match {
              case TType.I32 =>
    
                channel = readChannelValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'channel' (expected=%s, actual=%s).".format(
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
      codeOffset,
      channel,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  override def decode(_iprot: TProtocol): SendLoginVerificationCodeResponse =
    _iprot match {
      case i: LazyTProtocol => lazyDecode(i)
      case i => eagerDecode(i)
    }

  private[this] def eagerDecode(_iprot: TProtocol): SendLoginVerificationCodeResponse = {
    var code: String = ""
    var channel: Int = 0
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
              case TType.STRING =>
                code = readCodeValue(_iprot)
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'code' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 2 =>
            _field.`type` match {
              case TType.I32 =>
                channel = readChannelValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I32
                throw new TProtocolException(
                  "Received wrong type for field 'channel' (expected=%s, actual=%s).".format(
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
      code,
      channel,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  def apply(
    code: String = "",
    channel: Int = 0
  ): SendLoginVerificationCodeResponse =
    new Immutable(
      code,
      channel
    )

  def unapply(_item: SendLoginVerificationCodeResponse): _root_.scala.Option[scala.Product2[String, Int]] = _root_.scala.Some(_item)


  @inline private def readCodeValue(_iprot: TProtocol): String = {
    _iprot.readString()
  }

  @inline private def writeCodeField(code_item: String, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(CodeField)
    writeCodeValue(code_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeCodeValue(code_item: String, _oprot: TProtocol): Unit = {
    _oprot.writeString(code_item)
  }

  @inline private def readChannelValue(_iprot: TProtocol): Int = {
    _iprot.readI32()
  }

  @inline private def writeChannelField(channel_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(ChannelField)
    writeChannelValue(channel_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeChannelValue(channel_item: Int, _oprot: TProtocol): Unit = {
    _oprot.writeI32(channel_item)
  }


  object Immutable extends ThriftStructCodec3[SendLoginVerificationCodeResponse] {
    override def encode(_item: SendLoginVerificationCodeResponse, _oproto: TProtocol): Unit = { _item.write(_oproto) }
    override def decode(_iprot: TProtocol): SendLoginVerificationCodeResponse = SendLoginVerificationCodeResponse.decode(_iprot)
    override lazy val metaData: ThriftStructMetaData[SendLoginVerificationCodeResponse] = SendLoginVerificationCodeResponse.metaData
  }

  /**
   * The default read-only implementation of SendLoginVerificationCodeResponse.  You typically should not need to
   * directly reference this class; instead, use the SendLoginVerificationCodeResponse.apply method to construct
   * new instances.
   */
  class Immutable(
      val code: String,
      val channel: Int,
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends SendLoginVerificationCodeResponse {
    def this(
      code: String = "",
      channel: Int = 0
    ) = this(
      code,
      channel,
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
      codeOffset: Int,
      val channel: Int,
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends SendLoginVerificationCodeResponse {

    override def write(_oprot: TProtocol): Unit = {
      _oprot match {
        case i: LazyTProtocol => i.writeRaw(_buf, _start_offset, _end_offset - _start_offset)
        case _ => super.write(_oprot)
      }
    }

    lazy val code: String =
      if (codeOffset == -1)
        ""
      else {
        _proto.decodeString(_buf, codeOffset)
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
   * This Proxy trait allows you to extend the SendLoginVerificationCodeResponse trait with additional state or
   * behavior and implement the read-only methods from SendLoginVerificationCodeResponse using an underlying
   * instance.
   */
  trait Proxy extends SendLoginVerificationCodeResponse {
    protected def _underlying_SendLoginVerificationCodeResponse: SendLoginVerificationCodeResponse
    override def code: String = _underlying_SendLoginVerificationCodeResponse.code
    override def channel: Int = _underlying_SendLoginVerificationCodeResponse.channel
    override def _passthroughFields = _underlying_SendLoginVerificationCodeResponse._passthroughFields
  }
}

trait SendLoginVerificationCodeResponse
  extends ThriftStruct
  with scala.Product2[String, Int]
  with java.io.Serializable
{
  import SendLoginVerificationCodeResponse._

  def code: String
  def channel: Int

  def _passthroughFields: immutable$Map[Short, TFieldBlob] = immutable$Map.empty

  def _1 = code
  def _2 = channel


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
              if (code ne null) {
                writeCodeValue(code, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeResponse.CodeField)
              } else {
                _root_.scala.None
              }
            case 2 =>
              if (true) {
                writeChannelValue(channel, _oprot)
                _root_.scala.Some(SendLoginVerificationCodeResponse.ChannelField)
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
  def setField(_blob: TFieldBlob): SendLoginVerificationCodeResponse = {
    var code: String = this.code
    var channel: Int = this.channel
    var _passthroughFields = this._passthroughFields
    _blob.id match {
      case 1 =>
        code = readCodeValue(_blob.read)
      case 2 =>
        channel = readChannelValue(_blob.read)
      case _ => _passthroughFields += (_blob.id -> _blob)
    }
    new Immutable(
      code,
      channel,
      _passthroughFields
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetField(_fieldId: Short): SendLoginVerificationCodeResponse = {
    var code: String = this.code
    var channel: Int = this.channel

    _fieldId match {
      case 1 =>
        code = ""
      case 2 =>
        channel = 0
      case _ =>
    }
    new Immutable(
      code,
      channel,
      _passthroughFields - _fieldId
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetCode: SendLoginVerificationCodeResponse = unsetField(1)

  def unsetChannel: SendLoginVerificationCodeResponse = unsetField(2)


  override def write(_oprot: TProtocol): Unit = {
    SendLoginVerificationCodeResponse.validate(this)
    _oprot.writeStructBegin(Struct)
    if (code ne null) writeCodeField(code, _oprot)
    writeChannelField(channel, _oprot)
    if (_passthroughFields.nonEmpty) {
      _passthroughFields.values.foreach { _.write(_oprot) }
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    code: String = this.code,
    channel: Int = this.channel,
    _passthroughFields: immutable$Map[Short, TFieldBlob] = this._passthroughFields
  ): SendLoginVerificationCodeResponse =
    new Immutable(
      code,
      channel,
      _passthroughFields
    )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[SendLoginVerificationCodeResponse]

  override def equals(other: Any): Boolean =
    canEqual(other) &&
      _root_.scala.runtime.ScalaRunTime._equals(this, other) &&
      _passthroughFields == other.asInstanceOf[SendLoginVerificationCodeResponse]._passthroughFields

  override def hashCode: Int = _root_.scala.runtime.ScalaRunTime._hashCode(this)

  override def toString: String = _root_.scala.runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 2

  override def productElement(n: Int): Any = n match {
    case 0 => this.code
    case 1 => this.channel
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "SendLoginVerificationCodeResponse"
}