package com.datawizards.splot.builders

import scala.collection.JavaConversions._
import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.mapper.SPlotToXChartMapper
import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, XAxisValueType, YAxisValueType, YAxisValues}
import com.datawizards.splot.model.PlotType.PlotType
import com.datawizards.splot.model._
import org.knowm.xchart._

object PlotBuilder {
  val DefaultSingleGroup = ""
  val DefaultHistogramBins = 20

  def apply[T](data: Iterable[T]): PlotBuilder[T] = new PlotBuilder[T](data)
}

class PlotBuilder[T](data: Iterable[T]) {
  private var plotType: PlotType = _
  private var width = SPlotConfiguration.DefaultWidth
  private var height = SPlotConfiguration.DefaultHeight
  private var title: String = ""
  private var xTitle: String = "x"
  private var yTitle: String = "y"
  private var xValues: XAxisValues = _
  private var yValues: YAxisValues = _
  private var gridPlot = false
  private var colsGroupFunction: T => Any = x => PlotBuilder.DefaultSingleGroup
  private var rowsGroupFunction: T => Any = x => PlotBuilder.DefaultSingleGroup
  private var seriesName: String = "y"
  private var legendVisible: Boolean = true

  /**
    * Select bar chart
    *
    * @param values function mapping element of collection to values of bar chart
    */
  def bar(values: T => YAxisValueType): this.type = {
    plotType = PlotType.Bar
    mapValues(values)
    this
  }

  /**
    * Select bar chart
    *
    * @param x function mapping element of collection to x values
    * @param y function mapping element of collection to y values
    */
  def bar(x: T => XAxisValueType, y: T => YAxisValueType): this.type = {
    plotType = PlotType.Bar
    mapXY(x, y)
    this
  }

  /**
    * Select scatter chart
    *
    * @param x function mapping element of collection to x values
    * @param y function mapping element of collection to y values
    */
  def scatter(x: T => XAxisValueType, y: T => YAxisValueType): this.type = {
    plotType = PlotType.Scatter
    mapXY(x, y)
    this
  }

  /**
    * Select line chart
    *
    * @param values function mapping element of collection to values of line chart
    */
  def line(values: T => YAxisValueType): this.type = {
    plotType = PlotType.Line
    mapValues(values)
    this
  }

  /**
    * Select line chart
    *
    * @param x function mapping element of collection to x values
    * @param y function mapping element of collection to y values
    */
  def line(x: T => XAxisValueType, y: T => YAxisValueType): this.type = {
    plotType = PlotType.Line
    mapXY(x, y)
    this
  }

