package com.datawizards.splot.api

import java.util.Date

import com.datawizards.splot.builders._
import com.datawizards.splot.model.PlotAxisValues.{XAxisValueType, XAxisValueTypeDate, XAxisValueTypeDouble, XAxisValueTypeInt, XAxisValueTypeString, YAxisValueType, YAxisValueTypeDouble, YAxisValueTypeInt}

package object implicits {

  implicit def convertIterable[T](x: Iterable[T]): IterablePlot[T] =
    new IterablePlot(x)

  implicit def convertIterableDouble(x: Iterable[Double]): IterableDoublePlot =
    new IterableDoublePlot(x)

  implicit def convertIterableInt(x: Iterable[Int]): IterableIntPlot =
    new IterableIntPlot(x)

  implicit def convertIterablePairDoubleDouble(x: Iterable[(Double,Double)]): IterablePairDoubleDoublePlot =
    new IterablePairDoubleDoublePlot(x)

  implicit def convertIterablePairDoubleInt(x: Iterable[(Double,Int)]): IterablePairDoubleIntPlot =
    new IterablePairDoubleIntPlot(x)

  implicit def convertIterablePairIntDouble(x: Iterable[(Int,Double)]): IterablePairIntDoublePlot =
    new IterablePairIntDoublePlot(x)

  implicit def convertIterablePairIntInt(x: Iterable[(Int,Int)]): IterablePairIntIntPlot =
    new IterablePairIntIntPlot(x)

  implicit def convertIterablePairStringDouble(x: Iterable[(String,Double)]): IterablePairStringDoublePlot =
    new IterablePairStringDoublePlot(x)

  implicit def convertIterablePairStringInt(x: Iterable[(String,Int)]): IterablePairStringIntPlot =
    new IterablePairStringIntPlot(x)

  implicit def convertIterablePairDateDouble(x: Iterable[(Date,Double)]): IterablePairDateDoublePlot =
    new IterablePairDateDoublePlot(x)

  implicit def convertIterablePairDateInt(x: Iterable[(Date,Int)]): IterablePairDateIntPlot =
    new IterablePairDateIntPlot(x)

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

    /**
      * Plot histogram chart
      *
      * @param values function mapping element of collection to values
      */
    def plotHistogramForCategories(values: T => String): Unit =
      plotBuilder.histogramForCategories(values).display()

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

  class IterableIntPlot(iterable: Iterable[Int]) {
    private val plotBuilder = new PlotBuilderForInt(iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForInt = plotBuilder

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

  trait IterablePairOfXYAxis {
    protected def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)]

    private val plotBuilder = new PlotBuilderForPairOfXYAxis(iterablePairOfXYAxis)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForPairOfXYAxis = plotBuilder

    /**
      * Plot bar chart
      */
    def plotBar(): Unit = plotBuilder.bar().display()

    /**
      * Plot scatter chart
      */
    def plotScatter(): Unit = plotBuilder.scatter().display()

    /**
      * Plot line chart
      */
    def plotLine(): Unit = plotBuilder.line().display()
  }

  class IterablePairDoubleDoublePlot(iterable: Iterable[(Double, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDouble(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairDoubleIntPlot(iterable: Iterable[(Double, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDouble(x._1), new YAxisValueTypeInt(x._2)))
  }

  class IterablePairIntDoublePlot(iterable: Iterable[(Int, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeInt(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairIntIntPlot(iterable: Iterable[(Int, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeInt(x._1), new YAxisValueTypeInt(x._2)))
  }

  class IterablePairStringDoublePlot(iterable: Iterable[(String, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeString(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairStringIntPlot(iterable: Iterable[(String, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeString(x._1), new YAxisValueTypeInt(x._2)))
  }

  class IterablePairDateDoublePlot(iterable: Iterable[(Date, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDate(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairDateIntPlot(iterable: Iterable[(Date, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDate(x._1), new YAxisValueTypeInt(x._2)))
  }

}
