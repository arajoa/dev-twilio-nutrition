package io.arajoa.hackathon

import cats.effect.{ExitCode, IO, IOApp, Resource}
import cats.implicits._
import com.twitter.finagle.{Http, ListeningServer}
import io.arajoa.hackathon.ConversionDataTypes._
import io.finch._

object Main extends IOApp with Endpoint.Module[IO] {

  val api: Endpoint[IO, String] = get("hello") { Ok("Hello, World!") }

  val incoming: Endpoint[IO, String] =
    post(
      "incoming":: paramOption[String]("To") :: paramOption[String]("From") :: paramOption[String]("Body")
    ){
      (to: Option[String], from: Option[String], body: Option[String]) =>
        println(s"-- $to --\n-- $from --\n-- $body --")
        Ok("yeah!")
    }

  def server: Resource[IO, ListeningServer] =
    Resource.make {
      IO(Http.server.serve(":8080", (api :+: incoming).toServiceAs[Text.Plain]))
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
