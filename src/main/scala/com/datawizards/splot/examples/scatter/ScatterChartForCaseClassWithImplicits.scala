package com.datawizards.splot.examples.scatter

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._

object ScatterChartForCaseClassWithImplicits extends App {
  val data = ageIncome

  data.plotScatter(_.age, _.income)
}
