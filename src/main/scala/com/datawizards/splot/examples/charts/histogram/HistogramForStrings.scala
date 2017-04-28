package com.datawizards.splot.examples.charts.histogram

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples.people

object HistogramForStrings extends App {
  people.plotHistogramForCategories(_.education)
}
