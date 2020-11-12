import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.langinteger.application.routes.HelloWorldWithDslRoute
import org.scalatest.{Matchers, WordSpec}

class RestSpec extends WordSpec with Matchers with ScalatestRouteTest {

  val helloWorldWithDslRoute = HelloWorldWithDslRoute()

  "Hello world API" should {
    "Return hello world" in {
      Get("/v1/hello") -> helloWorldWithDslRoute -> check {
        responseAs[String] shouldEqual "<h1>Say hello to akka-http</h1>"
      }
    }
  }

}
