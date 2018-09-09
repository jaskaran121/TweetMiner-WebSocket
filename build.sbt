name := """Soen6441Assignment2"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += ehcache
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.6"

libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"

libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.3" % Test

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.8.0" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "3.0.0" % Test
