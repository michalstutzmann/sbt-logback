libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"
libraryDependencies += "org.scala-sbt" % "scripted-plugin" % sbtVersion.value
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.10")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.6")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0-M1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
