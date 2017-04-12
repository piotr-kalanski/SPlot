package com.datawizards.splot.api

import com.datawizards.splot.builders.PlotBuilder

object SPlot {
  def apply[T](data: Iterable[T]): PlotBuilder[T] = new PlotBuilder[T](data)
}
