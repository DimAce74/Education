name := "SimplePlayApp"

version := "1.0"

lazy val `simpleplayapp` = (project in file(".")).enablePlugins(PlayJava, PlayEbean, PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc ,
  cache ,
  ws   ,
  specs2 % Test,
  "be.objectify"  %% "deadbolt-java"     % "2.5.0",
  "com.feth"      %% "play-authenticate" % "0.8.3",
  "org.postgresql" % "postgresql" % "42.0.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

//resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("releases")