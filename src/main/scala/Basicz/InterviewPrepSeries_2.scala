object InterviewPrepSeries_2 extends App {
 val a = Array(1,2,3,4,5)
// how to print this
  println(a)
  println(a.mkString(","))
  println(a.mkString("||||"))// make string with a seperator
  a.foreach(println)
 for(ai <- a){
  println(ai)
 }

 //Array can be referened by index and it is zero based indexe
 //Arrays are mutable collection
 a(2) = 2312
 println(a.mkString(","))
 println(a.mkString(" benxo "))
 println()

//>>>>>>>>>>>>>>>>>>
// first class functions
//>>>>>>>>>>>>>>>>>>
 // 1. functions can be assigned to a variable
def doublerer(x:Int) :Int = {
  x*2
}
println(doublerer(2))

val k = doublerer(_)
 println(k(55))

 // 2. functions can be passed as an argument
 def tripleer(x:Int) : Int ={
  x*3
 }
 def fn(i:Int, f:Int=> Int): Int ={// two aregumner, one argument is a function
  f(i)
 }

 println(fn(2,tripleer))


 // 3. a function should be able to return a function // if all these are met then first class functions
 def f43 = {
  x:Int => x*x
 }
 println(f43(4))




 //>>>>>>>>>>>>>>>>>>
// higher order functions
 //>>>>>>>>>>>>>>>>>>
// if it takes a function argument or retunrd a function as output then it is higher order function
//  example is a map function
 // if we have n input rows then we will have n output rows

 var a2 = 1 to 10;
 def doubl(x:Int):Int = {
  x*2
 }

 println(a2.map(doubl))   // Vector(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

 //>>>>>>>>>>>>>>>>>>
 // anonymous functions
 //>>>>>>>>>>>>>>>>>>

def factorial(n:Int): Int = {
 var result = 1;
 for(i <- 1 to n){
  result = result*i;
 }
result
}

 println(factorial(4))

 // tail recursion
def factorialTail(n:Int, result:Int):Int = {
 if(n ==1) return result
 factorialTail(n-1, result* n)
}
 println(factorialTail(5, 1))

println(println("Nikhil"))


 //Statement vs expressions - statements
 // expression is  a line of code which return something
 var x = 6
val io  = if(x==5) "true" else "false"
 println(io)

// pure function
 // first class nd higher order function
 // immutabilty


 //New session
// >>>>>>> 1. Closure
// What is the use of closure function in Scala?
//   A closure is a function, whose return value depends on the value of one or more variables declared outside this function.
 // in functional programming a function can return a function
 // in OOP we can return a object

 /*
 class person{
 int eyes = 2; // data elements
 def play{// method
 }
 }
 new Person hold two things = state and behavior
*/

 def areaOfCircle = {val pi = 3.24; r:Int => pi * r * r}
 val pi = -3
 println(areaOfCircle(10)) // state and functionality  are associated together--does not take new value of pi -  bound to function
 val s = areaOfCircle
 println(s(2))
 println(s)

 // >>>>>>> 2. Scala type system
// Any type = class will be of type
         // AnyVal = types available in AnyVal
//                   Byte, Short, Int, Long, Float, Double,
//                    Unit, Boolean, Char
//                    (), true false, 'a'
//                   operation between two diff type of element then both will be converted to right element
         //AnyRef = class will be of type when we crete
//        Collection dataypes and string types
 //         list sequence tuple, string
// Special type:
//  Null = only valid for AnyRef - reference type
 //  Nothing = absence of value -  when nothing is returned

//val a98 = 5
val a3 = println("sd")
 println(a3)

 val v7 = 4
val b5 = if(v7 ==5) 1 else 2.0
 println(b5)


 val g = if(v7==5) 1 else println("Hello") // g will be of type AnyVal =  as print and 1 goes down to base class
println(g.getClass.toString)



 //any val vs anyref
 val g2 = if(v7==5) 1 else List(1,2) // goes back to any


 //>>>>>>> 3. Operators
// In scala we do not have  operator, we only have methods
 println (3 + 4) // + is a method
 val h1 = 5
 val h2 = 6
 h1.+(h2) // works
 h1 compare (h2) // works

 // >>>>>> 4. Anaoymous function
// a function without a name is anonymous function

 val s1 = 1 to 100
 println(s1.map(x => x*2))


//>>>>>>> 5. PlaceHolder syntax
 val ay = 1 to 100
 println( ay.map(x => x *x)) // map takes only one input parameter, then why to pass it? can we write a.map(x*2)??
// ay.map(x*2) // gives Error:(171, 10) type mismatch;
// found   : Int
// required: Int => ?
// ay.map(x*2)

// therefore we can write
   println(ay.map(_*2))

 println(ay.reduce((x:Int, y:Int) => x+y))
// write using placeholderss
 println(ay.reduce(_+_))


//Session 4
// 1. Partially applied function
// 2. Currying



 // 1. Partially applied function = this is an act of creating brand new functions by fixing one or more parameters
 // in a function

 def divideFunction(x:Double, y:Double) = {
  x/y
 }
 println(divideFunction(2,3))
// What is we want to fix one parameter then what? Can we change the above?
val inverse = divideFunction(1, _:Double) // x is always fixed as one  and we use previous definition
println(inverse(1))


 def sumFunction(x:Int, y:Int) = {
  x+y
 }
println( sumFunction(5,6))
 val increment = sumFunction(1,_:Int)
 println(increment(12))

 def feneric(x:Int, y:Int, f:Int => Int) = {
  f(x) + f(y)
 }
 println(feneric(5,5,x => x*x))
 println(feneric(5,5,x => x*x*x))
 println(feneric(5,5,x => x*x*x*x))


 val sumOfSquare =feneric(_:Int, _:Int, x => x*x)
 println(sumOfSquare(3,4))









// 2. Currying - same as partianlly defined function but sytactic sugar = logically segregate parameters into groups
 //One benefit is that Scala currying makes creating anonymous functions easier.
 // Scala Currying also makes it easier to pass around a function as a first-class object.
 // You can keep applying parameters when you find them.
 def genericSum (f:Int=>Int)(x:Int, y:Int)={
  f(x)+f(y)
 }
println(genericSum(x=> x*x)(5,3))

 val sumOfSq = genericSum(x=>x*x)_
println("Idhar")
println(sumOfSq(2,3))




}
