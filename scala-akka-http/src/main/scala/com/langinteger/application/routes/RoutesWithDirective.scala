package com.langinteger.application.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

case class RoutesWithDirective() {

  val helloWorldWithDirective: Route =
    complete("42")

  val route: Route =
    path("hello") {

      get {
        complete("Received GET")
      } ~
        complete("Received something else")
    }

}
