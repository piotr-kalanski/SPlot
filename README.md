# SPlot

SPlot is Scala library for data visualization.

# Goals

- Provide simple API in Scala for data visualization similar to ggplot (http://ggplot2.org/) and Seaborn (https://seaborn.pydata.org/)
- Support exploratory data analysis

# Getting started

Import implicits, which adds methods to Scala collection enabling plotting:
```scala
import com.datawizards.splot.implicits._
```

# Basic example

To plot bar chart using Scala sequence you need to call one method:
```scala
import com.datawizards.splot.implicits._

Seq(1.0, 4.0, 9.0).plotBar()
```

# Supported charts

- Bar
- Scatter

Please note that all below examples **require** importing:
```scala
import com.datawizards.splot.implicits._
```

## Bar

### Bar chart for sequence of numbers

```scala
val data = Seq(1.0, 4.0, 9.0)
data.plotBar()
```

### Bar chart for sequence of case class

```scala
case class Person(name: String, age: Int)

val data = Seq(
    Person("p1", 20),
    Person("p2", 30),
    Person("p3", 40)
)

data.plotBar(_.age)
```

## Scatter

### Scatter chart for sequence of numbers

```scala
val data = Seq(
    (1, 1),
    (2, 4),
    (3, 9)
)

data.plotScatter(_._1, _._2)
```

### Scatter chart for sequence of case class

```scala
case class AgeIncome(age: Int, income: Double)

val data = Seq(
    AgeIncome(20, 1000.0),
    AgeIncome(25, 2000.0),
    AgeIncome(30, 2500.0),
    AgeIncome(35, 3000.0),
    AgeIncome(40, 3500.0),
    AgeIncome(45, 3000.0),
    AgeIncome(50, 2500.0)
)

data.plotScatter(_.age, _.income)
```
