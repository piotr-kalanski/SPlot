package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.{PlotAxisValues, PlotType}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HistogramPlotTest extends SPlotBaseTest {
  test("Histogram for numbers") {
    val data = Seq(1.0, 2.0, 2.0, 4.0, 4.0, 4.0)

    data.plotHistogram(3)

    val plot = getLastPlot

    assertResult(PlotType.Histogram) {
      plot.plotType
    }

    assertResult(PlotAxisValues.createYAxisValues(Seq(1.0, 2.0, 3.0)), "y values") {
      plot.series.head.yValues
    }
  }

  test("Histogram for categorical data") {
    val data = Seq(
      ("p1","category1"),
      ("p21","category2"),
      ("p22","category2"),
      ("p31","category3"),
      ("p32","category3"),
      ("p33","category3"),
      ("p41","category4"),
      ("p42","category4"),
      ("p43","category4"),
      ("p44","category4")
    )

    data.buildPlot().histogramForCategories(_._2).display()

    val plot = getLastPlot

    val result = plot.series.head.xValues.values zip plot.series.head.yValues.values

    def assertResultForCategory(category: String, expectedCount: Int): Unit = {
      assertResult(expectedCount, category) {
        result.filter{case (x,y) => x.value == category}.head._2.value
      }
    }

    assertResultForCategory("category1", 1)
    assertResultForCategory("category2", 2)
    assertResultForCategory("category3", 3)
    assertResultForCategory("category4", 4)

  }


  // TODO - add test for histogram by series, columns
}
