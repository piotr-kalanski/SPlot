package com.datawizards.splot.model

import java.util.Date

object PlotAxisValues {

  /*** X Axis ***/

  trait XAxisValueType extends Any with Ordered[XAxisValueType] {
    def value: Any

    override def hashCode: Int = value.hashCode

    override def equals(obj: scala.Any): Boolean = {
      if(value.equals(obj)) true
      else
        obj match {
          case x:XAxisValueType => value.equals(x.value)
          case _ => false
        }
    }

    override def toString: String = value.toString

    def compare(that: XAxisValueType): Int = (this,that) match {
      case (i1:XAxisValueTypeInt,i2:XAxisValueTypeInt) => i1.value.compare(i2.value)
      case (d1:XAxisValueTypeDouble,d2:XAxisValueTypeDouble) => d1.value.compare(d2.value)
      case (i:XAxisValueTypeInt,d:XAxisValueTypeDouble) => i.value.toDouble.compare(d.value)
      case (d:XAxisValueTypeDouble,i:XAxisValueTypeInt) => d.value.compare(i.value.toDouble)
      case (s1:XAxisValueTypeString,s2:XAxisValueTypeString) => s1.value.compare(s2.value)
      case (date1:XAxisValueTypeDate,date2:XAxisValueTypeDate) => date1.value.compareTo(date2.value)
      case _ => 0
    }

  }

  class XAxisValueTypeInt(val value: Int) extends AnyVal with XAxisValueType
  class XAxisValueTypeDouble(val value: Double) extends AnyVal with XAxisValueType
  class XAxisValueTypeString(val value: String) extends AnyVal with XAxisValueType
  class XAxisValueTypeDate(val value: Date) extends AnyVal with XAxisValueType

  class XAxisValues(val values: Iterable[XAxisValueType]) {

    override def toString: String = s"XAxis[${values.toString}]"

    override def hashCode: Int = values.hashCode

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

  def createXAxisValuesDate(values: Iterable[Date]): XAxisValues =
    new XAxisValues(values.map(x => new XAxisValueTypeDate(x)))

  def createXAxisValues(values: Iterable[XAxisValueType]): XAxisValues =
    new XAxisValues(values)

  implicit def Int2XAxisValue(x: Int): XAxisValueType = new XAxisValueTypeInt(x)
  implicit def Double2XAxisValue(x: Double): XAxisValueType = new XAxisValueTypeDouble(x)
  implicit def String2XAxisValue(x: String): XAxisValueType = new XAxisValueTypeString(x)
  implicit def Date2XAxisValue(x: Date): XAxisValueType = new XAxisValueTypeDate(x)

  /*** Y Axis ***/

  trait YAxisValueType extends Any {
    def value: AnyVal

    override def hashCode: Int = value.hashCode

    override def equals(obj: scala.Any): Boolean = {
      if(value.equals(obj)) true
      else
        obj match {
          case x:YAxisValueType => value.equals(x.value)
          case _ => false
        }
    }

    override def toString: String = value.toString

    def +(that: YAxisValueType): YAxisValueType
  }

  class YAxisValueTypeInt(val value: Int) extends AnyVal with YAxisValueType {
    override def +(that: YAxisValueType): YAxisValueType = that match {
      case i:YAxisValueTypeInt => new YAxisValueTypeInt(value + i.value)
      case d:YAxisValueTypeDouble => new YAxisValueTypeDouble(value + d.value)

    }
  }

  class YAxisValueTypeDouble(val value: Double) extends AnyVal with YAxisValueType {
    override def +(that: YAxisValueType): YAxisValueType = that match {
      case i:YAxisValueTypeInt => new YAxisValueTypeDouble(value + i.value)
      case d:YAxisValueTypeDouble => new YAxisValueTypeDouble(value + d.value)
    }
  }

  class YAxisValues(val values: Iterable[YAxisValueType]) {

    override def toString: String = s"YAxis[${values.toString}]"

    override def hashCode: Int = values.hashCode

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
