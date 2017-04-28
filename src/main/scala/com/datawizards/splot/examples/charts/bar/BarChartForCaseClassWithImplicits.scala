package com.datawizards.splot.examples.charts.bar

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object BarChartForCaseClassWithImplicits extends App {
  people.plotBar(_.age)
}
