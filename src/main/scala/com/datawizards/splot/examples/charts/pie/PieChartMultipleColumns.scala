package com.datawizards.splot.examples.charts.pie

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples.people
import com.datawizards.splot.functions._

object PieChartMultipleColumns extends App {
  people
    .buildPlot()
    .colsBy(_.education)
    .rowsBy(_.country)
    .pieWithAggregations(_.age / 10, mean(_.income))
    .size(1200, 800)
    .display()
}
