package com.datawizards.splot.customizations

import com.datawizards.splot.SPlotBaseTest
import com.datawizards.splot.api.implicits._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AnnotationsCustomizationsTest extends SPlotBaseTest {
  val data = Seq(1.0, 4.0, 9.0)

  test("Don't change annotations") {
    data
      .buildPlot()
      .bar()
      .display(unitTestsDevice)

    assert(getLastPlot.annotations === None)
  }

  test("Hide annotations") {
    data
      .buildPlot()
      .bar()
      .showAnnotations(false)
      .display(unitTestsDevice)

    assert(getLastPlot.annotations === Some(false))
  }

}
