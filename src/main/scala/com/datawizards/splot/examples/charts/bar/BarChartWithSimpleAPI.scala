package com.datawizards.splot.examples.charts.bar

import com.datawizards.splot.api.implicits._

object BarChartWithSimpleAPI extends App {
  SPlot.plotBar(Seq(1.0, 4.0, 9.0))

  SPlot.plotBar(Seq("a","b","c"), Seq(1.0, 4.0, 9.0))
}
