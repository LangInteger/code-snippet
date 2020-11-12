package com.langinteger.utils.exception

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server._
import com.langinteger.utils.api.ResponseWrapper

object CommonExceptionHandler {

  implicit val exceptionHandler: ExceptionHandler = ExceptionHandler {

    case e: ApplicationException => ctx => {
      ctx.log.error(e, e.message)
      ctx.request.discardEntityBytes(ctx.materializer)
      ctx.complete(HttpEntity(ContentTypes.`application/json`, ResponseWrapper.errorResponse(e.code, e.message)))
    }

    case e: RuntimeException => ctx => {
      ctx.log.error(e, e.getMessage)
      ctx.request.discardEntityBytes(ctx.materializer)
      ctx.complete(HttpEntity(ContentTypes.`application/json`, ResponseWrapper.errorResponse(99, e.getMessage)))
    }
  }
}
