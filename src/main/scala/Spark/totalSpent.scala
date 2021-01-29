package Spark
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level




object totalSpent extends  App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  val sc = new SparkContext("local[*]", "totalSpent")
  val path ="/Users/nikhilmishra/Desktop/customerorders-201008-180523.csv"
  val input = sc.textFile(path)
  input.map(x => (x.split(",")(0),x.split(",")(1) ))


}
