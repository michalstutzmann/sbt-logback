lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin, ScalafmtPlugin)
  .settings(
    name := "sbt-logback",
    organization := "com.github.mwegrz",
    versionScheme := Some("early-semver"),
    crossScalaVersions := Seq(scalaVersion.value, "2.12.21"),
    pluginCrossBuild / sbtVersion := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.5.8"
        case _      => (pluginCrossBuild / sbtVersion).value
      }
    },
    scriptedSbt := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.13.0"
        case _      => scriptedSbt.value
      }
    },
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-unchecked"
    ),
    scalafmtOnCompile := true,
    scriptedLaunchOpts ++= Seq("-Dplugin.version=" + version.value),
    scriptedBufferLog := false,
    // Publish settings
    publishTo := localStaging.value,
    credentials ++= (for {
      username <- sys.env.get("SONATYPE_CENTRAL_USERNAME")
      password <- sys.env.get("SONATYPE_CENTRAL_PASSWORD")
    } yield Credentials("Sonatype Nexus Repository Manager", "central.sonatype.com", username, password)).toSeq,
    publishMavenStyle := true,
    Test / publishArtifact := false,
    pomIncludeRepository := { _ => false },
    licenses := Seq("Apache License, Version 2.0" -> uri("https://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(uri("https://github.com/michalstutzmann/sbt-logback")),
    scmInfo := Some(
      ScmInfo(
        uri("https://github.com/michalstutzmann/sbt-logback.git"),
        "scm:git:git@github.com:michalstutzmann/sbt-logback.git"
      )
    ),
    developers := List(
      Developer(
        id = "michalstutzmann",
        name = "Michal Stutzmann",
        email = null,
        url = uri("https://github.com/michalstutzmann")
      )
    )
  )
