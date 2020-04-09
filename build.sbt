name := "dev-twilio-nutrition"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core"   % "0.32.1",
  "com.github.finagle" %% "finchx-circe"  % "0.32.1",
  "io.circe"           %% "circe-core"    % "0.12.3",
  "io.circe"           %% "circe-generic" % "0.12.3",
  "org.typelevel"      %% "cats-core"     % "2.0.0",
  "org.typelevel"      %% "cats-effect"   % "2.1.2",
  "org.scalatest"      %% "scalatest"     % "3.1.1" % "test"
)
