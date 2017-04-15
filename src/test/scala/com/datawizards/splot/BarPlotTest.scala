package com.datawizards.splot

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.{PlotAxisValues, PlotType}

@RunWith(classOf[JUnitRunner])
class BarPlotTest extends SPlotBaseTest {
  test("Bar") {
    val data = Seq(1.0, 4.0, 9.0)

    data.plotBar()

    val plot = getLastPlot

    assertResult(PlotType.Bar) {
      plot.plotType
    }

    assertResult(PlotAxisValues.createXAxisValuesInt(Seq(1, 2, 3)), "x values") {
      plot.xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesDouble(data), "y values") {
      plot.yValues
    }
  }

  test("String x values") {
    val xs = Seq("c1", "c2", "c3")
    val ys = Seq(1, 2, 3)
    val data = xs zip ys

    data.plotBar()

    val plot = getLastPlot

    assertResult(PlotType.Bar) {
      plot.plotType
    }

    assertResult(PlotAxisValues.createXAxisValuesString(xs), "x values") {
      plot.xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesInt(ys), "y values") {
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
