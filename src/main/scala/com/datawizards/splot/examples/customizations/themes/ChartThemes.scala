package com.datawizards.splot.examples.customizations.themes

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.theme.PlotTheme

object ChartThemes extends App {
  val data = Seq(
    ("Python", 3.0),
    ("Java", 4.0),
    ("Scala", 5.0)
  )

  data.buildPlot().bar().size(300,150).theme(PlotTheme.GGPlot2).title("ggplot").display()
  data.buildPlot().bar().size(300,150).theme(PlotTheme.Matlab).title("matlab").display()
  data.buildPlot().bar().size(300,150).theme(PlotTheme.XChart).title("xchart").display()
  data.buildPlot().bar().size(300,150).theme(PlotTheme.SPlot).title("splot").display()

}
