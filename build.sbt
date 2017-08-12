import ReleaseTransformations._

lazy val root = (project in file(".")).
  enablePlugins(ReleasePlugin, ScalafmtPlugin).
  settings(
    name := "sbt-logback",
    organization := "com.github.mwegrz",
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-unchecked"
    ),
    sbtPlugin := true,
    scalaVersion := "2.12.3",
    sbtVersion in Global := "1.0.0",
    scalaCompilerBridgeSource := {
      val sv = appConfiguration.value.provider.id.version
      ("org.scala-sbt" % "compiler-interface" % sv % "component").sources
    },
    // Release settings
    releaseTagName := { (version in ThisBuild).value },
    releaseTagComment := s"Release version ${(version in ThisBuild).value}",
    releaseCommitMessage := s"Set version to ${(version in ThisBuild).value}",
    releaseCrossBuild := false,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      releaseStepCommandAndRemaining("publishSigned"),
      setNextVersion,
      commitNextVersion,
      releaseStepCommandAndRemaining("sonatypeReleaseAll"),
      pushChanges
    ),
    useGpg := true,
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    // Publish settings
    crossPaths := false,
    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    licenses := Seq("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("http://github.com/mwegrz/sbt-logback")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/mwegrz/sbt-logback.git"),
        "scm:git@github.com:mwegrz/sbt-logback.git"
      )
    ),
    developers := List(
      Developer(
        id = "mwegrz",
        name = "Michał Węgrzyn",
        email = null,
        url = url("http://github.com/mwegrz")
      )
    )
  )
