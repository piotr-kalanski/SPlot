name := "SPlot"

organization := "com.github.piotr-kalanski"

version := "0.2.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.knowm.xchart" % "xchart" % "3.2.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

coverageExcludedPackages := "com.datawizards.splot.examples.*"

concurrentRestrictions in Global := Seq(
  Tags.limit(Tags.Test, 1)
)
