# sbt-logback

An sbt plugin that removes all logging libraries except SLF4J from `libraryDependencies`, then adds SLF4J bridging and Logback.

## What it does

When you enable `LogbackPlugin` on a project, it:

- Adds these dependencies (forced to the versions you configure):
  - `org.slf4j:slf4j-api`
  - `org.slf4j:log4j-over-slf4j`
  - `org.slf4j:jcl-over-slf4j`
  - `org.slf4j:jul-to-slf4j`
  - `ch.qos.logback:logback-classic`
- Excludes competing logging libraries from all dependencies, including transitive ones:
  - `commons-logging:commons-logging`
  - `log4j:log4j`
  - `org.slf4j:slf4j-log4j12`
  - `org.slf4j:slf4j-jcl`
  - `org.slf4j:slf4j-jul`

Any code that logs through Log4j 1.x, Jakarta Commons Logging or `java.util.logging` is then routed through SLF4J to Logback.

## Requirements

- sbt 2.x (Scala 3)
- sbt 1.5.8+ (Scala 2.12)

## Usage

Add the plugin to `project/plugins.sbt`:

```scala
addSbtPlugin("com.github.mwegrz" % "sbt-logback" % "0.2.0")
```

Enable it in `build.sbt` and set the SLF4J and Logback versions. Neither setting has a default, so you must set both:

```scala
lazy val root = (project in file("."))
  .enablePlugins(LogbackPlugin)
  .settings(
    slf4jVersion := "2.0.17",
    logbackVersion := "1.5.18"
  )
```

`jul-to-slf4j` is only put on the classpath. To route `java.util.logging` through it, install the bridge at application startup:

```scala
org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger()
org.slf4j.bridge.SLF4JBridgeHandler.install()
```

## Settings

| Key              | Type     | Description                                                                   |
|------------------|----------|-------------------------------------------------------------------------------|
| `slf4jVersion`   | `String` | Version of `slf4j-api`, `log4j-over-slf4j`, `jcl-over-slf4j`, `jul-to-slf4j` |
| `logbackVersion` | `String` | Version of `logback-classic`                                                  |

## Development

Run the scripted tests against both sbt 2 and sbt 1:

```sh
sbt +scripted
```

## Releasing

The GitHub Actions workflow publishes releases to Maven Central. It runs the `publish` script, which uses [git-semver-release](https://github.com/michalstutzmann/setup-git-semver-release) to work out the version. When the workflow builds a release tag, it runs `+scripted`, `+publishSigned` and `sonaRelease`, then creates a GitHub release. Any other build runs only the scripted tests.

## License

[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)
