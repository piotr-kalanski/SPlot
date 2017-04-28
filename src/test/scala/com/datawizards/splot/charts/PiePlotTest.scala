package com.datawizards.splot.charts

import com.datawizards.splot.SPlotBaseTest
import com.datawizards.splot.api.implicits._
import com.datawizards.splot.builders.PlotBuilder
import com.datawizards.splot.mapper.SPlotToXChartMapper
import com.datawizards.splot.model.PlotAxisValues.XAxisValueTypeString
import com.datawizards.splot.model.PlotType
import org.junit.runner.RunWith
import org.knowm.xchart.PieChart
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PiePlotTest extends SPlotBaseTest {
  test("Pie") {
    val data = Seq(1.0, 4.0, 9.0)

    data.plotPie(unitTestsDevice)

    val plot = getLastPlot

    assertResult(PlotType.Pie) {
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

    data.plotPie(unitTestsDevice)

    assertResult(PlotType.Pie) {
      getLastPlot.plotType
    }

    assertPlotXYAxisValues(xs, ys, getLastPlotFirstSeries)

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
      .pie(_._2)
      .colsBy(_._1)
      .display(unitTestsDevice)

    val plotsGrid = getLastPlotsGrid

    val plotCol1 = plotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, new XAxisValueTypeString("col1"))
    val plotCol2 = plotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, new XAxisValueTypeString("col2"))

    assertPlotXYAxisValues(Seq(1,2,3), Seq(101,102,103), plotCol1.series.head)
    assertPlotXYAxisValues(Seq(1,2), Seq(201,202), plotCol2.series.head)
  }

  test("mapPieChart") {
    val data = Seq(
      ("c1", 1),
      ("c2", 2),
      ("c3", 3)
    )

    data.plotPie(unitTestsDevice)

    val chart = SPlotToXChartMapper.mapPlotToXChart(getLastPlot)

    assert(chart.isInstanceOf[PieChart])
    val pieChart = chart.asInstanceOf[PieChart]
    val seriesMap = pieChart.getSeriesMap
    assert(seriesMap.get("c1").getValue.equals(new java.lang.Integer(1)))
    assert(seriesMap.get("c2").getValue.equals(new java.lang.Integer(2)))
  }

}
