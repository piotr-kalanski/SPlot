package com.datawizards.splot.theme

object PlotThemes {
  val ggPlotTheme: PlotTheme = GGPlotTheme
}

trait PlotTheme

object GGPlotTheme extends PlotTheme
