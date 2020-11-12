package com.langinteger.application.routes

import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse, Uri}
import com.langinteger.Main._

case class HelloWorldWithPatternMatchingRoute() {

  // Using with Http().bindAndHandleSync, accept a HttpRequest ⇒ HttpResponse as router params

  val requestHandler: HttpRequest => HttpResponse = {
    case HttpRequest(HttpMethods.GET, Uri.Path("/hello2"), _, _, _) =>
      HttpResponse(entity = "Hello World! 2")

    case request: HttpRequest =>
      request.discardEntityBytes()
      HttpResponse(404, entity = "Unknown resource!")
  }
}
