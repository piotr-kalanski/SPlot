package com.datawizards.splot.model

import java.util.{Calendar, Date}

import com.datawizards.splot.model.PlotAxisValues.{XAxisValueTypeDate, XAxisValueTypeDouble, XAxisValueTypeInt, XAxisValueTypeString}
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class XAxisValueTypeTest extends FunSuite {
  private val i1 = new XAxisValueTypeInt(1)
  private val i1b = new XAxisValueTypeInt(1)
  private val i2 = new XAxisValueTypeInt(2)

  private val d1 = new XAxisValueTypeDouble(1.0)
  private val d1b = new XAxisValueTypeDouble(1.0)
  private val d2 = new XAxisValueTypeDouble(2.0)

  private val s1 = new XAxisValueTypeString("1")
  private val s1b = new XAxisValueTypeString("1")
  private val s2 = new XAxisValueTypeString("2")

  private val date1 = new XAxisValueTypeDate(date(2017,1,1))
  private val date1b = date1
  private val date2 = new XAxisValueTypeDate(date(2017,1,2))

  test("Equals") {
    assert(i1 == i1b)
    assert(i1 != i2)

    assert(d1 == d1b)
    assert(d1 != d2)

    assert(s1 == s1b)
    assert(s1 != s2)

    assert(date1 == date1b)
    assert(date1 != date2)
  }

  test("Compare") {
    assert(i1 < i2)
    assert(d1 < d2)
    assert(s1 < s2)
    assert(date1 < date2)
  }

  private def date(year: Int, month: Int, day: Int): Date = {
    val cal = Calendar.getInstance()
    cal.set(year, month-1, day)
    cal.getTime
  }

}
