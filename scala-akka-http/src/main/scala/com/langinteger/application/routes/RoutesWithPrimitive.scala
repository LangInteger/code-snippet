package com.langinteger.application.routes

import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.Route

case class RoutesWithPrimitive() {

  val helloWorldWithPrimitive: Route =
    ctx => ctx.complete("42")

  val helloWorldWithPrimitive1: Route =
    _.complete("42")

  val route: Route = { ctx =>
    if (ctx.request.method == HttpMethods.GET)
      ctx.complete("Received GET")
    else
      ctx.complete("Received something else")
  }

}
