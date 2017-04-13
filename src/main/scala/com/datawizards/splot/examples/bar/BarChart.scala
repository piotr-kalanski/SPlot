package com.datawizards.splot.examples.bar

import com.datawizards.splot.api.SPlot

object BarChart extends App {
  val data = Seq(1.0, 4.0, 9.0)

  SPlot(data).bar(x => x).display()
}
