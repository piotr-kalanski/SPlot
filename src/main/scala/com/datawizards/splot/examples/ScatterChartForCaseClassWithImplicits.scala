package com.datawizards.splot.examples

import com.datawizards.splot.implicits._

case class AgeIncome(age: Int, income: Double)

object ScatterChartForCaseClassWithImplicits extends App {
  val data = Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
  )

  data.plotScatter(_.age, _.income)
}
