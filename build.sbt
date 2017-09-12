name := "play-pjax"

organization := "com.thatscalaguy"

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.12.3","2.11.11")

val PlayVersion = "2.6.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion % Provided,
  "com.typesafe.play" %% "play-test" % PlayVersion % Provided,
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1",
  "org.scalactic" %% "scalactic" % "3.0.3" excludeAll(
    ExclusionRule(name = "scala-reflect"), ExclusionRule(name = "scala-library")
  ),
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.1" % "test"
)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

coverageExcludedPackages := "com.thatscalaguy.play.filters.PjaxFilterModule;com.thatscalaguy.utils.HTML"
    