package com.datawizards.splot.mapper

import com.datawizards.splot.model.PlotAxisValues._
import com.datawizards.splot.model.{Plot, PlotType, PlotsGrid}
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle
import org.knowm.xchart.internal.Series
import org.knowm.xchart._
import org.knowm.xchart.internal.chartpart.Chart
import org.knowm.xchart.style._

import scala.collection.JavaConversions._

object SPlotToXChartMapper {

  def mapPlotToXChart(plot: Plot): Chart[_ <: Styler, _ <: Series] = {
    plot.plotType match {
      case PlotType.Bar => mapCategoryChart(plot)
      case PlotType.Scatter => mapScatterChart(plot)
      case PlotType.Line => mapLineChart(plot)
      case PlotType.Histogram => mapCategoryChart(plot)
      case PlotType.Bubble => mapBubbleChart(plot)
      case PlotType.Pie => mapPieChart(plot)
      case PlotType.Area => mapAreaChart(plot)
      case _ => throw new Exception("Unknown plot type")
    }
  }

  def mapPlotsGridToXChart(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    plotsGrid.plotType match {
      case PlotType.Bar => mapCategoryChartsGrid(plotsGrid)
      case PlotType.Scatter => mapScatterChartsGrid(plotsGrid)
      case PlotType.Line => mapLineChartsGrid(plotsGrid)
      case PlotType.Histogram => mapCategoryChartsGrid(plotsGrid)
      case PlotType.Bubble => mapBubbleChartsGrid(plotsGrid)
      case PlotType.Pie => mapPieChartsGrid(plotsGrid)
      case PlotType.Area => mapAreaChartsGrid(plotsGrid)
      case _ => throw new Exception("Unknown plot type")
    }
  }

  private def mapCategoryChartsGrid(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    val charts = plotsGrid.plots.map(mapCategoryChart).toList
    charts.foreach(ch => ch.getStyler.setLegendVisible(false))
    charts
  }

  private def mapScatterChartsGrid(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    val charts = plotsGrid.plots.map(mapScatterChart).toList
    charts.foreach(ch => ch.getStyler.setLegendVisible(false))
    charts
  }

  private def mapLineChartsGrid(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    val charts = plotsGrid.plots.map(mapLineChart).toList
    charts.foreach(ch => ch.getStyler.setLegendVisible(false))
    charts
  }

  private def mapAreaChartsGrid(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    val charts = plotsGrid.plots.map(mapAreaChart).toList
    charts.foreach(ch => ch.getStyler.setLegendVisible(false))
    charts
  }

  private def mapBubbleChartsGrid(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    val charts = plotsGrid.plots.map(mapBubbleChart).toList
    charts.foreach(ch => ch.getStyler.setLegendVisible(false))
    charts
  }

  private def mapPieChartsGrid(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    val charts = plotsGrid.plots.map(mapPieChart).toList
    charts.foreach(ch => ch.getStyler.setLegendVisible(false))
    charts
  }

  private def mapCategoryChart(plot: Plot): CategoryChart = {
    val chart = new CategoryChartBuilder().build()

    for(series <- plot.series)
      chart.addSeries(series.name, mapXAxisValues(series.xValues), mapYAxisValues(series.yValues))

    mapPlotToChart(plot, chart)

    chart
  }

  private def mapBubbleChart(plot: Plot): BubbleChart = {
    val chart = new BubbleChartBuilder().build()

    for(series <- plot.series)
      chart.addSeries(series.name, mapXAxisValues(series.xValues), mapYAxisValues(series.yValues), mapYAxisValues(series.zValues))

    mapPlotToChart(plot, chart)

    chart
  }

  private def mapPieChart(plot: Plot): PieChart = {
    val chart = new PieChartBuilder().build()

    val seriesCount = plot.series.size
    for(series <- plot.series) {
      for((x,y) <- series.xValues.values zip series.yValues.values) {
        val name = if(seriesCount == 1) x.toString else series.name + " " + x.toString
        chart.addSeries(name, mapYAxisValueType(y))
      }
    }

    mapPlotToChart(plot, chart)

    chart
  }

  private def mapLineChart(plot: Plot): XYChart = mapXYChart(plot)

  private def mapScatterChart(plot: Plot): XYChart = {
    val chart = mapXYChart(plot)
    chart.getStyler.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter)
    chart
  }

  private def mapAreaChart(plot: Plot): XYChart = {
    val chart = mapXYChart(plot)
    chart.getStyler.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area)
    chart
  }

  private def mapXYChart(plot: Plot): XYChart = {
    val chart = new XYChartBuilder().build()

    for(series <- plot.series)
      chart.addSeries(series.name, mapXAxisValues(series.xValues), mapYAxisValues(series.yValues))

    mapPlotToChart(plot, chart)

    chart
  }

  private def mapPlotToChart(plot: Plot, chart: Chart[_, _]): Unit = {
    chart.setWidth(plot.width)
    chart.setHeight(plot.height)
    chart.setTitle(plot.title)
    chart.setXAxisTitle(plot.xTitle)
    chart.setYAxisTitle(plot.yTitle)

    plot.theme(chart)

    val styler: Styler = chart match {
      case c:XYChart => c.getStyler
      case c:CategoryChart => c.getStyler
      case c:BubbleChart => c.getStyler
      case c:PieChart => c.getStyler
    }

    plot.legendVisible match {
      case Some(b:Boolean) => styler.setLegendVisible(b)
      case None => ()
    }
  }

  private def mapXAxisValues(plotAxisValues: XAxisValues): java.util.List[_] =
    plotAxisValues.values.map(x => x.value).toList

  private def mapYAxisValues(plotAxisValues: YAxisValues): java.util.List[_ <: Number] = {
    plotAxisValues
      .values
      .map(mapYAxisValueType)
      .toList
  }

  private def mapYAxisValueType(y: YAxisValueType): Number = y.value match {
    case i:Int => new java.lang.Integer(i)
    case d:Double => new java.lang.Double(d)
    case _ => throw new Exception("Not supported type.")
  }

}
