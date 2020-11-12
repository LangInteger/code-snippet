package com.langinteger.utils.api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

object CommonRejectionHandler {

  implicit def myRejectionHandler = {

    RejectionHandler.newBuilder()
      .handle { case MissingCookieRejection(cookieName) =>
        complete(HttpResponse(BadRequest, entity = "No cookies, no service!!!"))
      }
      .handle { case AuthorizationFailedRejection =>
        complete((Forbidden, "You're out of your depth!"))
      }
      .handle { case ValidationRejection(msg, _) =>
        complete((InternalServerError, "That wasn't valid! " + msg))
      }
      .handle { case MalformedRequestContentRejection(msg, _) =>
          complete(HttpEntity(ContentTypes.`application/json`, ResponseWrapper.errorResponse(97, msg)))

      }
      .handleAll[MethodRejection] { methodRejections =>
      val names = methodRejections.map(_.supported.name)
      complete((MethodNotAllowed, s"Can't do that! Supported: ${names mkString " or "}!"))
    }
      .handleNotFound {
        complete((NotFound, "Not here!"))
      }
      .result()
  }
}
