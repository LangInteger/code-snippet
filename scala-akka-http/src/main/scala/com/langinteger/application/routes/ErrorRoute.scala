package com.langinteger.application.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.langinteger.utils.exception.ApplicationException

case class ErrorRoute() {

  val errorRoute: Route =
    path("applicationException") {
      get {
        complete {
          throw ApplicationException(2, "ApplicationException happens on purpose")
        }
      }
    } ~
      path("runtimeException") {
        get {
          complete {
            throw new RuntimeException("runtimeException happens on purpose")
          }
        }
      }

}