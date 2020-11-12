package com.langinteger.application.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.langinteger.utils.api.ResponseWrapper

case class HelloWorldWithDslRoute() {

  val helloWorldWithDslRoute: Route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`application/json`, ResponseWrapper.dataResponse(0, "Hello world")))
      }
    }
}
