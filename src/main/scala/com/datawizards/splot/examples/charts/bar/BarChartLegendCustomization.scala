package com.datawizards.splot.examples.charts.bar

import com.datawizards.splot.api.implicits._

object BarChartLegendCustomization extends App {
  val data = Seq(1.0, 4.0, 9.0)

  data
    .buildPlot()
    .bar()
    .legendVisible(false)
    .display()

  data
    .buildPlot()
    .bar()
    .seriesName("custom name")
    .legendVisible(true)
    .display()
}
