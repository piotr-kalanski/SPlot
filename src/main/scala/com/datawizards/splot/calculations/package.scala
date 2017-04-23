package com.datawizards.splot

import com.datawizards.splot.functions.AggregationFunction
import com.datawizards.splot.model.PlotAxisValues.{XAxisValueType, XAxisValueTypeDouble, XAxisValueTypeInt, XAxisValueTypeString, YAxisValueType, YAxisValueTypeDouble, YAxisValueTypeInt}
import com.datawizards.splot.model.PlotSeries
import org.knowm.xchart.Histogram

import scala.collection.JavaConversions._

package object calculations {

  object PlotSeriesCalculator {

    def createYMapperCalculator[T](values: T => YAxisValueType): PlotSeriesCalculator[T] =
      new PlotSeriesCalculatorWithSequence(values)

    def createXYMapperCalculator[T](x: T => XAxisValueType, y: T => YAxisValueType): PlotSeriesCalculator[T] =
      new PlotSeriesCalculatorWithMapper(x, y)

    def createXYZMapperCalculator[T](x: T => XAxisValueType, y: T => YAxisValueType, z: T => YAxisValueType): PlotSeriesCalculator[T] =
      new XYZValuesCalculatorWithMapper(x, y, z)

    def createAggregationCalculator[T](x: T => XAxisValueType, agg: AggregationFunction[T]): PlotSeriesCalculator[T] =
      new PlotSeriesCalculatorWithAggregator(x, agg)

    def createNumericalHistogramCalculator[T](values: T => Double, bins: Int): PlotSeriesCalculator[T] =
      new PlotSeriesCalculatorNumericHistogram(values, bins)

    def createCategoricalHistogramCalculator[T](values: T => String): PlotSeriesCalculator[T] =
      new PlotSeriesCalculatorCategoricalHistogram(values)
  }

  trait PlotSeriesCalculator[T] {
    def apply(seriesName: String, data: Iterable[T]): PlotSeries
  }

  class PlotSeriesCalculatorWithSequence[T](yMapper: T => YAxisValueType)
    extends PlotSeriesCalculator[T] {
    override def apply(seriesName: String, data: Iterable[T]): PlotSeries =
      PlotSeries(seriesName, data.zipWithIndex.map(p => (new XAxisValueTypeInt(1 + p._2), yMapper(p._1), null)))
  }

  class PlotSeriesCalculatorWithMapper[T](xMapper: T => XAxisValueType, yMapper: T => YAxisValueType)
    extends PlotSeriesCalculator[T] {
    override def apply(seriesName: String, data: Iterable[T]): PlotSeries =
      PlotSeries(seriesName, data.map(e => (xMapper(e), yMapper(e), null)))
  }

  class XYZValuesCalculatorWithMapper[T](xMapper: T => XAxisValueType, yMapper: T => YAxisValueType, zMapper: T => YAxisValueType)
    extends PlotSeriesCalculator[T] {
    override def apply(seriesName: String, data: Iterable[T]): PlotSeries =
      PlotSeries(seriesName, data.map(e => (xMapper(e), yMapper(e), zMapper(e))))
  }

  class PlotSeriesCalculatorWithAggregator[T](xMapper: T => XAxisValueType, agg: AggregationFunction[T])
    extends PlotSeriesCalculator[T] {
    override def apply(seriesName: String, data: Iterable[T]): PlotSeries =
      PlotSeries(
        seriesName,
        data
          .groupBy(xMapper)
          .mapValues(agg(_))
          .map{case (x,y) => (x,y,null)}
      )

  }

  class PlotSeriesCalculatorNumericHistogram[T](values: T => Double, bins: Int)
    extends PlotSeriesCalculator[T] {
    override def apply(seriesName: String, data: Iterable[T]): PlotSeries = {
      val rawValues = data.map(values).map(v => new java.lang.Double(v))
      val histogram = new Histogram(rawValues, bins)
      PlotSeries(
        seriesName,
        histogram.getxAxisData().toIterable.map(d => new XAxisValueTypeDouble(d)),
        histogram.getyAxisData().toIterable.map(d => new YAxisValueTypeDouble(d))
      )
    }
  }

  class PlotSeriesCalculatorCategoricalHistogram[T](values: T => String)
    extends PlotSeriesCalculator[T] {
    override def apply(seriesName: String, data: Iterable[T]): PlotSeries =
      PlotSeries(
        seriesName,
        data
          .groupBy(values)
          .mapValues(x => x.size)
          .map{case (s,i) => (new XAxisValueTypeString(s), new YAxisValueTypeInt(i), null)}
      )

  }

}
