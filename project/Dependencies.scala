import sbt._

object Version {

  val finchx       = "0.32.1"
  val finchxCirce  = "0.32.1"
  val circe        = "0.13.0"
  val circeGeneric = "0.13.0"
  val cats         = "2.1.1"
  val catsEffect   = "2.1.1"
  val scalaTest    = "3.1.1"
}

object Library {

  val finchx       = "com.github.finagle"  %% "finchx-core"   % Version.finchx
  val finchXCirce  = "com.github.finagle" %% "finchx-circe"  % Version.finchxCirce
  val circe        = "io.circe"            %% "circe-core"    % Version.circe
  val circeGeneric = "io.circe"            %% "circe-generic" % Version.circeGeneric
  val cats         = "org.typelevel"       %% "cats-core"     % Version.cats
  val catsEffect   = "org.typelevel"       %% "cats-effect"   % Version.catsEffect

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % Test
}
