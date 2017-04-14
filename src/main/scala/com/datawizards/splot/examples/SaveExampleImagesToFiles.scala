package com.datawizards.splot.examples

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.ImageFormats

import scala.util.Random

object SaveExampleImagesToFiles extends App {
  val exportPath = "images/"
  val format = ImageFormats.PNG
  val width = 400
  val height = 300
  val rand = new Random()
  rand.setSeed(0L)
  val gaussians = for(i <- 1 to 10000) yield rand.nextGaussian()

  Seq(1.0, 4.0, 9.0).buildPlot().bar(x => x).size(width, height).save(exportPath+"basic_bar.png", format)

  people.take(5).buildPlot().bar(_.age).size(width, height).save(exportPath+"bar_people.png", format)

  Seq(
    (1.0, 1.0),
    (2.0, 4.0),
    (3.0, 9.0)
  ).buildPlot().scatter(_._1, _._2).size(width, height).save(exportPath+"scatter_basic.png", format)

  Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
  ).buildPlot().scatter(_.age, _.income).size(width, height).save(exportPath+"scatter_age_income.png", format)

  Seq(
    (1.0, 1.0),
    (2.0, 4.0),
    (3.0, 9.0)
  ).buildPlot().line(_._1, _._2).size(width, height).save(exportPath+"line_basic.png", format)

  Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
  ).buildPlot().line(_.age, _.income).size(width, height).save(exportPath+"line_age_income.png", format)

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .size(300, 900)
    .rowsBy(_.education)
    .save(exportPath+"people_groupby_education.png", format)

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .size(800, 200)
    .colsBy(_.country)
    .save(exportPath+"people_groupby_country.png", format)

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .size(1200, 800)
    .colsBy(_.country)
    .rowsBy(_.education)
    .save(exportPath+"people_groupby_country_education.png", format)

  gaussians
    .buildPlot()
    .histogram(x=>x, 100)
    .size(400, 300)
    .save(exportPath+"histogram_for_gaussians.png", format)
}
