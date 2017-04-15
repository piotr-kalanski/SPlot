package com.datawizards.splot.mapper

import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.model.PlotAxisValues._
import com.datawizards.splot.model.{Plot, PlotType, PlotsGrid}
import com.datawizards.splot.theme.PlotThemes
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle
import org.knowm.xchart.internal.Series
import org.knowm.xchart._
import org.knowm.xchart.internal.chartpart.Chart
import org.knowm.xchart.style.Styler
import org.knowm.xchart.style.Styler.ChartTheme

import scala.collection.JavaConversions._

object SPlotToXChartMapper {

  def mapPlotToXChart(plot: Plot): Chart[_ <: Styler, _ <: Series] = {
    plot.plotType match {
      case PlotType.Bar => mapCategoryChart(plot)
      case PlotType.Scatter => mapScatterChart(plot)
      case PlotType.Line => mapLineChart(plot)
      case PlotType.Histogram => mapCategoryChart(plot)
      case _ => throw new Exception("Unknown plot type")
    }
  }

  def mapPlotsGridToXChart(plotsGrid: PlotsGrid): List[Chart[_ <: Styler, _ <: Series]] = {
    plotsGrid.plotType match {
      case PlotType.Bar => mapCategoryChartsGrid(plotsGrid)
      case PlotType.Scatter => mapScatterChartsGrid(plotsGrid)
      case PlotType.Line => mapLineChartsGrid(plotsGrid)
      case PlotType.Histogram => mapCategoryChartsGrid(plotsGrid)
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

  private def mapCategoryChart(plot: Plot): CategoryChart = {
    val chart = new CategoryChartBuilder()
      .width(plot.width)
      .height(plot.height)
      .title(plot.title)
      .xAxisTitle(plot.xTitle)
      .yAxisTitle(plot.yTitle)
      .theme(getChartTheme)
      .build()

    chart.addSeries(plot.seriesName, mapXAxisValues(plot.xValues), mapYAxisValues(plot.yValues))
    chart.getStyler.setLegendVisible(plot.legendVisible)

    chart
  }

  private def mapLineChart(plot: Plot): XYChart = mapXYChart(plot)

  private def mapScatterChart(plot: Plot): XYChart = {
    val chart = mapXYChart(plot)
    chart.getStyler.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter)
    chart
  }

  private def mapXYChart(plot: Plot): XYChart = {
    val chart = new XYChartBuilder()
      .width(plot.width)
      .height(plot.height)
      .title(plot.title)
      .xAxisTitle(plot.xTitle)
      .yAxisTitle(plot.yTitle)
      .theme(getChartTheme)
      .build()

    chart.addSeries(plot.seriesName, mapXAxisValues(plot.xValues), mapYAxisValues(plot.yValues))
    chart.getStyler.setLegendVisible(plot.legendVisible)

    chart
  }

  private def getChartTheme: ChartTheme = SPlotConfiguration.plotTheme match {
    case PlotThemes.ggPlotTheme => ChartTheme.GGPlot2
    case _ => throw new Exception("Unknown plot theme")
  }

  private def mapXAxisValues(plotAxisValues: XAxisValues): java.util.List[_] =
    plotAxisValues.values.map(x => x.value).toList

  private def mapYAxisValues(plotAxisValues: YAxisValues): java.util.List[_ <: Number] = {
    plotAxisValues
      .values
      .map(_.value)
      .map {
        case i:Int => new java.lang.Integer(i)
        case d:Double => new java.lang.Double(d)
        case _ => throw new Exception("Not supported type.")
      }
      .toList
  }
}
