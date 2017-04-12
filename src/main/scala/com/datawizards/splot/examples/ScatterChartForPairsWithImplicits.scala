package com.datawizards.splot.examples

import com.datawizards.splot.implicits._

object ScatterChartForPairsWithImplicits extends App {
  val data = Seq(
    (1, 1),
    (2, 4),
    (3, 9)
  )

  data.plotScatter(_._1, _._2)
}
