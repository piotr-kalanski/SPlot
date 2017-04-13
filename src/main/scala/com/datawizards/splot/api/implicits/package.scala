package com.datawizards.splot

import com.datawizards.splot.builders.PlotBuilder

package object implicits {
  implicit def Iterable2IterablePlot[T](x: Iterable[T]): IterablePlot[T] = new IterablePlot(x)

  implicit def IterableDouble2IterableDoublePlot(x: Iterable[Double]): IterableDoublePlot = new IterableDoublePlot(x)

  class IterablePlot[T](iterable: Iterable[T]) {
    def plotBar(values: T => Double): Unit =
      new PlotBuilder[T](iterable).bar(values).display()

    def plotScatter(x: T => Double, y: T => Double): Unit =
      new PlotBuilder[T](iterable).scatter(x, y).display()
  }

  class IterableDoublePlot(iterable: Iterable[Double]) {
    def plotBar(): Unit =
      new PlotBuilder[Double](iterable).bar(x => x).display()
  }
}
