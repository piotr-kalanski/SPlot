package com.datawizards.splot.examples.area

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object AreaChartForCaseClassWithImplicits extends App {
  val data = ageIncome

  data.plotArea(_.age, _.income)
}
