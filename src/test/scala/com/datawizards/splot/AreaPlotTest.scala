package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.PlotType
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AreaPlotTest extends SPlotBaseTest {

  test("Area") {
    val xs = Seq(1.0, 2.0, 3.0)
    val ys = xs.map(x => x*x)
    val data = xs zip ys

    data.plotArea(unitTestsDevice)

    val plot = getLastPlot

    assertResult(PlotType.Area) {
      plot.plotType
    }

    assertPlotXYAxisValues(xs, ys, getLastPlotFirstSeries)
  }

  test("IterablePlot[T].plotArea(x)") {
    Seq((11,"a"),(12,"b")).plotArea(_._1)
    assert(getLastPlot.plotType == PlotType.Area)
    assertPlotXYAxisValues(Seq(1,2), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterablePlot[T].plotArea(x,y)") {
    Seq((11,"a"),(12,"b")).plotArea(_._2, _._1)
    assert(getLastPlot.plotType == PlotType.Area)
    assertPlotXYAxisValues(Seq("a", "b"), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterableDoublePlot.plotArea()") {
    Seq(11.0, 12.0).plotArea()
    assert(getLastPlot.plotType == PlotType.Area)
    assertPlotXYAxisValues(Seq(1,2), Seq(11.0,12.0), getLastPlotFirstSeries)
  }

  test("IterableIntPlot.plotArea()") {
    Seq(11, 12).plotArea()
    assert(getLastPlot.plotType == PlotType.Area)
    assertPlotXYAxisValues(Seq(1,2), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterableIntPlot.plotArea(device)") {
    Seq(11, 12).plotArea(unitTestsDevice)
    assert(getLastPlot.plotType == PlotType.Area)
    assertPlotXYAxisValues(Seq(1,2), Seq(11,12), getLastPlotFirstSeries)
  }

  test("IterablePairOfXYAxis.plotArea(x,y)") {
    Seq(("a",11),("b",12)).plotArea()
    assert(getLastPlot.plotType == PlotType.Area)
    assertPlotXYAxisValues(Seq("a", "b"), Seq(11,12), getLastPlotFirstSeries)
  }

}
