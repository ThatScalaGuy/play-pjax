name := "play-pjax"

organization := "com.thatscalaguy"

scalaVersion := "2.11.8"

val PlayVersion = "2.5.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion % Provided,
  "com.typesafe.play" %% "play-test" % PlayVersion % Provided,
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test"
)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

coverageExcludedPackages := "com.thatscalaguy.play.filters.PjaxFilterModule;com.thatscalaguy.utils.HTML"
    