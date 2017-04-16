package com.datawizards.splot.model

import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, YAxisValues}
import com.datawizards.splot.model.PlotType.PlotType

class Plot (
  val plotType: PlotType,
  val width: Int,
  val height: Int,
  val title: String,
  val xTitle: String,
  val yTitle: String,
  val series: PlotSeries,
  val legendVisible: Boolean
) {

  def xValues: XAxisValues = series.xValues

  def yValues: YAxisValues = series.yValues

  def seriesName: String = series.name

  override def toString: String = {
    s"Plot($plotType, $width, $height, $title, $xTitle, $yTitle, $series)"
  }
}
