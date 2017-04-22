package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.builders.PlotBuilder
import com.datawizards.splot.model.{PlotAxisValues, PlotSeries, PlotType}
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

    assertResultForCategory(getLastPlot.series.head, "category1", 1)
    assertResultForCategory(getLastPlot.series.head, "category2", 2)
    assertResultForCategory(getLastPlot.series.head, "category3", 3)
    assertResultForCategory(getLastPlot.series.head, "category4", 4)

  }

  test("Histograms wit many series") {
    val data = Seq(
      ("series1","category1"),
      ("series1","category2"),
      ("series1","category2"),
      ("series1","category3"),
      ("series2","category1"),
      ("series2","category2"),
      ("series2","category2"),
      ("series2","category4"),
      ("series2","category4"),
      ("series2","category4")
    )

    data.buildPlot().histogramForCategories(_._2).seriesBy(_._1).display()

    val plotSeries1 = getLastPlot.findSeriesByName("series1")
    val plotSeries2 = getLastPlot.findSeriesByName("series2")

    assertResultForCategory(plotSeries1, "category1", 1)
    assertResultForCategory(plotSeries1, "category2", 2)
    assertResultForCategory(plotSeries1, "category3", 1)
    assertResultForCategory(plotSeries2, "category1", 1)
    assertResultForCategory(plotSeries2, "category2", 2)
    assertResultForCategory(plotSeries2, "category4", 3)
  }

  test("Histograms for many columns") {
    val data = Seq(
      ("col1","category1"),
      ("col1","category2"),
      ("col1","category2"),
      ("col1","category3"),
      ("col2","category1"),
      ("col2","category2"),
      ("col2","category2"),
      ("col2","category4"),
      ("col2","category4"),
      ("col2","category4")
    )

    data.buildPlot().histogramForCategories(_._2).colsBy(_._1).display()

    val plotCol1 = getLastPlotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, "col1")
    val plotCol2 = getLastPlotsGrid.plotsMap(PlotBuilder.DefaultSingleGroup, "col2")

    assertResultForCategory(plotCol1.series.head, "category1", 1)
    assertResultForCategory(plotCol1.series.head, "category2", 2)
    assertResultForCategory(plotCol1.series.head, "category3", 1)
    assertResultForCategory(plotCol2.series.head, "category1", 1)
    assertResultForCategory(plotCol2.series.head, "category2", 2)
    assertResultForCategory(plotCol2.series.head, "category4", 3)
  }

  private def assertResultForCategory(plotSeries: PlotSeries, category: String, expectedCount: Int): Unit = {
    val result = plotSeries.xValues.values zip plotSeries.yValues.values

    assertResult(expectedCount, category) {
      result.filter{case (x,y) => x.value == category}.head._2.value
    }
  }

}
