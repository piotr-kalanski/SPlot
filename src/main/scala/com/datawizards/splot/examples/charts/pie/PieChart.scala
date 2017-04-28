package com.datawizards.splot.examples.charts.pie

import com.datawizards.splot.api.implicits._

object PieChart extends App {
  val data = Seq(1.0, 4.0, 9.0)

  data.plotPie()
}
