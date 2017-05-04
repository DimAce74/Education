name := "SimplePlayApp"

version := "1.0"

lazy val `simpleplayapp` = (project in file(".")).enablePlugins(PlayJava, PlayEbean, PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies += "org.postgresql" % "postgresql" % "42.0.0"

libraryDependencies += "org.mybatis" % "mybatis" % "3.4.4"

libraryDependencies += "ws.securesocial" % "securesocial_2.11" % "3.0-M8"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("releases")