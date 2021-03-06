
name := "scala_stringlike_bug"
scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.11.12", "2.12.8")

version := "1.0.0-SNAPSHOT"

enablePlugins(JmhPlugin)

// libraryDependencies += "com.twitter" %% "util-core" % "19.2.0" //from "file:///Users/fbrasil/workspace/util/util-core/target/scala-2.11/util-core-assembly-18.6.0.jar"

libraryDependencies += "com.twitter" %% "finagle-http" % "19.2.0"  % "compile"
libraryDependencies += "com.lihaoyi" %% "fastparse" % "2.1.0"

libraryDependencies += "net.bytebuddy" % "byte-buddy" % "1.8.3"
libraryDependencies += "net.bytebuddy" % "byte-buddy-agent" % "1.8.3"

//libraryDependencies += "cglib" % "cglib" % "3.2.8"

scalacOptions ++= Seq("-Ydelambdafy:inline")

assemblyMergeStrategy in assembly := {
  _ match {
	  case "META-INF/MANIFEST.MF" => MergeStrategy.discard
	  case _ => MergeStrategy.first
  }
}
