package com.datawizards.splot.examples.line

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object LineChartForCaseClassWithImplicits extends App {
  val data = ageIncome

  data.plotLine(_.age, _.income)
}
