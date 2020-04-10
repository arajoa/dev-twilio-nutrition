lazy val branch = sys.props.get("docker.tags.branch")

lazy val root = project
  .in(file("."))
  .settings(
    name                 := "dev-twilio-nutrition",
    version              := "0.1",
    scalaVersion         := "2.12.11",
    mainClass in Compile := Some("io.arajoa.hackathon.Main"),
    libraryDependencies ++= appDependencies,
    libraryDependencies ++= testDependencies
  )
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(
    dockerExposedPorts ++= Seq(8080),
    dockerBaseImage    := "openjdk:11.0.6-jre",
    dockerRepository   := sys.props.get("docker.repository"),
    dockerUsername     := sys.props.get("docker.organization"),
    dockerUpdateLatest := branch.isEmpty,
    dockerAlias := DockerAlias(
      registryHost = dockerRepository.value,
      username     = dockerUsername.value,
      name         = name.value,
      tag          = branch.orElse(Some(version.value))
    )
  )

lazy val appDependencies = Seq(
  Library.finchx,
  Library.finchXCirce,
  Library.circe,
  Library.circeGeneric,
  Library.cats,
  Library.catsEffect
)

lazy val testDependencies = Seq(
  Library.scalaTest
)
