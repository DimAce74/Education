name := "SimplePlayApp"

version := "1.0"

lazy val `simpleplayapp` = (project in file(".")).enablePlugins(PlayJava, PlayEbean, PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc ,
  cache ,
  ws   ,
  specs2 % Test,
  "be.objectify"  %% "deadbolt-java"     % "2.5.4",
  "org.postgresql" % "postgresql" % "42.0.0",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.5.1"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

//resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("releases")