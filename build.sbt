lazy val commonSettings = Seq(
  javacOptions ++= Seq("-encoding", "UTF-8"),
  organization := "com.lawsofnature.sms",
  version := "1.0",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "net.codingwell" % "scala-guice_2.11" % "4.1.0",
    "com.lawsofnature.common" % "common-ice_2.11" % "1.0",
    "com.lawsofnature.common" % "common-error_2.11" % "1.0",
    "com.lawsofnature.common" % "common-utils_2.11" % "1.0",
    "org.scala-lang" % "scala-library" % "2.11.8"
  )
)

lazy val smscommon = (project in file("smscommon")).settings(commonSettings: _*).settings(
  name := """smscommon""",
  libraryDependencies ++= Seq(
    "org.apache.httpcomponents" % "httpclient" % "4.5.2",
    "com.trueaccord.scalapb" % "scalapb-runtime_2.11" % "0.5.46",
    "dom4j" % "dom4j" % "1.6.1"
  )
)

lazy val smsclient = (project in file("smsclient")).settings(commonSettings: _*).settings(
  name := """smsclient""",
  libraryDependencies ++= Seq(
  )
)

lazy val smsserver = (project in file("smsserver")).settings(commonSettings: _*).settings(
  name := """smsserver""",
  libraryDependencies ++= Seq(
    "com.lawsofnature.sms" % "smsclient_2.11" % "1.0",
    "com.lawsofnature.sms" % "smscommon_2.11" % "1.0",
    "com.lawsofnature.common" % "common-mysql_2.11" % "1.0",
    "com.lawsofnature.edcenter" % "edclient_2.11" % "1.0",
    "com.lawsofnature.common" % "common-rabbitmq_2.11" % "1.0",
    "com.lawsofnature.member" % "memberclient_2.11" % "1.0-SNAPSHOT",
    "org.scalaj" % "scalaj-http_2.12" % "2.3.0",
    "com.typesafe.slick" % "slick-codegen_2.11" % "3.2.0-M2" % "test"
  )
)