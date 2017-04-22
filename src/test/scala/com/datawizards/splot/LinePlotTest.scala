package com.datawizards.splot

import java.util.{Calendar, Date}

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.{PlotAxisValues, PlotType}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LinePlotTest extends SPlotBaseTest {

  test("Line") {
    val xs = Seq(1.0, 2.0, 3.0)
    val ys = xs.map(x => x*x)
    val data = xs zip ys

    data.plotLine()

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

    data.plotLine()

    assertResult(PlotType.Line) {
      getLastPlot.plotType
    }

    assertPlotXYAxisValues(xs, ys, getLastPlotFirstSeries)
  }

  private def date(year: Int, month: Int, day: Int): Date = {
    val cal = Calendar.getInstance()
    cal.set(year, month-1, day)
    cal.getTime
  }

}
