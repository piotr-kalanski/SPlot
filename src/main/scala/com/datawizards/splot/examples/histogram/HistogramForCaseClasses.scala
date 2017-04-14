package com.datawizards.splot.examples.histogram

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object HistogramForCaseClasses extends App {
  people.plotHistogram(_.income, 10)
}
