package com.datawizards.splot.examples.charts.bar

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object BarChartMultipleColumns extends App {
  people
    .buildPlot()
    .colsBy(_.education)
    .rowsBy(_.country)
    .bar(_.age)
    .size(1200, 800)
    .display()
}
