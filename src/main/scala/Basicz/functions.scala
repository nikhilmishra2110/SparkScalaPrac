object functions extends App {
  def addTwoNumber(x: Int, y: Int): Int = {
    //last statement is return by default no need to write it in scala
    return x + y
  }
//can be written like// scala is consise and succint and terse

  def addTwoNumber1(x: Int, y: Int) = x+y

  println(addTwoNumber(3, 2))
  println(addTwoNumber1(3, 2))


  def cubeOfNumber(x:Int) :Int = x*x*x
  println(cubeOfNumber(312))


  // we can pass functions as a parameter to another function
  // we are just concerned about input types and output types of a function - no need to worry about implementaion
  def transformIt(z:Int, f:Int => Int ): Int = {// this is higher order function -transformIt  //IMP
    f(z)
  }

  println(transformIt(2, cubeOfNumber))// here we pass argument and function in another function which combines the two

  println(transformIt(7,x=>x*x*x)) // can pass function definition also
  println(transformIt(5, v => v*v)) // we are passing a function without a name = anonymous function = like lambda in python
  println(transformIt(5, v => v+v)) // we are passing a function without a name = anonymous function = like lambda in python
  // use them wehn we use it just once

  def divideByTwo(x:Int) = x/2

  println(divideByTwo(66))
  println(transformIt(66,divideByTwo))
  println(transformIt(66,x => x/2))
  println(transformIt(6,x => {var y = x*6; y+4}))



}