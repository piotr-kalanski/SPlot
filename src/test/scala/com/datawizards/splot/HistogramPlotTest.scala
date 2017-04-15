package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.{PlotAxisValues, PlotType}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HistogramPlotTest extends SPlotBaseTest {
  test("Histogram") {
    val data = Seq(1.0, 2.0, 2.0, 4.0, 4.0, 4.0)

    data.plotHistogram(3)

    val plot = getLastPlot

    assertResult(PlotType.Histogram) {
      plot.plotType
    }

    assertResult(PlotAxisValues.createYAxisValues(Seq(1.0, 2.0, 3.0)), "y values") {
      plot.yValues
    }
  }

}
