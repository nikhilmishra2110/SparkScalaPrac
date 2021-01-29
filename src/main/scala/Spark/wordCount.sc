//package Spark
//
//object wordCount extends  App{
//  // gedit file1
//  // hadoop fs -mkdir /user/cloudera/sparkintput/
//  // hadoop fs -put file1 /user/cloudera/sparkintput/
//  // hadoop fs -cat file1 /user/cloudera/sparkintput/file1
//
//  // data loaded -> invoke spark shell(using spark-shell) now and write scala code
//  // for pyspark use --> pyspark
//  // Interactive way to write the code is not available for Java
//
//
//  // on Terminal it say sparkContext is available as sc
//  //  sc is the entry point for you to run on the cluster
//  // if we run 5 + 8 -> it runs locally on this machine not on cluster
//  // to run it accross spark cluster we need to use sc
//
//
//  // the basic unit which holds the data in spark is called rdd and the very first step we need to do is to
//  // load data into rdd for processing - this is also called as a base rdd
//  val rdd1 = sc.textFile("/user/cloudera/sparkintput/file1") //-> this is a lazy operation and will not happen immediate
//  val rdd2 = rdd1.flatMap(x => x.split(" "))  // whenever we do a split we get an array
//  // how flatMap works??
//      // flatMap takes each line as input and takes each line as input and does the processing
//      // x is a line
//      // we will get one array for one line >> flatMap will flatten out the array and give all the content in one array
//      //Array(spark,is,very,interesting,in,memory,engine,hope,learning,interesting,very,spark)
//      //flatMap is a higher order function and (x => x.split(" ")) is the anonymous function
//
//      val rdd3 = rdd2.map(x => (x,1))
//      // so if spark is input the ouptput will be (spark,1)
//      // evarything will become (word,1)
//
//      rdd3.collect()
//      (spark,1)
//      (spark,1)
//      (hope,1)
//      (hope,1)
//      (hope,1)
//      (interesting,1)
//      (interesting,1)
//      (interesting,1)
//      (interesting,1)
//
//      now we need to use reduceByKey
//
//      val rdd4 = rdd3.reduceByKey((x,y)) => x + y)
//      // it will group similar keys together
//      reduceByKey it always work on two rows at a time //TODO: Important
//      if we visualize
//        x is (spark,1)
//        y is (spark,1)
//        the operation of addition is only on the value part not on the key
//        key is spark and value is 1
//        result will be (spark,2)
//
//        moving forward we have
//        (spark,2)
//        (spark,1)
//            => above will become (spark,3)
//
//      reduceByKey is a transformation not an action
//      rdd4.collect() will get me the reuslts
//                >>> upon invocation it will calculate everything and give u the result instantly
//
//      localhost:4040 give you the spark UI
//
//      We generally do not use collect as that brings all the data to the driver nd might cause memory issues
//
//      We generally use saveAsTextFile(path) // this path should be non existent
//
//      When we go and check the path we should be able to find _SUCCESS and part files- this confirms that the data is written
//        RDD has partitions - this is
//
//      Whitoiut putting file in HDFS can we process local file
//        we need to prfix this to path:  file:///home/cloudera/file1
//
//      Function whoch do not have input paramters can be called without braces
//        Like .collect, .show,
//
//
//When we write a new application we have to write new scala object
//    There has to be a main method inside the scala object :
//        1.  def main(args :Array[String]){}
//        2. Or just object WordCount extends App
//
//    When we write program in spark shell sc was avalable to us already we just had to use it
//    BUT in IDE we need to create our own spark context
//
//    val sc = new SparkContext("local[*], "WordCount")
//    local = local mode
//    * means use all cores of my machine - this allows multitreading
//
//    Spark 2.2.4 is compatible with
//    Scala 2.11 version of Scala
//                    SBT is an interactive build tool that is used to run tests and package your projects as JAR files.
//                    SBT lets you create a project in a text editor and package it, so it can be run in a cloud cluster computing environment (like Databricks).
//                    The SBT build definition is specified in the build.sbt file.
//                    This is where you’ll add code to specify your dependencies, the Scala version, how to build your JAR files, how to manage memory, etc.
//
//                    One of the only things that’s not specified in the build.sbt file is the SBT version itself. The SBT version is specified in the project/build.properties file, for example:
//
//                    sbt.version=1.2.8
//                    libraryDependencies
//                    You can specify libraryDependencies in your build.sbt file to fetch libraries from Maven or JitPack.
//
//                    Here’s how to add Spark SQL and Spark ML to a project:
//
//                    libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0" % "provided"
//                    libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.0" % "provided"
//                    SBT provides shortcut sytax if we’d like to clean up our build.sbt file a bit.
//
//                    libraryDependencies ++= Seq(
//                      "org.apache.spark" %% "spark-sql" % "2.4.0" % "provided",
//                      "org.apache.spark" %% "spark-mllib" % "2.4.0" % "provided"
//                    )
//
//
//    we provide paralellism to scala code using Spark
//    and by adding JARs we are making our code cabable of running on Spark
//
//
//    val input  = sc.textFile("/user/cloudera/file1.txt")
//    this input will be of type rdd[String]
//        rdd of type string
//
//    Transformation:
//        now we have text file like:
//            ------------------
//            Big data training
//            hadoop developer
//            spark training
//            scala training
//            -------------------
//            above need to be translated to - one word per line
//            big
//            dat
//            training
//            hadoop ............
//            --------------------
//
//            Which transformation to use? FlatMap as map will give one output per line
//            but we want one word after spliting on space
//
//    Logger level set to ERROR to not see many lines in log
//
//
//    trasformations used:
//        flatMap
//        map
//        reduceByKey
//    action used:
//        rdd.collect.foreach(println)
//
//    word count code in scala" //todo: IMP
//        val input = sc.textFile("path")
//        val words = input.flatmap(x => x.split(" ")).map(x => x.toLowerCase())
//        val wordMap = words.map(x => (x,1))
//        val wordFreq = wordMap.reduceByKey((x,y) => x + y)
//        val res = wordFreq.sorftBy(x => x._2)
//        res.collect.foreach(println)
//
//>>>>>>>>>>>>>
//New session - top 10 customers who spend the most amount in shopping
//
//customer_id, product_id, amountSpent
//
//Algo:
//    Base rdd
//    Column 1 and column 3 -----Column 2 is irrelevant for this case
//            val input = sc.textFile("path").
//            val rdd1 =  input.map(x => (x._1, x._3))   // x is a row
//            val rdd2 = rdd1.reduceByKey((x,y) => x+y)   // x and y are two rows
//            val rdd3 = rdd2.sortBy(x => x._2)
//
//
//          package com.nikhil.spark
//
//          import org.apache.log4j.{Level, Logger}
//          import org.apache.spark.SparkContext
//
//          object totalSpent extends  App{
//            Logger.getLogger("org").setLevel(Level.ERROR)
//
//            val sc = new SparkContext("local[*]", "totalSpent")
//            val path ="/Users/nikhilmishra/Desktop/customerorders-201008-180523.csv"
//            val input = sc.textFile(path)
//            val mappedInput = input.map(x => (x.split(",")(0), x.split(",")(1).toFloat))
//            val rdd2 = mappedInput.reduceByKey((x,y) => x+y)   // x and y are two rows
//            val rdd3 = rdd2.sortBy(x => x._2)
//          }
//
//
//
//whenever rdd contains tuple of teo elements it is called as a pair rdd
//
//Here first element can be treated like key and second as value
//
//
//
//How to get certain columns form a text file
//val mappedInput = input.map(x => split("\t"))(2)
//val res = mappedInput.countByValue
//res.collect.foreach(println)
//
//
//we want last two elements
////map is higer order function and can take function as an argument
//
//0, Will, 33, 387
////We want last two elements
//def parseLine(line:String) = {
//    val fields = line.split(",") //(o,Will, 33, 385)
//    val age = fields(2).toInt
//    val numFriends = fields(3).toInt
//    (age, numFriends)
//}
//
//val path ="/Users/nikhilmishra/Desktop/linkedInFriends.csv"
//val input = sc.textFile(path)
//val mappedInput = input.map(parseLine)
//
//(33,100)
//(33,300)
//(33,200)
//
//When we try to do an average it gives wrong result - avg of avg is not average
//
//
//output-->
//
//(33,(100,1))
//(33,(300,1))
//(33,(200,1))
//val mappedOutput =  mappedInput.map(x => (x._1,(x._2,1)))
//
//output we want is
//(33,(600,3))
//mappedOutput.reduceByKey((x,y) => ((x._2._1 + y._2._1),(x._2._2 + y._2._2))) // this is wrong as we know that in reduceby key we
//only deal with values
//to get 100 in (33,(100,1)) we just need to do x._1
//
//
//val totalByAge = mappedOutput.reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2))
//(33,(600, 3))
//(34,(800, 4))
//
//val avgByAge = totalByAge.map(x => (x._1,x._2._1/x._2._2)// tuple of two
//
//
//
//==>
//    mapValue directly work with values
//
//
//
//
//}
