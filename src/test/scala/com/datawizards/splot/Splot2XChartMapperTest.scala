package com.datawizards.splot

import com.datawizards.splot.mapper.SPlotToXChartMapper
import com.datawizards.splot.model.{Plot, PlotAxisValues, PlotSeries, PlotType}
import com.datawizards.splot.theme.PlotTheme
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.knowm.xchart._
import org.knowm.xchart.style.MatlabTheme

@RunWith(classOf[JUnitRunner])
class Splot2XChartMapperTest extends FunSuite {
  val plot = new Plot(
    plotType = PlotType.Bar,
    width = 1000,
    height = 200,
    title = "title",
    xTitle = "xtitle",
    yTitle = "ytitle",
    series = Seq(
      new PlotSeries(
        name = "series1",
        xValues = PlotAxisValues.createXAxisValues(Seq(1,2,3)),
        yValues = PlotAxisValues.createYAxisValues(Seq(20.0,21.0,22.0)),
        zValues = PlotAxisValues.createYAxisValues(Seq(30.0,31.0,32.0))
      )
    ),
    legendVisible = Some(true),
    theme = PlotTheme.Matlab
  )

  val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
  val barChart = chart.asInstanceOf[CategoryChart]

  test("Type") {
    assert(chart.isInstanceOf[CategoryChart])
  }

  test("Titles") {
    assert(chart.getTitle == plot.title)
    assert(chart.getXAxisTitle == plot.xTitle)
    assert(chart.getyYAxisTitle == plot.yTitle)
  }

  test("Size") {
    assert(chart.getWidth == plot.width)
    assert(chart.getHeight == plot.height)
  }

  test("Legend") {
    assert(chart.getStyler.isLegendVisible)
  }

  test("Theme") {
    assert(barChart.getStyler.getTheme.isInstanceOf[MatlabTheme])
  }

}
