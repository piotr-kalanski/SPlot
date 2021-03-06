package com.datawizards.splot.customizations

import com.datawizards.splot.SPlotBaseTest
import com.datawizards.splot.api.implicits._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LegendCustomizationsTest extends SPlotBaseTest {
  val data = Seq(1.0, 4.0, 9.0)

  test("Don't change legend") {
    data
      .buildPlot()
      .bar()
      .display(unitTestsDevice)

    assert(getLastPlot.legendVisible === None)
  }

  test("Hide legend") {
    data
      .buildPlot()
      .bar()
      .legendVisible(false)
      .display(unitTestsDevice)

    assert(getLastPlot.legendVisible === Some(false))
  }

  test("Change series name") {
    val customName = "Custom series name"

    data
      .buildPlot()
      .bar()
      .seriesName(customName)
      .display(unitTestsDevice)

    assert(getLastPlot.series.head.name === customName)
  }
}
