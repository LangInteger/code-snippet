package com.langinteger.utils.api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import com.langinteger.utils.JsonUtils

object ResponseWrapper extends ResponseWrapper

trait ResponseWrapper {

  def plainTextResponse(text: String): HttpResponse =
    HttpResponse(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, text))


  def dataResponse[A](result: A): String = {
    JsonUtils.toJson(DataResponse[A](CommonCode.SUCCESS_CODE, CommonCode.SUCCESS_MESSAGE, result))
  }

  def errorResponse(code: Int, message: String): String = {
    JsonUtils.toJson(DataResponse[Null](code, message))
  }
}

