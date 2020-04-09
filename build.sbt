name := "dev-twilio-nutrition"

version := "0.1"

scalaVersion := "2.12.11"

mainClass in Compile := Some("io.arajoa.hackathon.Main")
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerExposedPorts ++= Seq(8080)
dockerBaseImage := "openjdk:11.0.6-jre"

dockerUsername   := sys.props.get("DOCKER_USERNAME")
dockerRepository := sys.props.get("DOCKER_REPOSITORY")

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core"   % "0.32.1",
  "com.github.finagle" %% "finchx-circe"  % "0.32.1",
  "io.circe"           %% "circe-core"    % "0.12.3",
  "io.circe"           %% "circe-generic" % "0.12.3",
  "org.typelevel"      %% "cats-core"     % "2.0.0",
  "org.typelevel"      %% "cats-effect"   % "2.1.2",
  "org.scalatest"      %% "scalatest"     % "3.1.1" % "test"
)
