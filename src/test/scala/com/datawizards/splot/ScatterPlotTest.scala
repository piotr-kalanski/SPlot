package com.datawizards.splot

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import com.datawizards.splot.implicits._
import com.datawizards.splot.model.PlotType

@RunWith(classOf[JUnitRunner])
class ScatterPlotTest extends SPlotBaseTest {
  test("Scatter") {
    val xs = Seq(1.0, 2.0, 3.0)
    val ys = xs.map(x => x*x)
    val data = xs zip ys

    data.plotScatter(_._1, _._2)

    val plot = getLastPlot

    assertResult(PlotType.Scatter) {
      plot.plotType
    }

    assertResult(xs, "x values") {
      plot.xValues
    }

    assertResult(ys, "y values") {
      plot.yValues
    }
  }
}
