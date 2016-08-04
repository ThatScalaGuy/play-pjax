name := "play-pjax"

organization := "com.thatscalaguy"

scalaVersion := "2.11.8"

val PlayVersion = "2.5.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion % Provided,
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"
)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
    