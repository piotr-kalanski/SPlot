package com.datawizards.splot.examples.bar

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples.people

object BarChartMultipleSeries extends App {
  people
    .buildPlot()
    .bar(_.income)
    .seriesBy(_.education)
    .display()
}
