package com.datawizards.splot.examples.charts.bar

import com.datawizards.splot.api.implicits._

object BarChartStringXValues extends App {
  val populationByCountry = Seq(
    ("DE", 81),
    ("TR", 72),
    ("FR", 63),
    ("UK", 62),
    ("IT", 61),
    ("ES", 46),
    ("UA", 45),
    ("PL", 38),
    ("RO", 19),
    ("NL", 17),
    ("GR", 11),
    ("PT", 11),
    ("BE", 10),
    ("CZ", 10),
    ("HU", 10)
  )

  populationByCountry.plotBar()
}
