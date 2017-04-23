package com.datawizards.splot.model

import com.datawizards.splot.calculations.PlotSeriesCalculator
import com.datawizards.splot.model.PlotType.PlotType
import com.datawizards.splot.theme.PlotTheme

object Plot {
  def apply[T] (
                 plotType: PlotType,
                 width: Int,
                 height: Int,
                 title: String,
                 xTitle: String,
                 yTitle: String,
                 data: Iterable[T],
                 plotSeriesCalculator: PlotSeriesCalculator[T],
                 seriesGroupFunction: T => Any,
                 legendVisible: Option[Boolean],
                 theme: PlotTheme
  ): Plot = {

    val dataGrouped = data.groupBy(seriesGroupFunction)

    val series = dataGrouped.map{
      case (group, values) => plotSeriesCalculator(group.toString, values)
    }

    new Plot(
      plotType = plotType,
      width = width,
      height = height,
      title = title,
      xTitle = xTitle,
      yTitle = yTitle,
      series = series,
      legendVisible = legendVisible,
      theme = theme
    )
  }

}

class Plot (
  val plotType: PlotType,
  val width: Int,
  val height: Int,
  val title: String,
  val xTitle: String,
  val yTitle: String,
  val series: Iterable[PlotSeries],
  val legendVisible: Option[Boolean],
  val theme: PlotTheme
) {

  override def toString: String = {
    s"Plot($plotType, $width, $height, $title, $xTitle, $yTitle, $series)"
  }

  def findSeriesByName(seriesName: String): PlotSeries =
    series.filter(_.name == seriesName).head
}
