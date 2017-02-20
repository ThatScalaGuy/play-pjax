package com.thatscalaguy.play.filters

import javax.inject.Inject

import akka.stream.Materializer
import akka.util.ByteString
import com.thatscalaguy.utils.HTML
import play.api.http.HttpEntity
import play.api.mvc.{Filter, RequestHeader, Result}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by sven on 8/4/16.
  */
class PjaxFilter @Inject()(materializer: Materializer)(implicit executionContext: ExecutionContext) extends Filter {
  private[this] val CONTAINER_KEY = "X-PJAX-Container"
  private[this] val PJAX_KEY = "X-PJAX"

  override implicit def mat: Materializer = materializer

  override def apply(f: (RequestHeader) => Future[Result])(rh: RequestHeader): Future[Result] = {
    rh.headers.get(PJAX_KEY) match {
      case Some("true") if rh.headers.keys.contains(CONTAINER_KEY) =>
        f(rh) flatMap { result =>
          stripBody(result.body, rh.headers.get(CONTAINER_KEY).get.replace("#", "")) map { body =>
            result.copy(body = body)
          }
        }
      case _ => f(rh)
    }
  }

  private[this] def stripBody(body: HttpEntity, container: String): Future[HttpEntity] = {
    body.consumeData map { content =>
      val root = HTML.loadString(content.decodeString("UTF-8"))

      val stripedBody = (root \\ "_" filter (_ \@ "id" == container)).toString

      val newContent = if(stripedBody.isEmpty) content.decodeString("UTF-8") else stripedBody

      HttpEntity.Strict(ByteString(newContent), body.contentType)
    }
  }
}
