lazy val root = (project in file(".")).
  settings(ScriptedPlugin.scriptedSettings: _*).
  enablePlugins(GitBranchPrompt, ReleasePlugin, SbtScalariform, SbtPgp).
  settings(
    sbtPlugin := true,
    name := "sbt-logback",
    organization := "com.github.mwegrz",
    scalaVersion := "2.10.6",
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-unchecked"
    ),
    scriptedLaunchOpts ++= Seq(
      "-Dplugin.version=" + (version in ThisBuild).value,
      "-Djava.io.tmpdir=" + target.value
    ),
    scriptedBufferLog := false,
    // Publishing
    publishMavenStyle := true,
    crossPaths := true,
    autoScalaLibrary := false,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    pomExtra := (
      <url>http://github.com/mwegrz/sbt-logback</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>https://opensource.org/licenses/MIT</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:mwegrz/sbt-logback.git</url>
          <connection>scm:git:git@github.com:mwegrz/sbt-logback.git</connection>
        </scm>
        <developers>
          <developer>
            <id>mwegrz</id>
            <name>Michał Węgrzyn</name>
            <url>http://github.com/mwegrz</url>
          </developer>
        </developers>),
    releaseTagComment := s"Released ${(version in ThisBuild).value}",
    releaseCommitMessage := s"Set version to ${(version in ThisBuild).value}"
  )
