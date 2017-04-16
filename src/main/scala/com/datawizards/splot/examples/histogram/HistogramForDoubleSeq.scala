package com.datawizards.splot.examples.histogram

import com.datawizards.splot.api.implicits._

import scala.util.Random

object HistogramForDoubleSeq extends App {
  val rand = new Random()
  val gaussians = for(i <- 1 to 10000) yield rand.nextGaussian()
  gaussians.plotHistogram(100)
}
