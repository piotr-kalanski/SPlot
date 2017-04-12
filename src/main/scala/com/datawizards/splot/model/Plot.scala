package com.datawizards.splot.model

import com.datawizards.splot.model.PlotType.PlotType

class Plot (
  val plotType: PlotType,
  val width: Int,
  val height: Int,
  val title: String,
  val xTitle: String,
  val yTitle: String,
  val xValues: Iterable[Double], //TODO - support also Int, String and Date
  val yValues: Iterable[Double]
) {

  override def toString: String = {
    s"Plot($plotType, $width, $height, $title, $xTitle, $yTitle, $xValues, $yValues)"
  }
}
