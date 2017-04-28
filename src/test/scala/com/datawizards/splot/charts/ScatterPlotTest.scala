package com.datawizards.splot.charts

import com.datawizards.splot.SPlotBaseTest
import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.{PlotAxisValues, PlotType}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScatterPlotTest extends SPlotBaseTest {
  test("Scatter") {
    val xs = Seq(1.0, 2.0, 3.0)
    val ys = xs.map(x => x*x)
    val data = xs zip ys

    data.plotScatter(unitTestsDevice)

    val plot = getLastPlot

    assertResult(PlotType.Scatter) {
      plot.plotType
    }

    assertResult(PlotAxisValues.createXAxisValuesDouble(xs), "x values") {
      plot.series.head.xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesDouble(ys), "y values") {
      plot.series.head.yValues
    }
  }

  test("IterablePairOfXYAxis.plotScatter(x,y)") {
    Seq((1,11),(2,12)).plotScatter()
    assert(getLastPlot.plotType == PlotType.Scatter)
    assertPlotXYAxisValues(Seq(1, 2), Seq(11,12), getLastPlotFirstSeries)
  }

}
