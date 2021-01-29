package Spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext


object ADCampaign extends  App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  val sc = new SparkContext("local[*]", "totalSpent")
  val path ="/Users/nikhilmishra/Desktop/customerorders-201008-180523.csv"
  val input = sc.textFile(path)

  // we want to read only column 1 and column 11
val rdd1 = input.map(x => ( x.split(",")(10).toFloat , x.split(",")(0)))
  // 24.06 big data co
  // 29.23 spark content

//  to above we will apply flatMap to get
//  24.06 big
//  24.06 data
//  24.06 content
//  29.23 spark
//  29.23 content

  val flatten = rdd1.flatMapValues(x => x.split(" "))


  //reverse the content
  //  big 24.06 big
  //  data 24.06 data
  //  content 24.06
  //  spark 29.23
  //  content 29.23

  val reversedFinal = flatten.map(x => (x._2, x._1))



  // Now aggregate - reducebykey works on two rows
  val total = reversedFinal.reduceByKey((x,y) => x + y)

  total.collect



}
