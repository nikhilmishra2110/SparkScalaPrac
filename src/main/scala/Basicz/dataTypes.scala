package Basicz

object dataTypes extends App {
  val x: Integer = 12
  println(x)

  var v: String = "Nikhil Mishra"
  v = "Shubhra"
  println(v)

  val number1: Int = 432323

  val number2 = 123 //type inference..no need to give datatype

  val b = true;
  println(b)
  var c: Char = 'a'
  val pi: Double = 12312.123123123123123
  val e: Long = 213123123123l;
  val f: Float = 32.23123123123123f;
  val smallVal: Byte = 12
  println(smallVal)
  //int long float char


  // s interpolation
  val name = "Nikhil"
  println(s"Hello $name how are you?") // to substitute name with nikhil we need to as s in the begenning

  // f interpolation = like printf in C
  // %.3f we need to add f inbetween
  println(f"value of pi is $pi%.1f")

  //raw interpolation
  println(raw"hello \t \n how are \tyou")


  val abs: Boolean = 1>2;
  println(abs)

  val x2:String = "nikhil"
  val y :String = "nikhil"
  val z :Boolean = x2==y
  println(z)// in java to compare two string we have to use equals method
  // i java == is used for reference comparision
  // in case of scala == be used for string comparison


//  flow control in scala
  if(1>3){
    println("Hello")
  }else{
    println("Zelloss")
  }

//  CASE statement:
//    like switch  in Java
// val match{case 1 =>}
  val k = 9
  k match {
    case 1 => println("Wow")
    case 2 => println("tow")
    case 3 =>  println("mer")
    case _ => println("noce")// in Java after each case we need to add break; here we do not have to do
  }



  for(x <- 1 to 10){
    val sq = x*x
    println(sq)
  }//cannot change val

  var i = 0;
while(i <= 10){
  println(i)
  i=i+1
}

//do while()
  // code will execute atleast once
  i = 0
  do{
    println("yoga")
    i=i+1
  }while(i <=10)


  //Block of code -- will return the last value of the block
val oneVar = {
  val t = 10
  t + 10
10
}

  println(oneVar)

// scala is a functional programming language
  


}

