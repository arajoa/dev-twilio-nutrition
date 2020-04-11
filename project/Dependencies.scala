import sbt._

object Version {

  val cats       = "2.1.1"
  val catsEffect = "2.1.1"
  val circe      = "0.13.0"
  val finchx     = "0.32.1"
  val twilio     = "7.49.0"

  val scalaTest = "3.1.1"
}

object Library {

  val cats         = "org.typelevel"      %% "cats-core"     % Version.cats
  val catsEffect   = "org.typelevel"      %% "cats-effect"   % Version.catsEffect
  val circe        = "io.circe"           %% "circe-core"    % Version.circe
  val circeGeneric = "io.circe"           %% "circe-generic" % Version.circe
  val finchx       = "com.github.finagle" %% "finchx-core"   % Version.finchx
  val finchXCirce  = "com.github.finagle" %% "finchx-circe"  % Version.finchx
  val twilio       = "com.twilio.sdk"     % "twilio"         % Version.twilio

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % Test
}
