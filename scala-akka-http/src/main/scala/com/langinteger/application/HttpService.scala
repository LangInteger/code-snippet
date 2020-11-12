package com.langinteger.application

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.langinteger.application.routes._
import com.langinteger.services.log.LogService

/**
  * The HttpService will receive the other service and then it will init all the routers.
  * The server always returns a json formatted string as http response body with follow shape:
  *
  * {
  * **code: 0
  * **message: ok
  * **data: {
  * ****xx: xx
  * **}
  * }
  *
  * and the response body marshalling process is done via the ResponseWrapper.dataResponse method in every route.
  * Exception and rejection situations should also build their own formatted response in their scope.
  */
case class HttpService(logService: LogService) {

  // Routers
  private val logRouter = LogServiceRoute(logService)
  private val helloWorldWithDslRoute = HelloWorldWithDslRoute()
  private val helloWorldWithPlainRoute = HelloWorldWithPlainRoute()
  private val versionRoute = VersionRoute()
  private val errorRoute = ErrorRoute()
  private val loginRoute = LoginServiceRoute()

  val routes: Route = {
    pathPrefix("v1") {
      logRouter.routes ~
        helloWorldWithDslRoute.helloWorldWithDslRoute ~
        helloWorldWithPlainRoute.helloWorldWithPlainRoute ~
        versionRoute.versionRoute ~
        errorRoute.errorRoute ~
        loginRoute.routeLogin
    }
  }
}
