package com.datawizards.splot

import com.datawizards.splot.functions.AggregationFunction
import com.datawizards.splot.model.PlotAxisValues.{XAxisValueType, XAxisValueTypeDouble, XAxisValueTypeInt, XAxisValueTypeString, YAxisValueType, YAxisValueTypeDouble, YAxisValueTypeInt}
import org.knowm.xchart.Histogram

import scala.collection.JavaConversions._

package object calculations {

  object XYValuesCalculator {

    def createYMapperCalculator[T](values: T => YAxisValueType): XYValuesCalculator[T] =
      new XYValuesCalculatorWithSequence(values)

    def createXYMapperCalculator[T](x: T => XAxisValueType, y: T => YAxisValueType): XYValuesCalculator[T] =
      new XYValuesCalculatorWithMapper(x, y)

    def createAggregationCalculator[T](x: T => XAxisValueType, agg: AggregationFunction[T]): XYValuesCalculator[T] =
      new XYValuesCalculatorWithAggregator(x, agg)

    def createNumericalHistogramCalculator[T](values: T => Double, bins: Int): XYValuesCalculator[T] =
      new XYValuesCalculatorNumericHistogram(values, bins)

    def createCategoricalHistogramCalculator[T](values: T => String): XYValuesCalculator[T] =
      new XYValuesCalculatorCategoricalHistogram(values)
  }

  trait XYValuesCalculator[T] {
    def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType])
  }

  class XYValuesCalculatorWithSequence[T](yMapper: T => YAxisValueType)
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) =
      data.zipWithIndex.map(p => (new XAxisValueTypeInt(1 + p._2), yMapper(p._1))).unzip
  }

  class XYValuesCalculatorWithMapper[T](xMapper: T => XAxisValueType, yMapper: T => YAxisValueType)
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) =
      data.map(e => (xMapper(e), yMapper(e))).unzip
  }

  class XYValuesCalculatorWithAggregator[T](xMapper: T => XAxisValueType, agg: AggregationFunction[T])
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) = {
      data
        .groupBy(xMapper)
        .mapValues(agg(_))
        .unzip
    }
  }

  class XYValuesCalculatorNumericHistogram[T](values: T => Double, bins: Int)
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) = {
      val rawValues = data.map(values).map(v => new java.lang.Double(v))
      val histogram = new Histogram(rawValues, bins)
      (
        histogram.getxAxisData().toIterable.map(d => new XAxisValueTypeDouble(d)),
        histogram.getyAxisData().toIterable.map(d => new YAxisValueTypeDouble(d))
      )
    }
  }

  class XYValuesCalculatorCategoricalHistogram[T](values: T => String)
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) = {
      data
        .groupBy(values)
        .mapValues(x => x.size)
        .map{case (s,i) => (new XAxisValueTypeString(s), new YAxisValueTypeInt(i))}
        .unzip
    }
  }

}
