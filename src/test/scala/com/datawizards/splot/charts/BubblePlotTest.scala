package com.datawizards.splot.charts

import com.datawizards.splot.SPlotBaseTest
import com.datawizards.splot.api.implicits._
import com.datawizards.splot.builders.PlotBuilder
import com.datawizards.splot.model.PlotType
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BubblePlotTest extends SPlotBaseTest {

  test("Bubble") {
    val xs = Seq(1, 2, 3)
    val ys = Seq(2, 5, 4)
    val zs = Seq(1.0, 4.0, 2.5)
    val data = ((xs zip ys) zip zs).map(p => (p._1._1, p._1._2, p._2))

    data
      .buildPlot()
      .bubble(_._1, _._2, _._3)
      .display(unitTestsDevice)


    assertResult(PlotType.Bubble) {
      getLastPlot.plotType
    }

    assertPlotXYZAxisValues(xs, ys, zs, getLastPlotFirstSeries)
  }

  test("Bubble - multiple columns") {
    val data = Seq(
      ("col1",1,1,111.0),
      ("col1",1,2,112.0),
      ("col1",2,1,121.0),
      ("col2",10,10,211.0),
      ("col2",10,20,212.0),
      ("col2",20,10,221.0)
    )

    data
      .buildPlot()
      .bubble(_._2, _._3, _._4)
      .colsBy(_._1)
      .display(unitTestsDevice)

    val plotsGrid = getLastPlotsGrid

    val plotCol1 = plotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, "col1")
    val plotCol2 = plotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, "col2")

    assertPlotXYZAxisValues(Seq(1,1,2), Seq(1,2,1), Seq(111.0,112.0,121.0), plotCol1.series.head)
    assertPlotXYZAxisValues(Seq(10,10,20), Seq(10,20,10), Seq(211.0,212.0,221.0), plotCol2.series.head)
  }

}
