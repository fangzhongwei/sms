lazy val commonSettings = Seq(
  javacOptions ++= Seq("-encoding", "UTF-8"),
  organization := "com.jxjxgo.sms",
  version := "1.0",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "net.codingwell" % "scala-guice_2.11" % "4.1.0",
    "com.jxjxgo.common" % "common-error_2.11" % "1.0",
    "com.jxjxgo.common" % "common-utils_2.11" % "1.0",
    "com.jxjxgo.common" % "common-finagle-thrift_2.11" % "1.0",
    "org.scala-lang" % "scala-library" % "2.11.8"
  )
)

lazy val smscommon = (project in file("smscommon")).settings(commonSettings: _*).settings(
  name := """smscommon""",
  libraryDependencies ++= Seq(
    "com.trueaccord.scalapb" % "scalapb-runtime_2.11" % "0.5.46"
  )
)

lazy val smsserver = (project in file("smsserver")).settings(commonSettings: _*).settings(
  name := """smsserver""",
  mainClass in (Compile, run) := Some("com.lawsofnatrue.sms.server.SmsApplicationServer"),
  libraryDependencies ++= Seq(
    "com.jxjxgo.sms" % "smscommon_2.11" % "1.0",
    "com.jxjxgo.common" % "common-db_2.11" % "1.0",
    "com.typesafe.akka" %% "akka-actor" % "2.4.14",
    "com.typesafe.akka" %% "akka-stream" % "2.4.14",
    "com.typesafe.akka" % "akka-http_2.11" % "10.0.0",
    "com.jxjxgo.edcenter" % "edclient_2.11" % "1.0",
    "com.jxjxgo.common" % "common-kafka_2.11" % "1.0",
    "com.jxjxgo.member" % "membercommonlib_2.11" % "1.0",
    "org.scalaj" % "scalaj-http_2.12" % "2.3.0",
    "org.scala-lang" % "scala-xml" % "2.11.0-M4",
    "com.typesafe.slick" % "slick-codegen_2.11" % "3.2.0-M2" % "test"
  )
)