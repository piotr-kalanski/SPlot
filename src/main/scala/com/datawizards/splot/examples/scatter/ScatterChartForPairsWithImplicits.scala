package com.datawizards.splot.examples.scatter

import com.datawizards.splot.api.implicits._

object ScatterChartForPairsWithImplicits extends App {
  val data = Seq(
    (1, 1),
    (2, 4),
    (3, 9)
  )

  data.plotScatter(_._1, _._2)
}
