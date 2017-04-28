package com.datawizards.splot.customizations

import com.datawizards.splot.SPlotBaseTest
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
      .display(unitTestsDevice)

    assert(getLastPlot.theme == PlotTheme.GGPlot2)

    data
      .buildPlot()
      .bar()
      .theme(PlotTheme.Matlab)
      .display(unitTestsDevice)

    assert(getLastPlot.theme == PlotTheme.Matlab)

    data
      .buildPlot()
      .bar()
      .theme(PlotTheme.XChart)
      .display(unitTestsDevice)

    assert(getLastPlot.theme == PlotTheme.XChart)

    data
      .buildPlot()
      .bar()
      .theme(PlotTheme.SPlot)
      .display(unitTestsDevice)

    assert(getLastPlot.theme == PlotTheme.SPlot)
  }

}
