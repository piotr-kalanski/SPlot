package com.datawizards.splot.model

import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, YAxisValues}
import com.datawizards.splot.model.PlotType.PlotType

object Plot {
  def apply[T] (
    plotType: PlotType,
    width: Int,
    height: Int,
    title: String,
    xTitle: String,
    yTitle: String,
    data: Iterable[T],
    xValues: XAxisValues,
    yValues: YAxisValues,
    seriesGroupFunction: T => Any,
    legendVisible: Boolean
  ): Plot = {

    val dataGrouped = (data zip (xValues.values zip yValues.values))
      .map{case (point,(x,y)) => (seriesGroupFunction(point), (x,y)) }
      .groupBy{case (group,_) => group}

    val series = dataGrouped.map{case (group, values) =>
      val (xValues, yValues) = values.map{case (_, (x,y)) => (x,y)}.unzip
      val groupStr = group.toString

      new PlotSeries(
        name = groupStr,
        xValues = PlotAxisValues.createXAxisValues(xValues),
        yValues = PlotAxisValues.createYAxisValues(yValues)
      )
    }

    new Plot(
      plotType = plotType,
      width = width,
      height = height,
      title = title,
      xTitle = xTitle,
      yTitle = yTitle,
      series = series,
      legendVisible = legendVisible
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
  val legendVisible: Boolean
) {

  override def toString: String = {
    s"Plot($plotType, $width, $height, $title, $xTitle, $yTitle, $series)"
  }
}
