package com.datawizards.splot.api

import com.datawizards.splot.builders.{PlotBuilder, PlotBuilderForDouble, PlotBuilderForPairOfDouble}
import com.datawizards.splot.model.PlotAxisValues.{XAxisValueType, YAxisValueType}

package object implicits {

  implicit def Iterable2IterablePlot[T](x: Iterable[T]): IterablePlot[T] = new IterablePlot(x)

  implicit def IterableDouble2IterableDoublePlot(x: Iterable[Double]): IterableDoublePlot = new IterableDoublePlot(x)

  implicit def IterablePairDouble2IterablePairDoublePlot(x: Iterable[(Double,Double)]): IterablePairDoublePlot = new IterablePairDoublePlot(x)

  class IterablePlot[T](iterable: Iterable[T]) {
    private val plotBuilder = new PlotBuilder[T](iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilder[T] = plotBuilder

    /**
      * Plot bar chart
      *
      * @param values function mapping element of collection to values
      */
    def plotBar(values: T => YAxisValueType): Unit = plotBuilder.bar(values).display()

    /**
      * Plot bar chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotBar(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.bar(x, y).display()

    /**
      * Plot scatter chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotScatter(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.scatter(x, y).display()

    /**
      * Plot line chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotLine(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.line(x, y).display()

    /**
      * Plot histogram chart
      *
      * @param values function mapping element of collection to values
      * @param bins number of bins for histogram
      */
    def plotHistogram(values: T => Double, bins: Int=PlotBuilder.DefaultHistogramBins): Unit =
      plotBuilder.histogram(values, bins).display()

  }

  class IterableDoublePlot(iterable: Iterable[Double]) {
    private val plotBuilder = new PlotBuilderForDouble(iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForDouble = plotBuilder

    /**
      * Plot bar chart
      */
    def plotBar(): Unit = plotBuilder.bar().display()

    /**
      * Plot histogram chart
      *
      * @param bins number of bins for histogram
      */
    def plotHistogram(bins: Int=PlotBuilder.DefaultHistogramBins): Unit =
      plotBuilder.histogram(x => x, bins).display()
  }

  class IterablePairDoublePlot(iterable: Iterable[(Double, Double)]) {
    private val plotBuilder = new PlotBuilderForPairOfDouble(iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForPairOfDouble = plotBuilder

    /**
      * Plot scatter chart
      */
    def plotScatter(): Unit = plotBuilder.scatter().display()

    /**
      * Plot line chart
      */
    def plotLine(): Unit = plotBuilder.line().display()
  }
}
