package com.datawizards.splot.examples.histogram

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object HistogramMultipleSeries extends App {
  people1000
    .buildPlot()
    .seriesBy(_.education)
    .histogram(_.age, 50)
    .display()
}
