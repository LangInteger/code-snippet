name := """scala-code-gen"""

version := "0.0.1"

scalaVersion := "2.13.1"


libraryDependencies ++= {
  val slickVersion = "3.3.2"
  val mysqlConnectorVersion = "8.0.13"
  val poiScalaVersion = "0.19"
  val caffeineVersion = "0.28.0"
  val scalaTestPlusPlay = "4.0.3"
  val scalaCheckVersion = "1.14.1"

  Seq(
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-codegen" % slickVersion,
    "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
    "mysql" % "mysql-connector-java" % mysqlConnectorVersion,
    // for excel write
    "info.folone" %% "poi-scala" % poiScalaVersion,
    // for cache
    "com.github.cb372" %% "scalacache-caffeine" % caffeineVersion,
    "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusPlay % Test,
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test"
  )
}
