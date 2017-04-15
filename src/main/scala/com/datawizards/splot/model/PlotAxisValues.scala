package com.datawizards.splot.model

object PlotAxisValues {

  /*** X Axis ***/

  trait XAxisValueType {
    def value: Any

    override def equals(obj: scala.Any): Boolean = {
      obj match {
        case x:XAxisValueType => value.equals(x.value)
        case _ => false
      }
    }

    override def toString: String = value.toString
  }

  class XAxisValueTypeInt(val value: Int) extends XAxisValueType
  class XAxisValueTypeDouble(val value: Double) extends XAxisValueType
  class XAxisValueTypeString(val value: String) extends XAxisValueType

  class XAxisValues(val values: Iterable[XAxisValueType]) {

    override def toString: String = s"XAxis[${values.toString}]"

    override def equals(obj: scala.Any): Boolean = {
      obj match {
        case xav:XAxisValues => values.equals(xav.values)
        case _ => false
      }
    }
  }

  def createXAxisValuesInt(values: Iterable[Int]): XAxisValues =
    new XAxisValues(values.map(x => new XAxisValueTypeInt(x)))

  def createXAxisValuesDouble(values: Iterable[Double]): XAxisValues =
    new XAxisValues(values.map(x => new XAxisValueTypeDouble(x)))

  def createXAxisValuesString(values: Iterable[String]): XAxisValues =
    new XAxisValues(values.map(x => new XAxisValueTypeString(x)))

  def createXAxisValues(values: Iterable[XAxisValueType]): XAxisValues =
    new XAxisValues(values)

  implicit def Int2XAxisValue(x: Int): XAxisValueType = new XAxisValueTypeInt(x)
  implicit def Double2XAxisValue(x: Double): XAxisValueType = new XAxisValueTypeDouble(x)
  implicit def String2XAxisValue(x: String): XAxisValueType = new XAxisValueTypeString(x)

  /*** Y Axis ***/

  trait YAxisValueType {
    def value: AnyVal

    override def equals(obj: scala.Any): Boolean = {
      obj match {
        case x:YAxisValueType => value.equals(x.value)
        case _ => false
      }
    }

    override def toString: String = value.toString
  }

  class YAxisValueTypeInt(val value: Int) extends YAxisValueType
  class YAxisValueTypeDouble(val value: Double) extends YAxisValueType

  class YAxisValues(val values: Iterable[YAxisValueType]) {

    override def toString: String = s"YAxis[${values.toString}]"

    override def equals(obj: scala.Any): Boolean = {
      obj match {
        case xav:YAxisValues => values.equals(xav.values)
        case _ => false
      }
    }
  }

  def createYAxisValuesInt(values: Iterable[Int]): YAxisValues =
    new YAxisValues(values.map(x => new YAxisValueTypeInt(x)))

  def createYAxisValuesDouble(values: Iterable[Double]): YAxisValues =
    new YAxisValues(values.map(x => new YAxisValueTypeDouble(x)))

  def createYAxisValues(values: Iterable[YAxisValueType]): YAxisValues =
    new YAxisValues(values)

  implicit def Int2YAxisValue(x: Int): YAxisValueType = new YAxisValueTypeInt(x)
  implicit def Double2YAxisValue(x: Double): YAxisValueType = new YAxisValueTypeDouble(x)
}
