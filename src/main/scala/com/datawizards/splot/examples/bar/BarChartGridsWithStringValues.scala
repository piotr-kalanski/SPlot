package com.datawizards.splot.examples.bar

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object BarChartGridsWithStringValues extends App {
  val groupedPeople = people
    .groupBy(p => (p.country, p.education))
    .mapValues(pv => pv.size)

  groupedPeople
    .buildPlot()
    .colsBy(_._1._1)
    .bar(x => x._1._2, x => x._2)
    .size(1200, 300)
    .display()
}
