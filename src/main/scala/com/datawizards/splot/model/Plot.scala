package com.datawizards.splot.model

import com.datawizards.splot.model.PlotType.PlotType

class Plot (
  val plotType: PlotType,
  val width: Int,
  val height: Int,
  val title: String,
  val xTitle: String,
  val yTitle: String,
  val series: Seq[PlotSeries],
  val legendVisible: Boolean
) {

  override def toString: String = {
    s"Plot($plotType, $width, $height, $title, $xTitle, $yTitle, $series)"
  }
}
