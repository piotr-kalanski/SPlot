package com.datawizards.splot

import com.datawizards.splot.model.PlotAxisValues.{YAxisValueType, YAxisValueTypeInt, YAxisValueTypeDouble}

package object functions {

  trait AggregationFunction[T] {
    def apply(data: Iterable[T]): YAxisValueType
  }

  class CountFunction[T] extends AggregationFunction[T] {
    override def apply(data: Iterable[T]): YAxisValueType =
      data.size
  }

  class MeanFunction[T](y: T => YAxisValueType) extends AggregationFunction[T] {
    override def apply(data: Iterable[T]): YAxisValueType = {
      val ys = data.map(y)
      val sum = ys.reduce((y1, y2) => y1+y2)
      sum match {
        case i:YAxisValueTypeInt => new YAxisValueTypeDouble(1.0 * i.value / ys.size)
        case d:YAxisValueTypeDouble => new YAxisValueTypeDouble(d.value / ys.size)
      }
    }
  }

  def count[T](): AggregationFunction[T] = new CountFunction[T]
  def mean[T](y: T => YAxisValueType): AggregationFunction[T] = new MeanFunction[T](y)

}
