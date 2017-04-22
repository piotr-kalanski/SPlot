package com.datawizards.splot.model

import com.datawizards.splot.calculations.XYValuesCalculator
import com.datawizards.splot.model.PlotType.PlotType
import com.datawizards.splot.theme.PlotTheme

import scala.collection.mutable.ListBuffer

object PlotsGrid {

  def apply[T] (
                 data: Iterable[T],
                 plotType: PlotType,
                 xyValuesCalculator: XYValuesCalculator[T],
                 colsGroupFunction: T => Any,
                 rowsGroupFunction: T => Any,
                 seriesGroupFunction: T => Any,
                 totalWidth: Int,
                 totalHeight: Int,
                 theme: PlotTheme
  ): PlotsGrid = {

    val dataGrouped = data
      .groupBy(point => (rowsGroupFunction(point), colsGroupFunction(point)))

    val rowsCount = dataGrouped.keys.map(_._1).toSeq.distinct.size
    val colsCount = dataGrouped.keys.map(_._2).toSeq.distinct.size

    val plotsMap = dataGrouped.map{case (group, values) =>
        val rowStr = group._1.toString
        val colStr = group._2.toString

        val dataGroupedBySeries = values.groupBy(seriesGroupFunction)

        val series = dataGroupedBySeries.map { case (seriesGroup, valuesWithinSeriesGroup) =>
          val (xValues, yValues) = xyValuesCalculator(valuesWithinSeriesGroup)
          val groupStr = seriesGroup.toString

          new PlotSeries(
            name = groupStr,
            xValues = PlotAxisValues.createXAxisValues(xValues),
            yValues = PlotAxisValues.createYAxisValues(yValues)
          )
        }

        group -> new Plot(
          plotType = plotType,
          width = totalWidth / colsCount,
          height = totalHeight / rowsCount,
          title = if(rowStr == "") colStr else if(colStr == "") rowStr else rowStr + " | " + colStr,
          xTitle = "",
          yTitle = "",
          series = series,
          legendVisible = None,
          theme = theme
        )
      }

    new PlotsGrid(plotsMap, plotType)
  }

}

class PlotsGrid(val plotsMap: Map[(Any, Any), Plot], val plotType: PlotType) {

  def apply(row: Any, col: Any): Plot = {
    plotsMap((row, col))
  }

  def plots: Iterable[Plot] = {
    val keys = plotsMap.keys.unzip
    val rows = keys._1.toSeq.distinct
    val cols = keys._2.toSeq.distinct

    val listBuffer = new ListBuffer[Plot]()
    for(r <- rows)
      for(c <- cols)
        listBuffer += apply(r,c)
    listBuffer
  }

  lazy val rows: Int = plotsMap.keys.map(_._1).toSeq.distinct.size

  lazy val cols: Int = plotsMap.keys.map(_._2).toSeq.distinct.size

  override def toString: String = plotsMap.toString
}
