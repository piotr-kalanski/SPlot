package com.datawizards.splot.model

import org.knowm.xchart.BitmapEncoder.BitmapFormat
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat

object ImageFormats {
  val BMP: ImageFormat = new BitmapImageFormat(BitmapFormat.BMP) {}
  val JPG: ImageFormat = new BitmapImageFormat(BitmapFormat.JPG) {}
  val PNG: ImageFormat = new BitmapImageFormat(BitmapFormat.PNG) {}
  val GIF: ImageFormat = new BitmapImageFormat(BitmapFormat.GIF) {}

  val EPS: ImageFormat = new VectorGraphicsImageFormat(VectorGraphicsFormat.EPS) {}
  val PDF: ImageFormat = new VectorGraphicsImageFormat(VectorGraphicsFormat.PDF) {}
  val SVG: ImageFormat = new VectorGraphicsImageFormat(VectorGraphicsFormat.SVG) {}
}

trait ImageFormat
abstract class BitmapImageFormat(val bitmapFormat: BitmapFormat) extends ImageFormat
abstract class VectorGraphicsImageFormat(val vectorGraphicsFormat: VectorGraphicsFormat) extends ImageFormat
