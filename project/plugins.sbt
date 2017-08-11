libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"
libraryDependencies <+= sbtVersion(v => "org.scala-sbt" % "scripted-plugin" % v)
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.6")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.6")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0-M1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
