package com.datawizards.splot.examples.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartsGrid extends App {

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .colsBy(_.country)
    .rowsBy(_.education)
    .display()
}
