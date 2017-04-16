package com.datawizards.splot.device

import com.datawizards.splot.model.{Plot, PlotsGrid}

trait Device {
  def plot(plot: Plot): Unit
  def plot(plotsGrid: PlotsGrid): Unit
}
