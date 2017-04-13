package com.datawizards.splot.examples.bar

import com.datawizards.splot.api.implicits._

case class Person(name: String, age: Int)

object BarChartForCaseClassWithImplicits extends App {
  Seq(
    Person("p1", 20),
    Person("p2", 30),
    Person("p3", 40)
  ).plotBar(_.age)
}
