name := "SimplePlayApp"

version := "1.0"

lazy val `simpleplayapp` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies += "org.postgresql" % "postgresql" % "42.0.0"

libraryDependencies += javaJpa

libraryDependencies += "org.apache.openjpa" % "openjpa" % "2.4.2"


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  