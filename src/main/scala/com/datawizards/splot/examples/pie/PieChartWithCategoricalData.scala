package com.datawizards.splot.examples.pie

import com.datawizards.splot.api.implicits._

object PieChartWithCategoricalData extends App {
  val populationByCountry = Seq(
    ("DE", 81),
    ("TR", 72),
    ("FR", 63),
    ("UK", 62),
    ("IT", 61)
  )

  populationByCountry.plotPie()
}
