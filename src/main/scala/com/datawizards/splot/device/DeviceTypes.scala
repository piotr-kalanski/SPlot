package com.datawizards.splot.device

import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.model.{Plot, PlotType}
import com.datawizards.splot.theme.PlotThemes
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle
import org.knowm.xchart.style.Styler.ChartTheme
import org.knowm.xchart._

object DeviceTypes {

  val popupWindowDevice = PopupWindowDevice

  object PopupWindowDevice extends Device {

    override def plot(plot: Plot): Unit = {
      plot.plotType match {
        case PlotType.Bar => displayCategoryChart(plot)
        case PlotType.Scatter => displayScatterChart(plot)
        case PlotType.Line => displayLineChart(plot)
        case _ => throw new Exception("Unknown plot type")
      }
    }

    private def displayCategoryChart(plot: Plot): Unit = {
      new SwingWrapper[CategoryChart](buildCategoryChart(plot)).displayChart()
    }

    private def displayScatterChart(plot: Plot): Unit = {
      val chart = buildXYChart(plot)
      chart.getStyler.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter)
      new SwingWrapper[XYChart](chart).displayChart()
    }

    private def displayLineChart(plot: Plot): Unit = {
      val chart = buildXYChart(plot)
      new SwingWrapper[XYChart](chart).displayChart()
    }

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

  private def buildXYChart(plot: Plot): XYChart = {
    val chart = new XYChartBuilder()
      .width(plot.width)
      .height(plot.height)
      .title(plot.title)
      .xAxisTitle(plot.xTitle)
      .yAxisTitle(plot.yTitle)
      .theme(getChartTheme)
      .build()

    //TODO - series name customization
    chart.addSeries("xy", plot.xValues.toArray, plot.yValues.toArray)

    chart
  }

  private def getChartTheme: ChartTheme = SPlotConfiguration.plotTheme match {
    case PlotThemes.ggPlotTheme => ChartTheme.GGPlot2
    case _ => throw new Exception("Unknown plot theme")
  }

}
