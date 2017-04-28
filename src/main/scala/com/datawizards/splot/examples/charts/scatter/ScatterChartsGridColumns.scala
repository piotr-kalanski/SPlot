package com.datawizards.splot.examples.charts.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartsGridColumns extends App {

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .colsBy(_.country)
    .size(1200, 300)
    .display()
}
