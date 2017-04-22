package com.datawizards.splot.configuration

import com.datawizards.splot.device.{Device, DeviceTypes}
import com.datawizards.splot.theme.PlotTheme

object SPlotDefaults {
  var Width = 800
  var Height = 600
  var DeviceType: Device = DeviceTypes.popupWindowDevice
  var PlotTheme: PlotTheme = com.datawizards.splot.theme.PlotTheme.GGPlot2
}
