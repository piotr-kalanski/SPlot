package com.datawizards.splot

import java.util.{Calendar, Date}

import scala.util.Random

package object examples {
  private val rand = new Random()
  rand.setSeed(0L)
  case class AgeIncome(age: Int, income: Double)
  case class Person(name: String, age: Int, country: String, education: String, income: Double)

  lazy val ageIncome = Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
  )

  lazy val people: Iterable[Person] = for(i <- 1 to 100) yield randomPerson
  lazy val people1000: Iterable[Person] = for(i <- 1 to 1000) yield randomPerson
  lazy val timeseriesData2017: Iterable[(Date,Double)] = for(month <- 1 until 12; day <- 1 to 30) yield (date(2017,month,day), 100*month+day+rand.nextGaussian()*50)
  lazy val timeseriesData201704: Iterable[(Date,Double)] = for(day <- 1 until 30) yield (date(2017,4,day), 100*day+rand.nextGaussian()*100)

  private def randomPerson: Person = {
    Person(
      name = "p_" + rand.nextInt(1000),
      age = Math.abs(50 + (rand.nextGaussian()*10).toInt),
      country = randSelect("PL", "UK", "DE", "USA"),
      education = randSelect("MSc", "BSc", "PhD"),
      income = rand.nextInt(100000)
    )
  }

  private def randSelect[T](values: T*): T = {
    values(rand.nextInt(values.size).abs)
  }

  private def date(year: Int, month: Int, day: Int): Date = {
    val cal = Calendar.getInstance()
    cal.set(year, month-1, day)
    cal.getTime
  }

}
