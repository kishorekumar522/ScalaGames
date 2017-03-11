import sbt.Keys._
import sbt._

object Commons {
  val appVersion = "1.0"

  val settings: Seq[Def.Setting[_]] = Seq(
    version := appVersion,
    resolvers += Opts.resolver.mavenLocalFile
  )
}