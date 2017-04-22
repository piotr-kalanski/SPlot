package com.datawizards.splot

import com.datawizards.splot.builders.PlotBuilder
import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.device.Device
import com.datawizards.splot.model.{Plot, PlotAxisValues, PlotSeries, PlotsGrid}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

trait SPlotBaseTest extends FunSuite with BeforeAndAfterAll {
  private var lastPlot: Plot = _
  private var lastPlotsGrid: PlotsGrid = _

  object UnitTestsDevice extends Device {
    override def plot(plot: Plot): Unit = lastPlot = plot

    override def plot(plotsGrid: PlotsGrid): Unit = lastPlotsGrid = plotsGrid
  }

  override def beforeAll(): Unit = {
    SPlotConfiguration.deviceType = UnitTestsDevice
  }

  /**
    * @return Last plotted plot by SPlot
    */
  def getLastPlot: Plot = lastPlot

  /**
    * @return First series of last plotted chart
    */
  def getLastPlotFirstSeries: PlotSeries = getLastPlot.series.head

  /**
    * @return Last plotted plots grid by SPlot
    */
  def getLastPlotsGrid: PlotsGrid = lastPlotsGrid

  /**
    * @return last plot series from plotted grids for provided col,row value
    */
  def getLastPlotSeriesForColRow(row: Any, col: Any): PlotSeries =
    getLastPlotsGrid.plotsMap(row, col).series.head

  /**
    * @return last plot series from plotted grids for provided col value
    */
  def getLastPlotSeriesForColumn(col: Any): PlotSeries =
    getLastPlotSeriesForColRow(PlotBuilder.DefaultSingleGroup, col)

  /**
    * @return last plot series from plotted grids for provided row value
    */
  def getLastPlotSeriesForRow(row: Any): PlotSeries =
    getLastPlotSeriesForColRow(row, PlotBuilder.DefaultSingleGroup)

  /**
    * Assert both X,Y axis values
    *
    * @param expectedX expected X values
    * @param expectedY expected Y values
    * @param series result plot series
    */
  def assertPlotXYAxisValues[X,Y](expectedX: Seq[X], expectedY: Seq[Y], series: PlotSeries): Unit = {
    val sortedSeries = series.sorted
    assertPlotXAxisValues(expectedX, sortedSeries)
    assertPlotYAxisValues(expectedY, sortedSeries)
  }

  /**
    * Assert X axis values
    *
    * @param expected expected X values
    * @param series result plot series
    */
  def assertPlotXAxisValues[T](expected: Seq[T], series: PlotSeries): Unit = {
    val xVals = expected.head match {
      case _:String => PlotAxisValues.createXAxisValuesString(expected.asInstanceOf[Seq[String]])
      case _:Int => PlotAxisValues.createXAxisValuesInt(expected.asInstanceOf[Seq[Int]])
      case _:Double => PlotAxisValues.createXAxisValuesDouble(expected.asInstanceOf[Seq[Double]])
    }

    assertResult(xVals) {
      series.xValues
    }

  }

  /**
    * Assert Y axis values
    *
    * @param expected expected Y values
    * @param series result plot series
    */
  def assertPlotYAxisValues[T](expected: Seq[T], series: PlotSeries): Unit = {
    val yVals = expected.head match {
      case _:Int => PlotAxisValues.createYAxisValuesInt(expected.asInstanceOf[Seq[Int]])
      case _:Double => PlotAxisValues.createYAxisValuesDouble(expected.asInstanceOf[Seq[Double]])
    }

    assertResult(yVals) {
      series.yValues
    }

  }
}
