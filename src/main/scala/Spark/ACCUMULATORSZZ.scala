package Spark


import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, rdd}

import scala.io.Source



object ACCUMULATORSZZ extends  App{

def boringWords() :Set[String] = {
  var bWords :Set[String] = Set();
  // Open this file to be broacasted locally and then broadcast it to all the cluster
  val lines = Source.fromFile("/boringWords.txt").getLines();
  //Lines is not a rdd
//  each line has just one word and we will iterate over it and add words to Hashset
  for(line <- lines){
    bWords+=line // add all the lines
  }

  bWords
}



  val sc = new SparkContext("local[*]", "totalSpent")
  // There is a shared copy of a variable kept int the driver program
  // Executors update that variable in driver
  // It can be a counter
  // 500 MB file - spread accros 4 partitions
  //Find number of blank line in the file. we can create a variable in the dirver
  // when executtor find any new line they will update the shared variable in driver
  // Executors cannot read the accumulator data it can only upadate it

// create broadcast varaible
  val nameSet = sc.broadcast(boringWords)
  val filteredRdd = finalMapped.filter(x => !nameSet.value(x._1))
//  redcubyKey laga do

  //can broacast anything list, arrays, hmaps, sets

// Stop words removal using broadcast file

// finding blank lines using accumularotrs
//Shared copy on dirver machine





//  to create a accumulator:
val myAccum =   sc.longAccumulator("blankLineAccumulator")
  val path ="/Users/nikhilmishra/Desktop/customerorders-201008-180523.csv"
  val rdd =  sc.textFile(path);
  rdd.foreach(x => if (x =="") myAccum.add(1));

}
