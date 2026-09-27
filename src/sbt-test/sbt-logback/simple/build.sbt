lazy val root = (project in file("."))
  .enablePlugins(LogbackPlugin)
  .settings(
    name := "Main",
    version := "0.0.0",
    slf4jVersion := "2.0.17",
    logbackVersion := "1.5.18"
  )
