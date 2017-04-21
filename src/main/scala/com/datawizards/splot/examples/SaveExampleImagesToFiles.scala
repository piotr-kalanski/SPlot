package com.datawizards.splot.examples

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.functions.{count, mean}
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

  Seq(1.0, 4.0, 9.0).buildPlot().bar().size(width, height).save(exportPath+"basic_bar.png", format)

  people.take(3).buildPlot().bar(_.age).size(width, height).save(exportPath+"bar_people.png", format)

  Seq(
    (1.0, 1.0),
    (2.0, 4.0),
    (3.0, 9.0)
  ).buildPlot().scatter().size(width, height).save(exportPath+"scatter_basic.png", format)

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
  ).buildPlot().line().size(width, height).save(exportPath+"line_basic.png", format)

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
    .histogram(100)
    .size(400, 300)
    .save(exportPath+"histogram_for_gaussians.png", format)

  people
    .buildPlot()
    .histogramForCategories(_.education)
    .size(400, 300)
    .titles("People by education", "Education", "Count")
    .legendVisible(false)
    .save(exportPath+"histogram_for_categories.png", format)

  val populationByCountry = Seq(
    ("DE", 81),
    ("TR", 72),
    ("FR", 63),
    ("UK", 62),
    ("IT", 61),
    ("ES", 46),
    ("UA", 45),
    ("PL", 38),
    ("RO", 19),
    ("NL", 17),
    ("GR", 11),
    ("PT", 11),
    ("BE", 10),
    ("CZ", 10),
    ("HU", 10)
  )

  populationByCountry
    .buildPlot()
    .bar(_._1, _._2)
    .titles("Population by country [millions]", "Country", "Population")
    .size(1200, 300)
    .legendVisible(false)
    .save(exportPath+"bar_chart_with_string.png", format)

  val groupedPeopleByCountryEducation = people
    .groupBy(p => (p.country, p.education))
    .mapValues(pv => pv.size)

  groupedPeopleByCountryEducation
    .buildPlot()
    .colsBy(_._1._1)
    .bar(x => x._1._2, x => x._2)
    .size(1200, 300)
    .save(exportPath+"bar_chart_grids_with_string.png", format)

  people1000
    .buildPlot()
    .colsBy(_.education)
    .histogram(_.age, 50)
    .size(1200, 400)
    .save(exportPath+"histogram_multiple_columns.png", format)

  Seq(1.0, 4.0, 9.0)
    .buildPlot()
    .bar()
    .seriesName("custom name")
    .size(400, 300)
    .save(exportPath+"bar_chart_custom_series_name.png", format)

  Seq(1.0, 4.0, 9.0)
    .buildPlot()
    .bar()
    .legendVisible(false)
    .size(400, 300)
    .save(exportPath+"bar_chart_hide_legend.png", format)

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .seriesBy(_.education)
    .size(500, 400)
    .titles("Age and income by education", "age", "income")
    .save(exportPath+"scatter_chart_with_multiple_series.png", format)

  people
    .buildPlot()
    .scatter(_.age, _.income)
    .size(1200, 300)
    .colsBy(_.country)
    .seriesBy(_.education)
    .save(exportPath+"scatter_chart_with_multiple_columns_and_series.png", format)

  people
    .buildPlot()
    .barWithAggregations(_.education, count())
    .save(exportPath+"bar_chart_with_count_aggregation.png", format)

  people
    .buildPlot()
    .barWithAggregations(_.country, mean(_.income))
    .save(exportPath+"bar_chart_with_mean_aggregation.png", format)

}
