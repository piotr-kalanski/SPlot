package com.datawizards.splot.examples.charts.histogram

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object HistogramMultipleColumns extends App {
  people1000
    .buildPlot()
    .colsBy(_.education)
    .histogram(_.age, 50)
    .size(1200, 400)
    .display()
}
