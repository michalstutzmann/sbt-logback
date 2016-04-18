package com.github.mwegrz.sbtlogback

import sbt.Keys._
import sbt._
import scala.language.implicitConversions

/**
 * Cleans up `libraryDependencies` from all logging libraries (except SLF4J) and adds SLF4J bridging and Logback
 */
object LogbackPlugin extends AutoPlugin {
  object autoImport {
    lazy val slf4jVersion = settingKey[String]("SLF4J version")
    lazy val logbackVersion = settingKey[String]("Logback version")
  }

  import LogbackPlugin.autoImport._

  override lazy val projectSettings = Seq(
    // Use SLF4J, SLF4J bridging
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % slf4jVersion.value force (),
      "org.slf4j" % "log4j-over-slf4j" % slf4jVersion.value force (),
      "org.slf4j" % "jcl-over-slf4j" % slf4jVersion.value force (),
      "org.slf4j" % "jul-to-slf4j" % slf4jVersion.value force (),
      "ch.qos.logback" % "logback-classic" % logbackVersion.value force ()
    ),
    // Exclude all logging frameworks (except SLF4J and Logback) from transitive dependencies
    excludeDependencies ++= Seq(
      "commons-logging" % "commons-logging",
      "log4j" % "log4j",
      "org.slf4j" % "slf4j-log4j12",
      "org.slf4j" % "slf4j-jcl",
      "org.slf4j" % "slf4j-jul"
    )
  )
}
