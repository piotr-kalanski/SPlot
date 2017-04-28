package com.datawizards.splot.examples.charts.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartsGridRows extends App {

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .rowsBy(_.education)
    .size(200, 600)
    .display()
}
