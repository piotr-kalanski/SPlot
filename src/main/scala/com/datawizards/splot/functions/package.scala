package com.datawizards.splot

import com.datawizards.splot.model.PlotAxisValues.{XAxisValueType, XAxisValueTypeInt, YAxisValueType, YAxisValueTypeDouble, YAxisValueTypeInt, YAxisValues}

package object functions {

  //TODO rozwazyc podzielenie na dwie grupy: funkcje agregujace, funkcje mapujace XY. Raczej warto na dwa pakiety podzielic

  trait AggregationFunction[T] {
    def apply(data: Iterable[T]): YAxisValueType //TODO moze tutaj zwrocic Iterable z jednym elementem, tak, aby miec generyczna funkcje?
  }

  // TODO czy potrzebne te klasy? Moze zamiast tego utworzyc klasy anonimowe w funkcjach count, mean?
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


  trait XAxisCalculator[T] {
    def apply(data: Iterable[T]): Iterable[XAxisValueType]
  }

  trait YAxisCalculator[T] {
    def apply(data: Iterable[T]): Iterable[YAxisValueType]
  }

  // TODO dodac wiecej funkcji np. Mapper[T]
  class XAxisCalculatorWithMapper[T](map: T => XAxisValueType) extends XAxisCalculator[T] {
    override def apply(data: Iterable[T]): Iterable[XAxisValueType] =
      data.map(map)
  }

  class XAxisCalculatorWithSequence[T] extends XAxisCalculator[T] {
    override def apply(data: Iterable[T]): Iterable[XAxisValueType] =
      data.zipWithIndex.map(p => new XAxisValueTypeInt(p._2))
  }

  // TODO generalnie potrzebna jest ogolna klasa, ktora na podstawie kolekcji elementów [T] zwróci wartości X,Y ktore moga byc agregatami, x moze byc serkwencja wartoscia, Y moze byc wybranym elementem itd.
  trait XYValuesCalculator[T] {
    def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType])
  }

  class XYValuesCalculatorWithMapper[T](xMapper: T=>XAxisValueType, yMapper: T=>YAxisValueType)
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) =
      data.map(e => (xMapper(e), yMapper(e))).unzip
  }

  class XYValuesCalculatorWithAggregator[T](x: XAxisCalculator[T], agg: AggregationFunction[T])
    extends XYValuesCalculator[T] {
    override def apply(data: Iterable[T]): (Iterable[XAxisValueType], Iterable[YAxisValueType]) = {
      val (xVals, yVals) = (x(data) zip data)
        .groupBy(_._1)
        .mapValues(vals => agg(vals.map(_._2)))
        .unzip

      (xVals, yVals)
    }
  }

}
