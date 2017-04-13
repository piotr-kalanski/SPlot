package com.datawizards.splot

package object examples {
  case class AgeIncome(age: Int, income: Double)
  case class Person(name: String, age: Int)

  val ageIncome = Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
  )

  val people = Seq(
    Person("p1", 20),
    Person("p2", 30),
    Person("p3", 40)
  )
}
