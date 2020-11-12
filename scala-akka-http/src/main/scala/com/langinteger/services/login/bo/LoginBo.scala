package com.langinteger.services.login.bo

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, JsonFormat, RootJsonFormat}

case class LoginBo(username: String, password: String) {
}

trait LoginBoJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val loginBoFormat: RootJsonFormat[LoginBo] = jsonFormat2(LoginBo)
}
