package com.datawizards.splot.device

import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.model.{Plot, PlotType}
import com.datawizards.splot.theme.PlotThemes

import org.knowm.xchart.style.Styler.ChartTheme
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper}

import scala.collection.JavaConversions._

object DeviceTypes {

  val PopupWindowDevice = new Device {

    override def plot(plot: Plot): Unit = {
      plot.plotType match {
        case PlotType.Bar => displayCategoryChart(plot)
        case _ => throw new Exception("Unknown plot type")
      }

    }
  }

  val default = PopupWindowDevice

  private def displayCategoryChart(plot: Plot): Unit = {
    new SwingWrapper[CategoryChart](buildCategoryChart(plot)).displayChart()
  }

  private def buildCategoryChart(plot: Plot): CategoryChart = {
    val chart = new CategoryChartBuilder()
      .width(plot.width)
      .height(plot.height)
      .title(plot.title)
      .xAxisTitle(plot.xTitle)
      .yAxisTitle(plot.yTitle)
      .theme(getChartTheme)
      .build()

    //TODO - series name customization
    chart.addSeries("x", plot.xValues.toArray, plot.yValues.toArray)

    chart
  }

  private def getChartTheme: ChartTheme = SPlotConfiguration.plotTheme match {
    case PlotThemes.ggPlotTheme => ChartTheme.GGPlot2
    case _ => throw new Exception("Unknown plot theme")
  }

}
