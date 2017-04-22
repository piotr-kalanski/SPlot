package com.datawizards.splot.model

import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, YAxisValues}

class PlotSeries (
  val name: String,
  val xValues: XAxisValues,
  val yValues: YAxisValues,
  val zValues: YAxisValues
) {

  override def toString: String =
    s"Series($name, $xValues, $yValues, $zValues)"

  def sorted: PlotSeries = {
    val (xSorted, ySorted, zSorted) = (xValues.values zip yValues.values).zipAll(zValues.values,null,null)
        .map{case ((x,y),z) => (x,y,z) }
        .toSeq
        .sortWith{case ((x1,_,_),(x2,_,_)) => x1 < x2}
        .unzip3

    new PlotSeries(
      name = name,
      xValues = PlotAxisValues.createXAxisValues(xSorted),
      yValues = PlotAxisValues.createYAxisValues(ySorted),
      zValues = PlotAxisValues.createYAxisValues(zSorted)
    )
  }

}
