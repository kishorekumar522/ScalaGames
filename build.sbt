name := "ScalaGames"

version := "1.0"

scalaVersion := "2.11.8"

//lazy val root = (project in file(".")).
//  settings(
//    name := "my-project",
//    version := "1.0",
//    scalaVersion := "2.11.8",
//    mainClass in Compile := Some("myPackage.MyMainObject")
//  )

//lazy val core = (project in file("."))
//  //  .settings(commonSettings: _*)
//  .settings()

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "3.0.1" % "runtime,test",

  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.slf4j" % "slf4j-simple" % "1.7.21",
  "org.scala-lang" % "scala-library" % "2.11.8"
)

scalaSource in Compile := baseDirectory.value / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"
