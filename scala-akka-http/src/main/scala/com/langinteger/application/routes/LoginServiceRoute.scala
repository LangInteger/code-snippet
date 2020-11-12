package com.langinteger.application.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.langinteger.services.login.bo.LoginBo
import com.langinteger.services.login.bo.LoginBoJsonSupport
import com.langinteger.utils.api.ResponseWrapper


case class LoginServiceRoute() extends LoginBoJsonSupport {

  val routeLogin: Route =
    path("login") {
      post {
        entity(as[LoginBo]) {
          loginBo =>
            complete {
              loginBo match {
                case LoginBo("root", "root") => HttpEntity(ContentTypes.`application/json`,
                  ResponseWrapper.dataResponse(null))
                case _ => HttpEntity(ContentTypes.`application/json`,
                  ResponseWrapper.errorResponse(1, "username or password not correct"))
              }
            }
        }
      }
    }

}