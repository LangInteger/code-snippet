import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server._

object Main1 extends HttpApp {

  implicit def myRejectionHandler =
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
      .handleAll[MethodRejection] { methodRejections =>
      val names = methodRejections.map(_.supported.name)
      complete((MethodNotAllowed, s"Can't do that! Supported: ${names mkString " or "}!"))
    }
      .handleNotFound {
        complete((NotFound, "Not here!"))
      }
      .result()


  protected def routes: Route =
    get {
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<html><body>Hello world!</body></html>"))
      } ~
        path("ping") {
          complete("PONG!")
        } ~
        path("crash") {
          sys.error("BOOM!")
        }
    }

  startServer("localhost", 8082)

}
