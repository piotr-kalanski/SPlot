package com.datawizards.splot

import com.datawizards.splot.mapper.SPlotToXChartMapper
import com.datawizards.splot.model.PlotType.PlotType
import com.datawizards.splot.model._
import com.datawizards.splot.theme.PlotTheme
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.knowm.xchart._
import org.knowm.xchart.style.MatlabTheme

@RunWith(classOf[JUnitRunner])
class Splot2XChartMapperTest extends FunSuite {
  val plot = buildExamplePlot(PlotType.Bar)
  val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
  val barChart = chart.asInstanceOf[CategoryChart]

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

  test("Map bar") {
    val plot = buildExamplePlot(PlotType.Bar)
    val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
    assert(chart.isInstanceOf[CategoryChart])
  }

  test("Map line") {
   val plot = buildExamplePlot(PlotType.Line)
   val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
   assert(chart.isInstanceOf[XYChart])
  }

  test("Map scatter") {
    val plot = buildExamplePlot(PlotType.Scatter)
    val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
    assert(chart.isInstanceOf[XYChart])
  }

  test("Map area") {
    val plot = buildExamplePlot(PlotType.Area)
    val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
    assert(chart.isInstanceOf[XYChart])
  }

  test("Map bubble") {
    val plot = buildExamplePlot(PlotType.Bubble)
    val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
    assert(chart.isInstanceOf[BubbleChart])
  }

  test("Map histogram") {
    val plot = buildExamplePlot(PlotType.Histogram)
    val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
    assert(chart.isInstanceOf[CategoryChart])
  }

  test("Map bar grid") {
    val plots = buildExamplePlotsGrid(PlotType.Bar)
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plots)
    assert(charts.head.isInstanceOf[CategoryChart])
  }

  test("Map line grid") {
    val plots = buildExamplePlotsGrid(PlotType.Line)
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plots)
    assert(charts.head.isInstanceOf[XYChart])
  }

  test("Map scatter grid") {
    val plots = buildExamplePlotsGrid(PlotType.Scatter)
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plots)
    assert(charts.head.isInstanceOf[XYChart])
  }

  test("Map area grid") {
    val plots = buildExamplePlotsGrid(PlotType.Area)
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plots)
    assert(charts.head.isInstanceOf[XYChart])
  }

  test("Map bubble grid") {
    val plots = buildExamplePlotsGrid(PlotType.Bubble)
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plots)
    assert(charts.head.isInstanceOf[BubbleChart])
  }

  test("Map histogram grid") {
    val plots = buildExamplePlotsGrid(PlotType.Histogram)
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plots)
    assert(charts.head.isInstanceOf[CategoryChart])
  }

  private def buildExamplePlot(plotType: PlotType): Plot =
    new Plot(
      plotType = plotType,
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

  private def buildExamplePlotsGrid(plotType: PlotType): PlotsGrid =
    new PlotsGrid(
      Map(("","") -> buildExamplePlot(plotType)),
      plotType = plotType
    )

}
