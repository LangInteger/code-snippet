package com.langinteger.application.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive0, Route, StandardRoute}

case class HelloWorldWithPlainRoute() {

  val complete1: StandardRoute = complete("Say hello to akka-http")
  val route1: Route = get(complete1)
  val path1: Directive0 = path("hello1")
  val helloWorldWithPlainRoute: Route = path1.apply(route1)
}
