package com.nikhil.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object totalSpent extends  App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  val sc = new SparkContext("local[*]", "totalSpent")
  val path ="/Users/nikhilmishra/Desktop/customerorders-201008-180523.csv"
  val input = sc.textFile(path)
  val mappedInput = input.map(x => (x.split(",")(0), x.split(",")(1).toFloat))
  val rdd2 = mappedInput.reduceByKey((x,y) => x+y)   // x and y are two rows
  val rdd3 = rdd2.sortBy(x => x._2)
}
