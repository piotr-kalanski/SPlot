package com.datawizards.splot.examples.charts.area

import com.datawizards.splot.api.implicits._

object AreaChartForPairsWithImplicits extends App {
  val data = Seq(
    (1.0, 1.0),
    (2.0, 4.0),
    (3.0, 9.0)
  )

  data.plotArea()
}
