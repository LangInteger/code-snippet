package $organization$.$artifact$.infrastructure

import $organization$.$artifact$.domain.UserRegistryActor._
import $organization$.$artifact$.domain.User
import $organization$.$artifact$.domain.Users

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol


trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._

  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)

  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
