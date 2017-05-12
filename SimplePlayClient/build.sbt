name := "SimplePlayClient"

version := "1.0"

lazy val `simpleplayclient` = (project in file(".")).enablePlugins(PlayJava, PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies += "com.typesafe.akka" % "akka-remote_2.11" % "2.5.1"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

//resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("releases")