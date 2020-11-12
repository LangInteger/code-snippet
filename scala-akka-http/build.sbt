import scala.sys.process.Process

name := "akka-http"

version := "0.1"

libraryDependencies ++= {
  val akkaVersion = "2.5.23"
  val httpVersion = "10.1.8"
  Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % httpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % httpVersion,
    
    // For testing
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % httpVersion % Test,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,

    // Using slf4j with logback as backend
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",

    // Using jackson for (un)marshalling
    "de.heikoseeberger" %% "akka-http-jackson" % "1.27.0"

  )
}

// 获取当前 git commit sha1 值的任务
val gitHeadCommitSha = taskKey[String]("Determines the current git commit SHA")
gitHeadCommitSha := Process("git log --oneline").lineStream.head

// 写当前 git commit sha1 值到文件的任务
val makeVersionProperties = taskKey[Seq[File]]("Makes a version.properties file")
makeVersionProperties := {
  val propFile = new File((resourceManaged in Compile).value, "version.properties")
  val content = "version=%s" format gitHeadCommitSha.value
  IO.write(propFile, content)
  Seq(propFile)
}

//将 version.properties 文件放入 runtime Classpath path
resourceGenerators in Compile += makeVersionProperties

//将文件 ./LICENSE 在打包时放到 JAR 包中的顶层目录 
mappings in packageBin in Compile += (baseDirectory.value / "LICENSE") -> "AKKA_HTTP_DEMO_LICENSE"