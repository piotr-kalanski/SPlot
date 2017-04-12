package com.datawizards.splot.theme

object PlotThemes {
  val ggPlotTheme: PlotTheme = GGPlotTheme
  val default: PlotTheme = ggPlotTheme
}

trait PlotTheme

object GGPlotTheme extends PlotTheme
