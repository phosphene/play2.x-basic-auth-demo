import scalariform.formatter.preferences._

name := """my-play-app"""

version := "0.01-snapshot"

scalaVersion := "2.11.7"

resolvers := ("Atlassian Releases" at "https://maven.atlassian.com/public/") +: resolvers.value

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.virtuslab" %% "unicorn-play" % "1.0.0",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "net.codingwell" %% "scala-guice" % "4.0.0",
  "net.ceedubs" %% "ficus" % "1.1.2",
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24",
  specs2 % Test,
  cache,
  filters
)

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"       % "2.3.4",
  "com.h2database"  %  "h2"                % "1.4.190",
  "ch.qos.logback"  %  "logback-classic"   % "1.1.3",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "2.3.4",
  "org.scalikejdbc"   %% "scalikejdbc-play-initializer"      % "2.4.3",
  "org.scalikejdbc"   %% "scalikejdbc-play-dbapi-adapter"    % "2.4.3",
  "org.scalikejdbc"   %% "scalikejdbc-play-fixture"          % "2.4.3",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.3.4",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.4.3"
)


libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M4" % "test",
  "org.flywaydb"      %% "flyway-play" % "2.0.1",
  "jp.t2v" %% "play2-auth"        % "0.14.1",
  "jp.t2v" %% "play2-auth-test"   % "0.14.1" % "test",
  play.sbt.Play.autoImport.cache // only when you use default IdContainer

)

libraryDependencies += "com.codeborne" % "phantomjsdriver" % "1.2.1" % "test"
//libraryDependencies += "org.yaml" % "snakeyaml" % "1.16" % "test"
//resolvers += "justwrote" at "http://repo.justwrote.it/snapshots/"

//libraryDependencies += "it.justwrote" %% "scala-faker" % "0.4-SNAPSHOT" % "test"


// Enable the plugins
lazy val root = (project in file(".")).enablePlugins(PlayScala, PhantomJs)

javaOptions in Test ++= PhantomJs.setup(baseDirectory.value)


      TwirlKeys.templateImports ++= Seq(
        "play.api.data.Form",
        "play.api.mvc.Flash",
        "views._",
        "views.html.helper",
        "controllers._",
        "model._"

      )



routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)

//********************************************************
// Scalariform settings
//********************************************************

defaultScalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(PreserveDanglingCloseParenthesis, true)
