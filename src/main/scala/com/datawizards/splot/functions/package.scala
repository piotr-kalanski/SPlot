package com.datawizards.splot

import com.datawizards.splot.model.PlotAxisValues.{YAxisValueType, YAxisValueTypeDouble, YAxisValueTypeInt}

package object functions {

  trait AggregationFunction[T] {
    def apply(data: Iterable[T]): YAxisValueType
  }

  def count[T](): AggregationFunction[T] = new AggregationFunction[T] {
    override def apply(data: Iterable[T]): YAxisValueType =
      data.size
  }

  def sum[T](y: T => YAxisValueType): AggregationFunction[T] = new AggregationFunction[T] {
    override def apply(data: Iterable[T]): YAxisValueType = {
      val ys = data.map(y)
      ys.reduce(_ + _)
    }
  }

  def mean[T](y: T => YAxisValueType): AggregationFunction[T] = new AggregationFunction[T] {
    override def apply(data: Iterable[T]): YAxisValueType = {
      val size = data.size
      sum(y)(data) match {
        case i:YAxisValueTypeInt => new YAxisValueTypeDouble(1.0 * i.value / size)
        case d:YAxisValueTypeDouble => new YAxisValueTypeDouble(d.value / size)
      }
    }
  }

}
