package com.datawizards.splot

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.datawizards.splot.api.implicits._

@RunWith(classOf[JUnitRunner])
class LegendCustomizationsTest extends SPlotBaseTest {
  val data = Seq(1.0, 4.0, 9.0)

  test("Hide legend") {
    data
      .buildPlot()
      .bar()
      .legendVisible(false)
      .display()

    assert(getLastPlot.legendVisible === false)
  }

  test("Change series name") {
    val customName = "Custom series name"

    data
      .buildPlot()
      .bar()
      .seriesName(customName)
      .display()

    assert(getLastPlot.seriesName === customName)
  }
}
