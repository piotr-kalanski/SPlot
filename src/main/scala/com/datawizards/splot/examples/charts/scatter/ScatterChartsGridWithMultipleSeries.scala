package com.datawizards.splot.examples.charts.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartsGridWithMultipleSeries extends App {

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .size(1200, 300)
    .colsBy(_.country)
    .seriesBy(_.education)
    .display()
}
