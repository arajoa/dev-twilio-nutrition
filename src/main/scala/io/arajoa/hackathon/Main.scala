package io.arajoa.hackathon

import java.nio.charset.Charset

import cats.effect.{ExitCode, IO, IOApp, Resource}
import cats.implicits._
import com.twilio.twiml.MessagingResponse
import com.twilio.twiml.messaging.{Body, Message}
import com.twitter.finagle.{Http, ListeningServer}
import com.twitter.io.Buf
import io.arajoa.hackathon.ConversionDataTypes._
import io.finch.Decode.Dispatchable
import io.finch._

import scala.util.Try

object Main extends IOApp with Endpoint.Module[IO] {
  val api: Endpoint[IO, String] = get("hello") { Ok("Hello, World!") }

  implicit val bufDecoder: Dispatchable[Buf, Text.Plain] = (_: String, b: Buf, _: Charset) => Try(b).toEither

  val logEndpoint: Endpoint[IO, String] = post("twilio" :: body[Buf, Text.Plain]) { request: Buf =>
    {
      val content = Buf.ByteArray.Owned.extract(request).map(_.toChar).mkString
      println(content)
      val body  = new Body.Builder(content).build
      val sms   = new Message.Builder().body(body).build
      val twiml = new MessagingResponse.Builder().message(sms).build
      Ok(twiml.toXml)
    }
  }

  val endpoints = api :+: logEndpoint

  def server: Resource[IO, ListeningServer] =
    Resource.make {
      IO(Http.server.serve(":8080", endpoints.toServiceAs[Text.Plain]))
    } { s => IO.fromFuture(IO(s.close().asScala)) }

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- server
        .use(_ => IO(println("app started")) *> IO.never)
        .handleErrorWith(error => {
          IO(println("error starting")) *>
            IO.raiseError(error)
        })
    } yield ExitCode.Success
}
