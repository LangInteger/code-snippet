name := "$name$"

version := "0.1"

libraryDependencies ++= {
  val akkaVersion = "2.5.23"
  val httpVersion = "10.1.8"
  Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % httpVersion,

    // For (un)marshalling
    "com.typesafe.akka" %% "akka-http-spray-json" % httpVersion,
    
    // For testing
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % httpVersion % Test,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,

    // Using slf4j with logback as backend
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",

  )
}