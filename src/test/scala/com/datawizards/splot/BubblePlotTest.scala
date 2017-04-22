package com.datawizards.splot

import com.datawizards.splot.api.implicits._
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
      .display()


    assertResult(PlotType.Bubble) {
      getLastPlot.plotType
    }

    assertPlotXYZAxisValues(xs, ys, zs, getLastPlotFirstSeries)
  }

}
