name := """DailyPlay"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc" % "3.4.0",
  "org.scalikejdbc" %% "scalikejdbc-test"   % "3.4.0"   % "test",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.4.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.4",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "mysql" % "mysql-connector-java" % "5.1.29",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

enablePlugins(ScalikejdbcPlugin)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
