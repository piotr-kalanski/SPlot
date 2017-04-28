package com.datawizards.splot.examples.customizations

import com.datawizards.splot.api.implicits._

object ShowAnnotations extends App {
  Seq(1, 4, 9)
    .buildPlot()
    .bar()
    .showAnnotations(true)
    .display()
}
