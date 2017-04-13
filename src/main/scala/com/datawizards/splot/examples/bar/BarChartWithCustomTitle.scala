package com.datawizards.splot.examples.bar

import com.datawizards.splot.api.implicits._

object BarChartWithCustomTitle extends App {
  val data = Seq(1.0, 4.0, 9.0)

  data
    .buildPlot()
    .bar(identity)
    .titles("Example bar chart", "x values", "y values")
    .display()
}
