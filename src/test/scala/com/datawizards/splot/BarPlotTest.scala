package com.datawizards.splot

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.datawizards.splot.api.implicits._
import com.datawizards.splot.builders.PlotBuilder
import com.datawizards.splot.model.PlotAxisValues.XAxisValueTypeString
import com.datawizards.splot.model.PlotType

@RunWith(classOf[JUnitRunner])
class BarPlotTest extends SPlotBaseTest {
  test("Bar") {
    val data = Seq(1.0, 4.0, 9.0)

    data.plotBar(unitTestsDevice)

    val plot = getLastPlot

    assertResult(PlotType.Bar) {
      plot.plotType
    }

    assertResult(1, "series count") {
      plot.series.size
    }

    assertPlotXYAxisValues(Seq(1, 2, 3), data, getLastPlotFirstSeries)
  }

  test("String x values") {
    val xs = Seq("c1", "c2", "c3")
    val ys = Seq(1, 2, 3)
    val data = xs zip ys

    data.plotBar(unitTestsDevice)

    assertResult(PlotType.Bar) {
      getLastPlot.plotType
    }

    assertPlotXYAxisValues(xs, ys, getLastPlotFirstSeries)

  }

  test("Change title") {
    val data = Seq(1.0, 4.0, 9.0)

    data
      .buildPlot()
      .bar()
      .titles("main title", "x title", "y title")
      .display(unitTestsDevice)

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
      .display(unitTestsDevice)

    assertPlotXYAxisValues(Seq("b1", "b2"), Seq(11, 12), getLastPlot.findSeriesByName("series1"))
    assertPlotXYAxisValues(Seq("b1", "b2", "b3"), Seq(31, 32, 33), getLastPlot.findSeriesByName("series3"))
  }

  test("Multiple columns with sequence of X") {
    val data = Seq(
      ("col1", 101),
      ("col1", 102),
      ("col1", 103),
      ("col2", 201),
      ("col2", 202)
    )

    data
      .buildPlot()
      .bar(_._2)
      .colsBy(_._1)
      .display(unitTestsDevice)

    val plotsGrid = getLastPlotsGrid

    val plotCol1 = plotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, new XAxisValueTypeString("col1"))
    val plotCol2 = plotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, new XAxisValueTypeString("col2"))

    assertPlotXYAxisValues(Seq(1,2,3), Seq(101,102,103), plotCol1.series.head)
    assertPlotXYAxisValues(Seq(1,2), Seq(201,202), plotCol2.series.head)
  }

}
