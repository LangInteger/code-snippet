package com.langinteger

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.langinteger.application.HttpService
import com.langinteger.services.log.LogService
import com.langinteger.utils.Configuration
import com.langinteger.utils.exception.CommonExceptionHandler.exceptionHandler
import com.langinteger.utils.api.CommonRejectionHandler.myRejectionHandler
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContextExecutor

object Main extends App with Configuration {

  lazy val LOG = LoggerFactory.getLogger(Main.getClass)

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executor: ExecutionContextExecutor = system.dispatcher

  val logService = LogService()
  val httpService = HttpService(logService)

  Http().bindAndHandle(httpService.routes, httpHost, httpPort)

}