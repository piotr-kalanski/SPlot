package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.builders.PlotBuilder
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
      .scatter(_._1, _._2)
      .colsBy(_._1)
      .display()

    val plotsGrid = getLastPlotsGrid

    assertResult(1) {
      plotsGrid.rows
    }

    assertResult(3) {
      plotsGrid.cols
    }

    assertResult(Seq(1.0, 1.5)) {
      plotsGrid(PlotBuilder.DefaultSingleGroup, 1.0).yValues
    }

  }

  test("group by rows") {
    data
      .buildPlot()
      .scatter(_._1, _._2)
      .rowsBy(_._1)
      .display()

    val plotsGrid = getLastPlotsGrid

    assertResult(3) {
      plotsGrid.rows
    }

    assertResult(1) {
      plotsGrid.cols
    }

    assertResult(Seq(1.0, 1.5)) {
      plotsGrid(1.0, PlotBuilder.DefaultSingleGroup).yValues
    }
  }

  test("group by cols, rows") {
    data
      .buildPlot()
      .scatter(_._1, _._2)
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
}
