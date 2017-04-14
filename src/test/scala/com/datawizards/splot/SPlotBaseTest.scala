package com.datawizards.splot

import com.datawizards.splot.configuration.SPlotConfiguration
import com.datawizards.splot.device.Device
import com.datawizards.splot.model.{Plot, PlotsGrid}
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
    * @return Last plotted plots grid by SPlot
    */
  def getLastPlotsGrid: PlotsGrid = lastPlotsGrid
}
