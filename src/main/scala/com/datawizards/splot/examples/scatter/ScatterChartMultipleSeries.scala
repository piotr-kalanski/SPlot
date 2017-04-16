package com.datawizards.splot.examples.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartMultipleSeries extends App {

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .seriesBy(_.country)
    .titles("Age and income by education", "age", "income")
    .display()
}
