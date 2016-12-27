lazy val commonSettings = Seq(
  javacOptions ++= Seq("-encoding", "UTF-8"),
  organization := "com.lawsofnature.sms",
  version := "1.0",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "net.codingwell" % "scala-guice_2.11" % "4.1.0",
    "com.lawsofnature.common" % "common-ice_2.11" % "1.0",
    "org.scala-lang" % "scala-library" % "2.11.8"
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
    "com.lawsofnature.sms" % "smsclient_2.11" % "1.0"
  )
)