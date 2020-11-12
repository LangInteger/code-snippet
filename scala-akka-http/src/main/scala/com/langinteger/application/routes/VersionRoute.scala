package com.langinteger.application.routes

import java.io.{File, FileInputStream}
import java.util.Properties

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.langinteger.utils.api.ResponseWrapper

case class VersionRoute() {

  val versionRoute: Route =
    path("version") {
      get {
        complete {
          val fileUrl = getClass().getResource("/version.properties")
          val prop = new Properties()
          prop.load(new FileInputStream(fileUrl.getFile))
          val version = prop.get("version")
          HttpEntity(ContentTypes.`application/json`, ResponseWrapper.dataResponse(version))
        }
      }
    }

}