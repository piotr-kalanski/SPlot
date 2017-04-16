package com.datawizards.splot.configuration

import com.datawizards.splot.device.{Device, DeviceTypes}
import com.datawizards.splot.theme.{PlotTheme, PlotThemes}

object SPlotConfiguration {
  var DefaultWidth = 800
  var DefaultHeight = 600
  var deviceType: Device = DeviceTypes.popupWindowDevice
  var plotTheme: PlotTheme = PlotThemes.ggPlotTheme
}
