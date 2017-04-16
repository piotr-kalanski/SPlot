package com.datawizards.splot.device

import scala.collection.JavaConversions._
import com.datawizards.splot.mapper.SPlotToXChartMapper
import com.datawizards.splot.model.{Plot, PlotsGrid}
import org.knowm.xchart._

object DeviceTypes {

  val popupWindowDevice = PopupWindowDevice

  object PopupWindowDevice extends Device {

    override def plot(plot: Plot): Unit = {
      val chart = SPlotToXChartMapper.mapPlotToXChart(plot)
      new SwingWrapper(chart).displayChart()
    }

    override def plot(plotsGrid: PlotsGrid): Unit = {
      val charts = SPlotToXChartMapper.mapPlotsGridToXChart(plotsGrid)
      new SwingWrapper(charts, plotsGrid.rows, plotsGrid.cols).displayChartMatrix()
    }

  }

}
