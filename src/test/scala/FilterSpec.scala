import akka.stream.Materializer
import akka.util.Timeout
import com.thatscalaguy.play.filters.PjaxFilter
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.mvc.Action
import play.api.mvc.Results._
import play.api.test.FakeRequest
import play.api.test.Helpers.contentAsString

import scala.concurrent.duration._

class FilterSpec extends PlaySpec with OneAppPerSuite with ScalaFutures {

  implicit lazy val materializer: Materializer = app.materializer
  implicit lazy val executionContext = materializer.executionContext
  implicit lazy val timeout = Timeout(5 seconds)

  implicit class StripAllString(underlying: String) {
    def stripAll: String = {
      val bad: Seq[Char] = Vector('\t', ' ', '\n')

      @scala.annotation.tailrec def start(n: Int): String =
        if (n == underlying.length) ""
        else if (bad.indexOf(underlying.charAt(n)) < 0) end(n, underlying.length)
        else start(1 + n)

      @scala.annotation.tailrec def end(a: Int, n: Int): String =
        if (n <= a) underlying.substring(a, n)
        else if (bad.indexOf(underlying.charAt(n - 1)) < 0) underlying.substring(a, n)
        else end(a, n - 1)

      start(0)
    }
  }


  "A PjaxFilter" should {

    def fullHTML(id: Option[String]) =
      s"""<!DOCTYPE html>
         |<html>
         | <body>
         |${containerHTML(id)}
         | </body>
         |</html>
         |""".stripMargin

    def containerHTML(id: Option[String]) = {
      def idTag = id match {
        case Some(name) => s""" id="$name""""
        case None => ""
      }
      s"""   <div$idTag>
         |     <h1>My Heading</h1>
         |     <p>My paragraph.</p>
         |   </div>""".stripMargin
    }


    def action(id: Option[String]) = Action { request =>
      Ok(fullHTML(id))
    }

    "extract the container" in {
      val id = Some("container")
      val filter = new PjaxFilter(app.materializer)
      val rh = FakeRequest().withHeaders("X-PJAX" -> "true", "X-PJAX-Container" -> s"#${id.get}")

      contentAsString(filter(action(id))(rh).run()).stripAll mustBe containerHTML(id).stripAll
    }

    "fallback on missing X-PJAX header" in {
      val id = Some("container")
      val filter = new PjaxFilter(app.materializer)
      val rh = FakeRequest().withHeaders("X-PJAX-Container" -> s"#${id.get}")

      contentAsString(filter(action(id))(rh).run()).stripAll mustBe fullHTML(id).stripAll
    }

    "fallback on missing X-PJAX-Container header" in {
      val id = Some("container")
      val filter = new PjaxFilter(app.materializer)
      val rh = FakeRequest().withHeaders("X-PJAX" -> "true")

      contentAsString(filter(action(id))(rh).run()).stripAll mustBe fullHTML(id).stripAll
    }

    "fallback on wrong X-PJAX-Container" in {
      val id = Some("container")
      val filter = new PjaxFilter(app.materializer)
      val rh = FakeRequest().withHeaders("X-PJAX" -> "true", "X-PJAX-Container" -> "wrong_container")

      contentAsString(filter(action(id))(rh).run()).stripAll mustBe fullHTML(id).stripAll
    }
  }
}
