package com.datawizards.splot.examples.charts.aggregations

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._
import com.datawizards.splot.functions._

object BarChartWithAggregations extends App {

  people
    .buildPlot()
    .barWithAggregations(_.country, mean(_.income))
    .display()

}
