package com.datawizards.splot.examples.bubble

import com.datawizards.splot.api.implicits._

object BubbleChart extends App {
  val data = Seq(
    (1, 1, 9.0),
    (1, 2, 4.0),
    (3, 2, 6.0),
    (2, 2, 2.0),
    (1, 3, 3.0),
    (2, 3, 4.0)
  )

  data
    .buildPlot()
    .bubble(_._1, _._2, _._3)
    .display()
}
