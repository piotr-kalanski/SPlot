package com.datawizards.splot.examples.charts.bubble

import com.datawizards.splot.api.implicits._

object BubbleChart extends App {
  val data = Seq(
    (1, 1, 9.0),
    (1, 2, 40.0),
    (3, 2, 60.0),
    (2, 2, 90.0),
    (1, 3, 30.0),
    (2, 3, 40.0)
  )

  data
    .buildPlot()
    .bubble(_._1, _._2, _._3)
    .display()
}
