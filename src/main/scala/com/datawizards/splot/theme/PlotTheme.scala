package com.datawizards.splot.theme

import org.knowm.xchart.internal.chartpart.Chart
import org.knowm.xchart.{BubbleChart, CategoryChart, XYChart}
import org.knowm.xchart.style._

object PlotTheme {

  object GGPlot2 extends PlotTheme {
    override def theme: Theme = new GGPlot2Theme
    override def applyAdditionalStyles(styler: Styler): Unit = {}
  }

  object Matlab extends PlotTheme {
    override def theme: Theme = new MatlabTheme
    override def applyAdditionalStyles(styler: Styler): Unit = {}
  }

  object XChart extends PlotTheme {
    override def theme: Theme = new XChartTheme
    override def applyAdditionalStyles(styler: Styler): Unit = {}
  }

  object SPlot extends PlotTheme {
    override def theme: Theme = new SPlotTheme
    override def applyAdditionalStyles(styler: Styler): Unit = {
      styler.setHasAnnotations(true)
    }
  }

}

trait PlotTheme {

  def theme: Theme

  def apply(chart: Chart[_,_]): Unit = {
    chart match {
      case c:XYChart => applyTheme(c)
      case c:CategoryChart => applyTheme(c)
      case c:BubbleChart => applyTheme(c)
    }
  }

  private def applyTheme(chart: XYChart): Unit = {
    chart.getStyler.setTheme(theme)
    applyAdditionalStyles(chart.getStyler)
  }

  private def applyTheme(chart: CategoryChart): Unit = {
    chart.getStyler.setTheme(theme)
    applyAdditionalStyles(chart.getStyler)
  }

  private def applyTheme(chart: BubbleChart): Unit = {
    chart.getStyler.setTheme(theme)
    applyAdditionalStyles(chart.getStyler)
  }

  protected def applyAdditionalStyles(styler: Styler): Unit

}