package com.datawizards.splot.builders

import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.model.PlotType.PlotType
import com.datawizards.splot.model.{Plot, PlotType}

class PlotBuilder[T](data: Iterable[T]) {

  private var plotType = PlotType.Bar
  private var width = SPlotConfiguration.DefaultWidth
  private var height = SPlotConfiguration.DefaultHeight
  private var title: String = ""
  private var xTitle: String = "x"
  private var yTitle: String = "y"
  private var xValues = Iterable[Double]()
  private var yValues = Iterable[Double]()

  def bar(values: T => Double): this.type = {
    plotType = PlotType.Bar
    yValues = data.map(values)
    xValues = data.zipWithIndex.map(_._2.toDouble)
    this
  }

  def display(): Unit = {
    SPlotConfiguration.deviceType.plot(buildPlot())
  }

  private def buildPlot(): Plot = {
    new Plot(
      plotType = plotType,
      width = width,
      height = height,
      title = title,
      xTitle = xTitle,
      yTitle = yTitle,
      xValues = xValues,
      yValues = yValues
    )
  }

}


