package com.datawizards.splot.device

import com.datawizards.splot.model.Plot

trait Device {
  def plot(plot: Plot): Unit
}
