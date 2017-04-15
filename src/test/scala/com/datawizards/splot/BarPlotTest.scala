package com.datawizards.splot

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.PlotType

@RunWith(classOf[JUnitRunner])
class BarPlotTest extends SPlotBaseTest {
  test("Bar") {
    val data = Seq(1.0, 4.0, 9.0)

    data.plotBar()

    val plot = getLastPlot

    assertResult(PlotType.Bar) {
      plot.plotType
    }

    assertResult(Seq(1.0, 2.0, 3.0), "x values") {
      plot.xValues
    }

    assertResult(data, "y values") {
      plot.yValues
    }
  }

  test("Change title") {
    val data = Seq(1.0, 4.0, 9.0)

    data
      .buildPlot()
      .bar()
      .titles("main title", "x title", "y title")
      .display()

    val plot = getLastPlot

    assertResult("main title") {
      plot.title
    }

    assertResult("x title") {
      plot.xTitle
    }

    assertResult("y title") {
      plot.yTitle
    }
  }
}
