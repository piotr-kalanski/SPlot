package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.builders.PlotBuilder
import com.datawizards.splot.model.PlotAxisValues
import com.datawizards.splot.model.PlotAxisValues.{XAxisValueTypeDouble, YAxisValueTypeDouble}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlotsGridTest extends SPlotBaseTest {
  val data = Seq(
    (1.0, 1.0),
    (1.0, 1.5),
    (2.0, 2.0),
    (2.0, 2.5),
    (3.0, 3.0),
    (3.0, 3.5)
  )

  test("group by cols") {
    data
      .buildPlot()
      .scatter()
      .colsBy(_._1)
      .display()

    val plotsGrid = getLastPlotsGrid

    assertResult(1) {
      plotsGrid.rows
    }

    assertResult(3) {
      plotsGrid.cols
    }

    assertResult(PlotAxisValues.createYAxisValuesDouble(Seq(1.0, 1.5))) {
      plotsGrid(PlotBuilder.DefaultSingleGroup, new XAxisValueTypeDouble(1.0)).yValues
    }

  }

  test("group by rows") {
    data
      .buildPlot()
      .scatter()
      .rowsBy(_._1)
      .display()

    val plotsGrid = getLastPlotsGrid

    assertResult(3) {
      plotsGrid.rows
    }

    assertResult(1) {
      plotsGrid.cols
    }

    assertResult(PlotAxisValues.createYAxisValuesDouble(Seq(1.0, 1.5))) {
      plotsGrid(new XAxisValueTypeDouble(1.0), PlotBuilder.DefaultSingleGroup).yValues
    }
  }

  test("group by cols, rows") {
    data
      .buildPlot()
      .scatter()
      .colsBy(_._1)
      .rowsBy(_._2)
      .display()

    val plotsGrid = getLastPlotsGrid

    assertResult(6) {
      plotsGrid.rows
    }

    assertResult(3) {
      plotsGrid.cols
    }
  }

  test("group by cols - string values") {
    Seq(
      ("col1","b1",11),
      ("col1","b2",12),
      ("col2","b1",21),
      ("col2","b2",22),
      ("col3","b1",31),
      ("col3","b2",32),
      ("col3","b3",33)
    )
    .buildPlot()
    .colsBy(_._1)
    .bar(x => x._2, x => x._3)
    .display()

    val plotsGrid = getLastPlotsGrid

    assertResult(1) {
      plotsGrid.rows
    }

    assertResult(3) {
      plotsGrid.cols
    }

    assertResult(PlotAxisValues.createYAxisValuesInt(Seq(11, 12))) {
      plotsGrid(PlotBuilder.DefaultSingleGroup, "col1").yValues
    }

    assertResult(PlotAxisValues.createXAxisValuesString(Seq("b1", "b2"))) {
      plotsGrid(PlotBuilder.DefaultSingleGroup, "col1").xValues
    }

    assertResult(PlotAxisValues.createYAxisValuesInt(Seq(31, 32, 33))) {
      plotsGrid(PlotBuilder.DefaultSingleGroup, "col3").yValues
    }

    assertResult(PlotAxisValues.createXAxisValuesString(Seq("b1", "b2", "b3"))) {
      plotsGrid(PlotBuilder.DefaultSingleGroup, "col3").xValues
    }

  }

}
