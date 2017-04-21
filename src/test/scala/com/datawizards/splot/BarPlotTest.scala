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

    assertResult(1, "series count") {
      plot.series.size
    }

    assertResult(PlotAxisValues.createXAxisValuesInt(Seq(1, 2, 3)), "x values") {
      plot.series.head.xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesDouble(data), "y values") {
      plot.series.head.yValues
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
      plot.series.head.xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesInt(ys), "y values") {
      plot.series.head.yValues
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

  test("Multiple series") {
    val data = Seq(
      ("series1","b1",11),
      ("series1","b2",12),
      ("series2","b1",21),
      ("series2","b2",22),
      ("series3","b1",31),
      ("series3","b2",32),
      ("series3","b3",33)
    )

    data
      .buildPlot()
      .bar(_._2, _._3)
      .seriesBy(_._1)
      .display()

    val plot = getLastPlot

    assertResult(PlotAxisValues.createYAxisValuesInt(Seq(11, 12))) {
      plot.series.filter(_.name == "series1").head.yValues
    }

    assertResult(PlotAxisValues.createXAxisValuesString(Seq("b1", "b2"))) {
      plot.series.filter(_.name == "series1").head.xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesInt(Seq(31, 32, 33))) {
      plot.series.filter(_.name == "series3").head.yValues
    }

    assertResult(PlotAxisValues.createXAxisValuesString(Seq("b1", "b2", "b3"))) {
      plot.series.filter(_.name == "series3").head.xValues
    }
  }

  // TODO - add test for bar charts by columns (without X axis mapping - sequence)

}
