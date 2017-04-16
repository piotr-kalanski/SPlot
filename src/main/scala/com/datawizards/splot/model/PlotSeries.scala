package com.datawizards.splot.model

import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, YAxisValues}

class PlotSeries (
  val name: String,
  val xValues: XAxisValues,
  val yValues: YAxisValues
) {

  override def toString: String =
    s"Series($name, $xValues, $yValues)"

}
