package com.langinteger.application.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.langinteger.services.log.LogService
import com.langinteger.utils.api.ResponseWrapper

case class LogServiceRoute(logService: LogService) {

  val routes: Route = pathPrefix("log") {
    routeLog //~ route2
  }

  private val routeLog: Route =
    path("recordLog") {
      post {
        entity(as[String]) {
          param =>
            complete {
              logService.log(param)
              HttpEntity(ContentTypes.`application/json`, ResponseWrapper.dataResponse(null).toString)
            }
        }
      }
    }

}