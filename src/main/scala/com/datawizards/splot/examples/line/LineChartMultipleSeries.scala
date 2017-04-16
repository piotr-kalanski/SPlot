package com.datawizards.splot.examples.line

import com.datawizards.splot.api.implicits._

object LineChartMultipleSeries extends App {

  val xs = (1 to 100) map (x => x/10.0)
  val sinx = xs.map(x => ("sin", x, Math.sin(x)))
  val cosx = xs.map(x => ("cos", x, Math.cos(x)))
  val sqrtx = xs.map(x => ("sqrt", x, Math.sqrt(x)))

  val data = sinx union cosx union sqrtx

  data
    .buildPlot()
    .seriesBy(_._1)
    .line(_._2, _._3)
    .titles("Functions", "x", "y")
    .size(1200, 400)
    .display()

}
