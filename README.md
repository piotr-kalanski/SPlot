SPlot
=====

SPlot is Scala library for data visualization.

Goals
-------

- Provide simple API in Scala for data visualization similar to ggplot (http://ggplot2.org/) and Seaborn (https://seaborn.pydata.org/)
- Support exploratory data analysis

Example
-------

```scala
import com.datawizards.splot.implicits._

Seq(1.0, 4.0, 9.0).plotBar()
```

Supported charts
----------------

- Bar
