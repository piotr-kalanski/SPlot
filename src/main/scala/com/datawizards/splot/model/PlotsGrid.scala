package com.datawizards.splot.model

import com.datawizards.splot.model.PlotAxisValues.{XAxisValues, YAxisValues}
import com.datawizards.splot.model.PlotType.PlotType

import scala.collection.mutable.ListBuffer

object PlotsGrid {

  def apply[T] (
                 data: Iterable[T],
                 plotType: PlotType,
                 xValues: XAxisValues,
                 yValues: YAxisValues,
                 colsGroupFunction: T => Any,
                 rowsGroupFunction: T => Any,
                 totalWidth: Int,
                 totalHeight: Int
  ): PlotsGrid = {
    val dataGrouped = (data zip (xValues.values zip yValues.values))
      .map{case (point,(x,y)) => ((rowsGroupFunction(point), colsGroupFunction(point)), (x,y)) }
      .groupBy{case (group,_) => group}

    val rowsCount = dataGrouped.keys.map(_._1).toSeq.distinct.size
    val colsCount = dataGrouped.keys.map(_._2).toSeq.distinct.size

    val plotsMap = dataGrouped.map{case (group, values) =>
        val (xValues, yValues) = values.map{case (_, (x,y)) => (x,y)}.unzip
        val rowStr = group._1.toString
        val colStr = group._2.toString

        group -> new Plot(
          plotType = plotType,
          width = totalWidth / colsCount,
          height = totalHeight / rowsCount,
          title = if(rowStr == "") colStr else if(colStr == "") rowStr else rowStr + " | " + colStr,
          xTitle = "",
          yTitle = "",
          xValues = PlotAxisValues.createXAxisValues(xValues),
          yValues = PlotAxisValues.createYAxisValues(yValues)
        )
      }

    new PlotsGrid(plotsMap, plotType)
  }

}

class PlotsGrid(plotsMap: Map[(Any, Any), Plot], val plotType: PlotType) {

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

}
