package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.theme.PlotTheme
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ThemeCustomizationsTest extends SPlotBaseTest {
  val data = Seq(1.0, 4.0, 9.0)

  test("Change theme") {
    data
      .buildPlot()
      .bar()
      .theme(PlotTheme.GGPlot2)
      .display()

    assert(getLastPlot.theme == PlotTheme.GGPlot2)

    data
      .buildPlot()
      .bar()
      .theme(PlotTheme.Matlab)
      .display()

    assert(getLastPlot.theme == PlotTheme.Matlab)
  }

}
