package com.datawizards.splot

import scala.util.Random

package object examples {
  private val rand = new Random()
  rand.setSeed(0L)
  case class AgeIncome(age: Int, income: Double)
  case class Person(name: String, age: Int, country: String, education: String, income: Double)

  val ageIncome = Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
  )

  val people: Iterable[Person] = for(i <- 1 to 100) yield randomPerson

  private def randomPerson: Person = {
    Person(
      name = "p_" + rand.nextInt(1000),
      age = rand.nextInt(100),
      country = randSelect("PL", "UK", "DE", "USA"),
      education = randSelect("MSc", "BSc", "PhD"),
      income = rand.nextInt(100000)
    )
  }

  private def randSelect[T](values: T*): T = {
    values(rand.nextInt(values.size).abs)
  }
}
