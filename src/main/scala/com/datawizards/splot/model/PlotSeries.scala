package com.datawizards.splot.model

import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, YAxisValues}

class PlotSeries (
  val name: String,
  val xValues: XAxisValues,
  val yValues: YAxisValues
) {

  override def toString: String =
    s"Series($name, $xValues, $yValues)"

  def sorted: PlotSeries = {
    val (xSorted, ySorted) = (xValues.values zip yValues.values)
        .toSeq
        .sortWith{case ((x1,_),(x2,_)) => x1 < x2}
        .unzip

    new PlotSeries(
      name = name,
      xValues = PlotAxisValues.createXAxisValues(xSorted),
      yValues = PlotAxisValues.createYAxisValues(ySorted)
    )
  }

}
