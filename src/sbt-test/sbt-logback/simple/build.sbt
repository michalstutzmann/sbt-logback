lazy val root = (project in file(".")).
  enablePlugins(LogbackPlugin).
  settings(
    name := "Main",
    version := "0.0.0"
  )