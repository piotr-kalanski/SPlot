package com.datawizards.splot.examples.charts.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartsGrid extends App {

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .size(1600, 1200)
    .colsBy(_.country)
    .rowsBy(_.education)
    .display()
}