  /**
    * Select histogram chart
    *
    * @param values function mapping element of collection to values
    * @param bins number of bins for histogram
    */
  def histogram(values: T => Double, bins: Int = PlotBuilder.DefaultHistogramBins): this.type = {
    plotType = PlotType.Histogram
    val rawValues = data.map(values).map(v => new java.lang.Double(v))
    val histogram = new Histogram(rawValues, bins)
    xValues = PlotAxisValues.createXAxisValuesDouble(histogram.getxAxisData().toIterable.map(d => d.toDouble))
    yValues = PlotAxisValues.createYAxisValuesDouble(histogram.getyAxisData().toIterable.map(d => d.toDouble))
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
    * Change chart width
    *
    * @param width new chart width
    */
  def width(width: Int): this.type = {
    this.width = width
    this
  }

  /**
    * Change chart height
    *
    * @param height new chart height
    */
  def height(height: Int): this.type = {
    this.height = height
    this
  }

  /**
    * Change chart size - width and height
    *
    * @param height new chart width, height
    */
  def size(width: Int, height: Int): this.type = {
    this.width = width
    this.height = height
    this
  }

  /**
    * Set function that will group input collection and for each group display dedicated chart in column
    *
    * @param cols data grouping function
    */
  def colsBy(cols: T => Any): this.type = {
    gridPlot = true
    this.colsGroupFunction = cols
    this
  }

  /**
    * Set function that will group input collection and for each group display dedicated chart in row
    *
    * @param rows data grouping function
    */
  def rowsBy(rows: T => Any): this.type = {
    gridPlot = true
    this.rowsGroupFunction = rows
    this
  }

  /**
    * Change series name
    *
    * @param seriesName new series name
    */
  def seriesName(seriesName: String): this.type = {
    this.seriesName = seriesName
    this
  }

  /**
    * Customise legend visibility
    *
    * @param visible legend visibility
    */
  def legendVisible(visible: Boolean): this.type = {
    this.legendVisible = visible
    this
  }

  /**
    * Display plot using all selected configuration values
    */
  def display(): Unit = {
    require(plotType != null, "Plot type not selected")
    val device = SPlotConfiguration.deviceType
    if(gridPlot) device.plot(buildPlotsGrid())
    else device.plot(buildPlot())
  }

  /**
    * Save plot to file
    *
    * @param path file path
    * @param imageFormat image format
    */
  def save(path: String, imageFormat: ImageFormat): Unit = {
    if(gridPlot) savePlotsGrid(buildPlotsGrid(), path, imageFormat)
    else savePlot(buildPlot(), path, imageFormat)
  }

  private def buildPlot(): Plot = {
    new Plot(
      plotType = plotType,
      width = width,
      height = height,
      title = title,
      xTitle = xTitle,
      yTitle = yTitle,
      series = new PlotSeries(
        name = seriesName,
        xValues = xValues,
        yValues = yValues
      ),
      legendVisible = legendVisible
    )
  }

  private def buildPlotsGrid(): PlotsGrid = {
    PlotsGrid(
      data = data,
      plotType = plotType,
      xValues = xValues,
      yValues = yValues,
      colsGroupFunction = colsGroupFunction,
      rowsGroupFunction = rowsGroupFunction,
      totalWidth = width,
      totalHeight = height
    )
  }

  private def mapXY(x: T => XAxisValueType, y: T => YAxisValueType): Unit = {
    yValues = PlotAxisValues.createYAxisValues(data.map(y))
    xValues = PlotAxisValues.createXAxisValues(data.map(x))
  }

  private def mapValues(values: T => YAxisValueType): Unit = {
    xValues = PlotAxisValues.createXAxisValuesInt(data.zipWithIndex.map(1 + _._2))
    yValues = PlotAxisValues.createYAxisValues(data.map(values))
  }

  private def savePlot(plot: Plot, path: String, imageFormat: ImageFormat): Unit = {
    val chart = SPlotToXChartMapper.mapPlotToXChart(plot)

    imageFormat match {
      case bif: BitmapImageFormat => BitmapEncoder.saveBitmap(chart, path, bif.bitmapFormat)
      case vif: VectorGraphicsImageFormat => VectorGraphicsEncoder.saveVectorGraphic(chart, path, vif.vectorGraphicsFormat)
    }
  }

  private def savePlotsGrid(plotsGrid: PlotsGrid, path: String, imageFormat: ImageFormat): Unit = {
    val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plotsGrid)

    imageFormat match {
      case bif: BitmapImageFormat => BitmapEncoderExtension.saveBitmap(charts, plotsGrid.rows, plotsGrid.cols, path, bif.bitmapFormat)
      case vif: VectorGraphicsImageFormat => VectorGraphicsEncoderExtension.saveVectorGraphic(charts, plotsGrid.rows, plotsGrid.cols, path, vif.vectorGraphicsFormat)
    }
  }
}

class PlotBuilderForDouble(data: Iterable[Double]) extends PlotBuilder[Double](data) {

  /**
    * Select bar chart
    */
  def bar(): this.type = bar(x => x)

  /**
    * Select histogram
    *
    */
  def histogram(): this.type = {
    histogram(x => x)
  }

  /**
    * Select histogram
    *
    * @param bins number of bins
    */
  def histogram(bins: Int): this.type = {
    histogram(x => x, bins)
  }

}

class PlotBuilderForInt(data: Iterable[Int]) extends PlotBuilder[Int](data) {

  /**
    * Select bar chart
    */
  def bar(): this.type = bar(x => x)

  /**
    * Select histogram
    *
    */
  def histogram(): this.type = {
    histogram(x => x)
  }

  /**
    * Select histogram
    *
    * @param bins number of bins
    */
  def histogram(bins: Int): this.type = {
    histogram(x => x, bins)
  }
}

class PlotBuilderForPairOfXYAxis(data: Iterable[(XAxisValueType, YAxisValueType)]) extends PlotBuilder[(XAxisValueType, YAxisValueType)](data) {

  /**
    * Select line chart
    */
  def bar(): this.type = {
    bar(_._1, _._2)
    this
  }

  /**
    * Select scatter chart
    */
  def scatter(): this.type = {
    scatter(_._1, _._2)
    this
  }

  /**
    * Select line chart
    */
  def line(): this.type = {
    line(_._1, _._2)
    this
  }

}
