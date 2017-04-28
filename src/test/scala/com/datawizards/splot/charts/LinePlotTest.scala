package com.datawizards.splot.charts

import java.util.{Calendar, Date}

import com.datawizards.splot.SPlotBaseTest
import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.PlotType
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LinePlotTest extends SPlotBaseTest {

  test("Line") {
    val xs = Seq(1.0, 2.0, 3.0)
    val ys = xs.map(x => x*x)
    val data = xs zip ys

    data.plotLine(unitTestsDevice)

    val plot = getLastPlot

    assertResult(PlotType.Line) {
      plot.plotType
    }

    assertPlotXYAxisValues(xs, ys, getLastPlotFirstSeries)
  }

  test("Timeseries line") {
    val xs = Seq(date(2017,1,1),date(2017,1,2),date(2017,1,3))
    val ys = Seq(1.0, 2.0, 3.0)
    val data = xs zip ys

    data.plotLine(unitTestsDevice)

    assertResult(PlotType.Line) {
      getLastPlot.plotType
    }

    assertPlotXYAxisValues(xs, ys, getLastPlotFirstSeries)
  }

  test("IterablePlot[T].plotLine(x)") {
    Seq((11,"a"),(12,"b")).plotLine(_._1)
    assert(getLastPlot.plotType == PlotType.Line)
    assertPlotXYAxisValues(Seq(1,2), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterablePlot[T].plotLine(x,y)") {
    Seq((11,"a"),(12,"b")).plotLine(_._2, _._1)
    assert(getLastPlot.plotType == PlotType.Line)
    assertPlotXYAxisValues(Seq("a", "b"), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterableDoublePlot.plotLine()") {
    Seq(11.0, 12.0).plotLine()
    assert(getLastPlot.plotType == PlotType.Line)
    assertPlotXYAxisValues(Seq(1,2), Seq(11.0,12.0), getLastPlotFirstSeries)
  }

  test("IterableIntPlot.plotLine()") {
    Seq(11, 12).plotLine()
    assert(getLastPlot.plotType == PlotType.Line)
    assertPlotXYAxisValues(Seq(1,2), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterableIntPlot.plotLine(device)") {
    Seq(11, 12).plotLine(unitTestsDevice)
    assert(getLastPlot.plotType == PlotType.Line)
    assertPlotXYAxisValues(Seq(1,2), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterablePairOfXYAxis.plotLine(x,y)") {
    Seq(("a",11),("b",12)).plotLine()
    assert(getLastPlot.plotType == PlotType.Line)
    assertPlotXYAxisValues(Seq("a", "b"), Seq(11,12), getLastPlotFirstSeries)
  }

  private def date(year: Int, month: Int, day: Int): Date = {
    val cal = Calendar.getInstance()
    cal.set(year, month-1, day)
    cal.getTime
  }

}
