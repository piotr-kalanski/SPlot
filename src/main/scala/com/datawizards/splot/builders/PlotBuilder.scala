package com.datawizards.splot.builders

import com.datawizards.splot.configuration.SPlotConfiguration
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

  /**
    * Select bar chart
    *
    * @param values function mapping element of collection to values of bar chart
    */
  def bar(values: T => Double): this.type = {
    plotType = PlotType.Bar
    yValues = data.map(values)
    xValues = data.zipWithIndex.map(1 + _._2.toDouble)
    this
  }

  /**
    * Select scatter chart
    *
    * @param x function mapping element of collection to x values
    * @param y function mapping element of collection to y values
    */
  def scatter(x: T => Double, y: T => Double): this.type = {
    plotType = PlotType.Scatter
    mapXY(x, y)
    this
  }

  /**
    * Select line chart
    *
    * @param x function mapping element of collection to x values
    * @param y function mapping element of collection to y values
    */
  def line(x: T => Double, y: T => Double): this.type = {
    plotType = PlotType.Line
    mapXY(x, y)
    this
  }

  /**
    * Change chart title
    *
    * @param title new chart title
    */
  def title(title: String): this.type = {
    this.title = title
    this
  }

  /**
    * Change main title and axis titles
    *
    * @param title new chart title
    * @param xTitle new x axis title
    * @param yTitle new y axis title
    * @return
    */
  def titles(title: String, xTitle: String, yTitle: String): this.type = {
    this.title = title
    this.xTitle = xTitle
    this.yTitle = yTitle
    this
  }

  /**
    * Display chart using all selected configuration values
    */
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

  private def mapXY(x: T => Double, y: T => Double): Unit = {
    yValues = data.map(y)
    xValues = data.map(x)
  }
}
