package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.datawizards.splot.functions._

@RunWith(classOf[JUnitRunner])
class AggregationTests extends SPlotBaseTest {

  test("Count aggregation") {
    val data = Seq(
      "col1",
      "col1",
      "col1",
      "col2",
      "col2"
    )

    data.buildPlot().barWithAggregations(x => x, count()).display(unitTestsDevice)

    assertPlotXYAxisValues(Seq("col1","col2"), Seq(3,2), getLastPlotFirstSeries)
  }

  test("Mean aggregation") {
    val data = Seq(
      ("col1", 1),
      ("col1", 2),
      ("col1", 3),
      ("col2", 4),
      ("col2", 6)
    )

    data.buildPlot().barWithAggregations(_._1, mean(_._2)).display(unitTestsDevice)

    assertPlotXYAxisValues(Seq("col1","col2"), Seq(2.0,5.0), getLastPlotFirstSeries)
  }

  test("Count aggregation with multiple series") {
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

    data.buildPlot().barWithAggregations(_._2, count()).seriesBy(_._1).display(unitTestsDevice)

    val plotSeries1 = getLastPlot.findSeriesByName("series1")
    val plotSeries2 = getLastPlot.findSeriesByName("series2")

    assertPlotXYAxisValues(Seq("category1","category2","category3"), Seq(1,2,1), plotSeries1)
    assertPlotXYAxisValues(Seq("category1","category2","category4"), Seq(1,2,3), plotSeries2)
  }

  test("Mean aggregation with multiple series") {
    val data = Seq(
      ("series1","category1",1),
      ("series1","category2",2),
      ("series1","category2",4),
      ("series1","category3",2),
      ("series2","category1",10),
      ("series2","category2",20),
      ("series2","category2",30),
      ("series2","category4",10),
      ("series2","category4",15),
      ("series2","category4",20)
    )

    data.buildPlot().barWithAggregations(_._2, mean(_._3)).seriesBy(_._1).display(unitTestsDevice)

    val plotSeries1 = getLastPlot.findSeriesByName("series1")
    val plotSeries2 = getLastPlot.findSeriesByName("series2")

    assertPlotXYAxisValues(Seq("category1","category2","category3"), Seq(1.0,3.0,2.0), plotSeries1)
    assertPlotXYAxisValues(Seq("category1","category2","category4"), Seq(10.0,25.0,15.0), plotSeries2)
  }

  test("Count aggregation with multiple columns") {
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

    data.buildPlot().barWithAggregations(_._2, count()).colsBy(_._1).display(unitTestsDevice)

    assertPlotXYAxisValues(Seq("category1","category2","category3"), Seq(1,2,1), getLastPlotSeriesForColumn("col1"))
    assertPlotXYAxisValues(Seq("category1","category2","category4"), Seq(1,2,3), getLastPlotSeriesForColumn("col2"))
  }

  test("Mean aggregation with multiple columns") {
    val data = Seq(
      ("col1","category1",1),
      ("col1","category2",2),
      ("col1","category2",4),
      ("col1","category3",2),
      ("col2","category1",10),
      ("col2","category2",20),
      ("col2","category2",30),
      ("col2","category4",10),
      ("col2","category4",15),
      ("col2","category4",20)
    )

    data.buildPlot().barWithAggregations(_._2, mean(_._3)).colsBy(_._1).display(unitTestsDevice)

    assertPlotXYAxisValues(Seq("category1","category2","category3"), Seq(1.0,3.0,2.0), getLastPlotSeriesForColumn("col1"))
    assertPlotXYAxisValues(Seq("category1","category2","category4"), Seq(10.0,25.0,15.0), getLastPlotSeriesForColumn("col2"))
  }

}
