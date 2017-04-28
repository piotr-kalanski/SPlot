package com.datawizards.splot.examples.charts.aggregations

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._
import com.datawizards.splot.functions._

object PieChartWithAggregations extends App {

  people
    .buildPlot()
    .pieWithAggregations(_.country, mean(_.income))
    .display()

}
