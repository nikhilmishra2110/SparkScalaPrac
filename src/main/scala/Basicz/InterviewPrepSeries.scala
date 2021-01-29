package Basicz

object  InterviewPrepSeries extends App { // object is a singletion
  //1. App
  // scala runs on JVm, it requires a main method
  // if main is not present then we need to add object obj extends App(a trait)
//   def main(args: Array[String]): Unit = {
//  }

//  2. default args, named args, variable args
  def add(nbr1:Int, incrementBy:Int) = {
    nbr1 + incrementBy
  }
  println(add(5,1))

  // can we have default arguments?
  def addDefault(nbr:Int = 2, incrementBy :Int =  1): Unit ={
    nbr + incrementBy
  }
  println(addDefault(1))
  println(addDefault(1,3))
  println(addDefault(incrementBy = 3)) // named arguments

  def printfn(name:String*): Unit ={
    for(i <- name ){println(i)} // it can be a list as it is a varaible lenght parameter
  }
  printfn("Nikhil"," Mishra ","wins and this is a variable length argument")


  // 3. Nil,null,  None, Nothing, option, Unit
// Null is a trait in scala - there exist only one instance of Null that is null
def tryIt(thing : Null):Unit = println("msg1")
//tryIt("nikhil")// will not work - type mismatch
tryIt(null)// null is an of Null
// we should restrinct the use of null as it can lead to null pointer exception

println()
//  Nil is an empty list
val c = Nil
  println(c)


//Nothing is a trait is scala -  there are no instances in Scala for Nothing
  // when evr there is an error the return is Nothing
  def fun() = {
    throw new Exception
  }

//Option
  // When we run into a situation where we do not have a useful value to return from a function
  // returning null is not preferred as it can lead to null pointer exception
  // scala has a built in solution to this problem
  // when we return using option we use None or Some

  def getAStringMaybe(num:Int): Option[String] = {
    if(num>=0) Some("Positive number")
    else None
  }

  println(getAStringMaybe(-1))
  println(getAStringMaybe(0))

  def printResult(num:Int) ={
    getAStringMaybe(num) match {
      case Some(str) => println(str)
      case None => println(None)
    }
  }


  printResult(-10)// gracefully handles None value using Options
  printResult(10)

  // How do you deal with nulls? Using options!!!!!!
  // Options return Some or None - a graceful way of handling

  // Unit -  it is like a void in Java to some extend
  // Unit is returned when there are side effects, say for example when we print something

  case class address(city:String, state:String, zip:String)
  /*class user(email:String, Password:String){// wrong style
    var firstName = _
    var lastName = _
    var address = _
  }
*/
  // lets create instance of above class
  val usr1 =   new user("trendy@gmail.com", "abcd")
//  println(usr1.firstName.length)

  class user(email:String, Password:String){// wrong style
      var firstName : Option[String] = None
      var lastName  : Option[String] = Some("Mishra")
      var address  : Option[address] = None
    }


  // lets create instance of above class
  val usr2 =   new user("trendy@gmail.com", "abcd")
  println(usr2.firstName.getOrElse("Not assigned")) // here we have gracully handled the null pointer exception or null values in scala
  println(usr2.lastName.getOrElse("Not assigned")) // here we have gracully handled the null pointer exception or null values in scala

  usr2.firstName = Some("Nikhil")
  usr2.firstName = Some("Mishra")
  usr2.address = Some(address("Bangalore", "KT", "232323"))
  println(usr2.address.getOrElse("Not assigned")) // here we have gracully handled the null pointer exception or null values in scala
// we should not use nulls in our code and should use options in aour code -  whenever we are not sure if a value will come or not
  // we can handle nulls using getOrElse

//  revision"
    /*
    App: inbuilt main
    Args: default, naems, variable length args
    nil, null, None, Nothing, option Unit in scala
    getOrElse - forn handling the nulls
     */

  // what is yield???
  val t = Array(1,2,3,4,5,6)

//  val b = for(i <- 1 to 10) i*i
//  println(b)

  val b = for(i <- 1 to 10) yield i*i
  println(b)  // Vector(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)

  //println is a side effect instead we want to get the reuslts
  // in a new list from a for loop so that there are no side effects
   // Vector = both indexed and Immutable collection
val t5 =   for( i <- 1 to 10) yield {
    if (i%2 ==0)
      i*i
  }
  println(t5) //Vector((), 4, (), 16, (), 36, (), 64, (), 100)

  // above can be avoided by if guard

  val t6 =   for( i <- 1 to 10; if i%2 ==0) yield {// this is if guard and will filter the values in loop itself, before yield
      i*i
  }
println(t6)//Vector(4, 16, 36, 64, 100)  using if guard

  //more beautification
  val t7 =  for{ i <- 1 to 10
                 if i%2 ==0
                } yield { i*i  }

  println(t7)
//  Vector(4, 16, 36, 64, 100)
//yield = to create a new lit from for loop
//  if guard = filter results from forr loop
// Pattern guard:
//    Case statements can be combined with if expressions to provide extra logic during pattern matching for xtra logic

  def checkSign(x:Int):String ={
    x match {
      case a if a < 0 => s"$a is a negative number"   // case with if is a Pattern guard
      case b if b > 0 => s"$b is a positive number"
      case c  => s"$c is a none of the above"
    }}
  println(checkSign(10))
  println(checkSign(-10))
  println(checkSign(0))

  //Option : None and Some for catching values
  def f(x: Option[Int]) = x match {
    case Some(i) if i%2 ==0 => checkSign(10)
    case None => println("None") //odd number are not handled we might get exeption - we shd have all the cases - remedy add wildcard below
    case _  => println("Else")
  }


  // For comprehension - interview question
  for(i <- 1 to 10) println(i*i)
  // above translates to -  scala internally transalted to this
  (1 to 10).foreach(i => println(i*i))


  for(i <- 1 to 10) yield i+2
  // above translates to
  (1 to 10).map(i => i +2) // for loop is internally converted to this - for better performance
  // scala internally uses map, flatmpa etc, harder syntax for better performance



  // Write nested for loop
  val t9 =   for(i <- 1 to 10; j <- 'a' to 'c') yield  i *2 + " " + j
  println(t9)//Vector(2 a, 2 b, 2 c, 4 a, 4 b, 4 c, 6 a, 6 b, 6 c, 8 a, 8 b, 8 c, 10 a, 10 b, 10 c, 12 a, 12 b, 12 c, 14 a, 14 b, 14 c, 16 a, 16 b, 16 c, 18 a, 18 b, 18 c, 20 a, 20 b, 20 c)


// == in scala vs Java
  /*In Java:
  == is used for reference comparison
  to check natural equality we use equals method in Java

   */

//  val s9 = new String("Nikhil")
//  val s10 = new String("Nikhil")
//  s9 == s10 == this will return false as both are different instances or references - they are diff objects in Java
//  s9.equals(s10) = gives true as content is same

//  In Scala == is for content equality check - it internally implements equals method
  // In Scala to compare the reference we have eq

  val s9 = new String("Nikhil")
  val s10 = new String("Nikhil")
  println(s9 == s10)  // this will return true in Scala,
//  == is method
  println(s9.==(s10))
  println(s9.eq(s10)) // should give false as it is a reference comarison /// instances are compared

// New session IW
  // 1. Strict val and lazy val

  val r = 10
  val area = {
    println("Calculating area of a  circle")
    3.14*r*r
  }// evalauted right now, area is now 314
  println(area)

  lazy val area_lazy = {
    println("Calculating area of a  circle")
    3.14*r*r
  }// NOT evalauted right now, only when below is executed then = lazy evaluated whn first used
  println(area_lazy)

  // 2. Default packages in scala / no need to import them
  // 3 packages are implicitly imported by Scala =>
          // java.lang._
          // scala._
          // scala.Predef._

//  In Java lang package we have math class and in math class we have sqrt function
  println(Math.sqrt(16))
  println(java.lang.Math.sqrt(16))
println(Math.random())


  // 3. Apply method:
//    Apply serves a purpose of closing the gap of object oriented and functional paradigm in scala
  // We will be able to call an object like a function - things become easy
  object Manav{
    def apply(name:String, age:Int): Unit ={
      s"$name is $age years old"
    }
  }// how to construct a manav object
  Manav.apply("Nikhil", 33)
  Manav("Sumit", 33) // apply is automaticlly called

  case class Manav(name:String, age:Int)
  // how to create object of above?
  Manav("Nikhil", 20) // internaly it calls apply

// 4. Diamond problem in Scala and how it is solved?
// It is a problem which comes with multiple inheritance
/*

class A{
  def func1
}

class B{
  def func1
}

class C extends A, B{
func1 // there is ambiguity here, which one are we referring to? One from A or from B
// this is called diamond problem when both classes have same function name
//Diamond problem = multiple inheritance is not suported in scala

class A             class B


        class C
}

How is diamond problem handled in Scala?
  This is possible using traits in scala
  trait is to some extend like an interface in Java
  Trait provides more flexibility than interface
  Trait can have both implemented and non implemented things
  but interface only has unimplimented things
  Scala does not support inheritance from multiple classes but user can extend multiple triats in single class
*/

  trait traitA{
    def name = println("This is a grandparent trait")
  }

  trait traitB extends traitA {
    override def name = {
      println("B is a child of A")
      super.name
    }
  }

  trait traitC extends traitA {
    override def name = {
      println("C is a child of A")
      super.name
    }
  }

  object grandChild extends traitB with traitC //extending from two traits

  println(grandChild.name)
  /* order:
  C is a child of A
  B is a child of A
  This is a grandparent trait

  Heirarchy is from right to left -
 linearization rule comes into play when we decide heirarchy
*/

//5. Why scala is top choice over Java and Python?
  /*
  a. Functioonal approach is very good for big dat
  b. In few lines we can write powerfull code
  c. Spark Fundamentals is written in Scala, any new feature comes in Scala then rolled out to Python Java
  d. Scala gives the best performance
  e. Python process is created to interact wtih JVM which adds more time

   */

//>>>>>>>>>>>>>>>>
  // Session 4
//>>>>>>>>>>>>>>>>
//  1. Typesafe in scala: the complier will validate types while compiling (throw error of wrong type is assigned)
//  val a:Int = "Nikhil" // Error:(332, 15) type mismatch;
//  found   : String("Nikhil")
//  required: Int
//  val a:Int = "Nikhil"
//Errors are caught early

//  1. deffierence between statically typed language and dynamically typed lan
      //  statically typed: type of a variable is known at compile time rather than run time
              //      e.g. Java, Scala, C - we need to give datatype while writing/compiling
      //Dynamically types: datatype checked during at run time...variables are bound during run time

//  Adv of static over dynamic typing
  /*
  Static is better performance -everything is known in advance and has scope to do optimizations
  less chances of type errors


   */
//>>>>>>>>>>>>
  //Exception handling in  Scala:
//>>>>>>>>>>>>

  // Diff between Exception and Error:
//  Exception=   abnormal condition which arise becoz of our code like divide by 0
//  Error: abnormal condition which arise becoz of our system issues like OOM error
// How to handle exception gracefully
    //
try {
  val b = 5/0 // code which can throw exception
} catch {
  case e: Exception => println("Please give meaningfully values")
}  finally {
    println("I will always execute!")
  }

// There is something called as finally which is always executed


//>>>>>>>>>>
  // Monad
//>>>>>>>>>>
// It is neither a class nor a trait it is just a concept
//  Monad is an object which wraps another object in Scala - output at a step is given input to another step - kind of chaining
  val list34 = List(1,2,3,4)
  val list35 = List(5,6,7,8)

  val t19 = list34.flatMap{
    x => list35.map{ y => x+ y }
  }
println(t19)

  //>>>>>>>>>>
  // Streams
  //>>>>>>>>>>
// Streams are lazy list in scala
  val k = 100::200::300::Nil
  println(k)

val stream = 100 #:: 200 #:: 300#:: Stream.empty
  println(stream) //Stream(100, ?)
  val stream2 = Stream(1,2,3,4,5,6)
  println(stream2)


//  ofDim = to create a two dimensional array
  val twoDArr = Array.ofDim[Int](3,3)


  //>>>>>>>>>>>>>>>>
  // Design patterns
  //>>>>>>>>>>>>>>>>
/*
    Is a general reusable solution to a commonly occurring problemm in software design -  we can reuse the template
    It is a template to solve a problem that can be used in many different situation

    When we face some commonly occurring problems then we have something called as design pattern which are best practices to solve that problem


    Few design pattern:
    1) Factory design pattern:
        the main aim of a factory design pattern is that, it separates instance creation logic from the client
        we implement instance creation logic in a factory class without exposing the logic to the client
    2) Singleton: Restrict the multiple instance creation = if we want people to share same instance and save space and avoid confusion
    Just ONE object will be created and provides a global point of access to it.

    object Student{
      // class level functionality

    3. Lazy initialization design pattern:
        It is a technique to init a variable on its first access. When we access the variable only then it is init. Why to waste
        resources if it is never initialized
    }
 */


  // Factory design pattern
  trait Computer{ // first create a trait and have 3 unimplemented methods
    def ram: String
    def hdd: String
    def cpu: String
  }
//above - super class
  // below multiple sub class - based on input we need to return one of the subclass


  // lets create a class which extends this
  case class PC(ram:String, hdd:String, cpu:String) extends Computer
  case class Laptop(ram:String, hdd:String, cpu:String) extends Computer

  object ComputerFactory{// here we will hide the instance creation logic -
    def apply(compType:String, ram:String, hdd:String, cpu:String) = compType match {
      case "PC" => PC(ram,hdd,cpu)
      case "Laptop" => Laptop(ram,hdd,cpu)
    }
  }

  ComputerFactory("PC", "16GB", "1TB", "2.3GHz") // factory design pattern - client needs to call this ti create new type of computer -50pc 50 tiems call
  ComputerFactory("Laptop", "8GB", "500B", "2.3GHz")
   /*
   Benefits of factory design pattern

   1. Offers loose coupling between object creation and the client -client only provides args
   2. Clear separation of responsibilities
   3.. Easy to change object creation logic without affecting client code


    */


  //>>>>>>>>>>>>>
  // Diff between Array and ArrayBuffer
  //>>>>>>>>>>>>>
//  Both are mutable
  // ArrayBuffer is resizable
  // How to remove duplicated from and array

  val arr1 = Array(1,2,3,4,4,4,4)
  println(arr1.toSet)
  println(arr1.toSet.toArray)
  println(arr1.distinct)

//  package Basicz

  object  IW extends App { // object is a singletion
    //1. App
    // scala runs on JVm, it requires a main method
    // if main is not present then we need to add object obj extends App(a trait)
    //   def main(args: Array[String]): Unit = {
    //  }

    //  2. default args, named args, variable args
    def add(nbr1:Int, incrementBy:Int) = {
      nbr1 + incrementBy
    }
    println(add(5,1))

    // can we have default arguments?
    def addDefault(nbr:Int = 2, incrementBy :Int =  1): Unit ={
      nbr + incrementBy
    }
    println(addDefault(1))
    println(addDefault(1,3))
    println(addDefault(incrementBy = 3)) // named arguments

    def printfn(name:String*): Unit ={
      for(i <- name ){println(i)} // it can be a list as it is a varaible lenght parameter
    }
    printfn("Nikhil"," Mishra ","wins and this is a variable length argument")


    // 3. Nil,null,  None, Nothing, option, Unit
    // Null is a trait in scala - there exist only one instance of Null that is null
    def tryIt(thing : Null):Unit = println("msg1")
    //tryIt("nikhil")// will not work - type mismatch
    tryIt(null)// null is an of Null
    // we should restrinct the use of null as it can lead to null pointer exception

    println()
    //  Nil is an empty list
    val c = Nil
    println(c)


    //Nothing is a trait is scala -  there are no instances in Scala for Nothing
    // when evr there is an error the return is Nothing
    def fun() = {
      throw new Exception
    }

    //Option
    // When we run into a situation where we do not have a useful value to return from a function
    // returning null is not preferred as it can lead to null pointer exception
    // scala has a built in solution to this problem
    // when we return using option we use None or Some

    def getAStringMaybe(num:Int): Option[String] = {
      if(num>=0) Some("Positive number")
      else None
    }

    println(getAStringMaybe(-1))
    println(getAStringMaybe(0))

    def printResult(num:Int) ={
      getAStringMaybe(num) match {
        case Some(str) => println(str)
        case None => println(None)
      }
    }


    printResult(-10)// gracefully handles None value using Options
    printResult(10)

    // How do you deal with nulls? Using options!!!!!!
    // Options return Some or None - a graceful way of handling

    // Unit -  it is like a void in Java to some extend
    // Unit is returned when there are side effects, say for example when we print something

    case class address(city:String, state:String, zip:String)
    /*class user(email:String, Password:String){// wrong style
      var firstName = _
      var lastName = _
      var address = _
    }
  */
    // lets create instance of above class
    val usr1 =   new user("trendy@gmail.com", "abcd")
    //  println(usr1.firstName.length)

    class user(email:String, Password:String){// wrong style
      var firstName : Option[String] = None
      var lastName  : Option[String] = Some("Mishra")
      var address  : Option[address] = None
    }


    // lets create instance of above class
    val usr2 =   new user("trendy@gmail.com", "abcd")
    println(usr2.firstName.getOrElse("Not assigned")) // here we have gracully handled the null pointer exception or null values in scala
    println(usr2.lastName.getOrElse("Not assigned")) // here we have gracully handled the null pointer exception or null values in scala

    usr2.firstName = Some("Nikhil")
    usr2.firstName = Some("Mishra")
    usr2.address = Some(address("Bangalore", "KT", "232323"))
    println(usr2.address.getOrElse("Not assigned")) // here we have gracully handled the null pointer exception or null values in scala
    // we should not use nulls in our code and should use options in our code -  whenever we are not sure if a value will come or not
    // we can handle nulls using getOrElse

    //  revision"
    /*
    App: inbuilt main
    Args: default, naems, variable length args
    nil, null, None, Nothing, option Unit in scala
    getOrElse - forn handling the nulls
     */

    // what is yield???
    val t = Array(1,2,3,4,5,6)

    //  val b = for(i <- 1 to 10) i*i
    //  println(b)

    val b = for(i <- 1 to 10) yield i*i
    println(b)  // Vector(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)

    //println is a side effect instead we want to get the reuslts
    // in a new list from a for loop so that there are no side effects
    // Vector = both indexed and Immutable collection
    val t5 =   for( i <- 1 to 10) yield {
      if (i%2 ==0)
        i*i
    }
    println(t5) //Vector((), 4, (), 16, (), 36, (), 64, (), 100)

    // above can be avoided by if guard

    val t6 =   for( i <- 1 to 10; if i%2 ==0) yield {// this is if guard and will filter the values in loop itself, before yield
      i*i
    }
    println(t6)//Vector(4, 16, 36, 64, 100)  using if guard

    //more beautification
    val t7 =  for{ i <- 1 to 10
                   if i%2 ==0
    } yield { i*i  }

    println(t7)
    //  Vector(4, 16, 36, 64, 100)
    //yield = to create a new lit from for loop
    //  if guard = filter results from forr loop
    // Pattern guard:
    //    Case statements can be combined with if expressions to provide extra logic during pattern matching for xtra logic

    def checkSign(x:Int):String ={
      x match {
        case a if a < 0 => s"$a is a negative number"   // case with if is a Pattern guard
        case b if b > 0 => s"$b is a positive number"
        case c  => s"$c is a none of the above"
      }}
    println(checkSign(10))
    println(checkSign(-10))
    println(checkSign(0))

    //Option : None and Some for catching values
    def f(x: Option[Int]) = x match {
      case Some(i) if i%2 ==0 => checkSign(10)
      case None => println("None") //odd number are not handled we might get exeption - we shd have all the cases - remedy add wildcard below
      case _  => println("Else")
    }


    // For comprehension - interview question
    for(i <- 1 to 10) println(i*i)
    // above translates to -  scala internally transalted to this
    (1 to 10).foreach(i => println(i*i))


    for(i <- 1 to 10) yield i+2
    // above translates to
    (1 to 10).map(i => i +2) // for loop is internally converted to this - for better performance
    // scala internally uses map, flatmpa etc, harder syntax for better performance



    // Write nested for loop
    val t9 =   for(i <- 1 to 10; j <- 'a' to 'c') yield  i *2 + " " + j
    println(t9)//Vector(2 a, 2 b, 2 c, 4 a, 4 b, 4 c, 6 a, 6 b, 6 c, 8 a, 8 b, 8 c, 10 a, 10 b, 10 c, 12 a, 12 b, 12 c, 14 a, 14 b, 14 c, 16 a, 16 b, 16 c, 18 a, 18 b, 18 c, 20 a, 20 b, 20 c)


    // == in scala vs Java
    /*In Java:
    == is used for reference comparison
    to check natural equality we use equals method in Java

     */

    //  val s9 = new String("Nikhil")
    //  val s10 = new String("Nikhil")
    //  s9 == s10 == this will return false as both are different instances or references - they are diff objects in Java
    //  s9.equals(s10) = gives true as content is same

    //  In Scala == is for content equality check - it internally implements equals method
    // In Scala to compare the reference we have eq

    val s9 = new String("Nikhil")
    val s10 = new String("Nikhil")
    println(s9 == s10)  // this will return true in Scala,
    //  == is method
    println(s9.==(s10))
    println(s9.eq(s10)) // should give false as it is a reference comarison /// instances are compared

    // New session IW
    // 1. Strict val and lazy val

    val r = 10
    val area = {
      println("Calculating area of a  circle")
      3.14*r*r
    }// evalauted right now, area is now 314
    println(area)

    lazy val area_lazy = {
      println("Calculating area of a  circle")
      3.14*r*r
    }// NOT evalauted right now, only when below is executed then = lazy evaluated whn first used
    println(area_lazy)

    // 2. Default packages in scala / no need to import them
    // 3 packages are implicitly imported by Scala =>
    // java.lang._
    // scala._
    // scala.Predef._

    //  In Java lang package we have math class and in math class we have sqrt function
    println(Math.sqrt(16))
    println(java.lang.Math.sqrt(16))
    println(Math.random())


    // 3. Apply method:
    //    Apply serves a purpose of closing the gap of object oriented and functional paradigm in scala
    // We will be able to call an object like a function - things become easy
    object Manav{
      def apply(name:String, age:Int): Unit ={
        s"$name is $age years old"
      }
    }// how to construct a manav object
    Manav.apply("Nikhil", 33)
    Manav("Sumit", 33) // apply is automaticlly called

    case class Manav(name:String, age:Int)
    // how to create object of above?
    Manav("Nikhil", 20) // internaly it calls apply

    // 4. Diamond problem in Scala and how it is solved?
    // It is a problem which comes with multiple inheritance
    /*

    class A{
      def func1
    }

    class B{
      def func1
    }

    class C extends A, B{
    func1 // there is ambiguity here, which one are we referring to? One from A or from B
    // this is called diamond problem when both classes have same function name
    //Diamond problem = multiple inheritance is not suported in scala

    class A             class B


            class C
    }

    How is diamond problem handled in Scala?
      This is possible using traits in scala
      trait is to some extend like an interface in Java
      Trait provides more flexibility than interface
      Trait can have both implemented and non implemented things
      but interface only has unimplimented things
      Scala does not support inheritance from multiple classes but user can extend multiple triats in single class
    */

    trait traitA{
      def name = println("This is a grandparent trait")
    }

    trait traitB extends traitA {
      override def name = {
        println("B is a child of A")
        super.name
      }
    }

    trait traitC extends traitA {
      override def name = {
        println("C is a child of A")
        super.name
      }
    }

    object grandChild extends traitB with traitC //extending from two traits

    println(grandChild.name)
    /* order:
    C is a child of A
    B is a child of A
    This is a grandparent trait

    Heirarchy is from right to left -
   linearization rule comes into play when we decide heirarchy
  */

    //5. Why scala is top choice over Java and Python?
    /*
    a. Functioonal approach is very good for big dat
    b. In few lines we can write powerfull code
    c. Spark Fundamentals is written in Scala, any new feature comes in Scala then rolled out to Python Java
    d. Scala gives the best performance
    e. Python process is created to interact wtih JVM which adds more time

     */

    //>>>>>>>>>>>>>>>>
    // Session 4
    //>>>>>>>>>>>>>>>>
    //  1. Typesafe in scala: the complier will validate types while compiling (throw error of wrong type is assigned)
    //  val a:Int = "Nikhil" // Error:(332, 15) type mismatch;
    //  found   : String("Nikhil")
    //  required: Int
    //  val a:Int = "Nikhil"
    //Errors are caught early

    //  1. deffierence between statically typed language and dynamically typed lan
    //  statically typed: type of a variable is known at compile time rather than run time
    //      e.g. Java, Scala, C - we need to give datatype while writing/compiling
    //Dynamically types: datatype checked during at run time...variables are bound during run time

    //  Adv of static over dynamic typing
    /*
    Static is better performance -everything is known in advance and has scope to do optimizations
    less chances of type errors


     */
    //>>>>>>>>>>>>
    //Exception handling in  Scala:
    //>>>>>>>>>>>>

    // Diff between Exception and Error:
    //  Exception=   abnormal condition which arise becoz of our code like divide by 0
    //  Error: abnormal condition which arise becoz of our system issues like OOM error
    // How to handle exception gracefully
    //
    try {
      val b = 5/0 // code which can throw exception
    } catch {
      case e: Exception => println("Please give meaningfully values")
    }  finally {
      println("I will always execute!")
    }

    // There is something called as finally which is always executed


    //>>>>>>>>>>
    // Monad
    //>>>>>>>>>>
    // It is neither a class nor a trait it is just a concept
    //  Monad is an object which wraps another object in Scala - output at a step is given input to another step - kind of chaining
    val list34 = List(1,2,3,4)
    val list35 = List(5,6,7,8)

    val t19 = list34.flatMap{
      x => list35.map{ y => x+ y }
    }
    println(t19)

    //>>>>>>>>>>
    // Streams
    //>>>>>>>>>>
    // Streams are lazy list in scala
    val k = 100::200::300::Nil
    println(k)

    val stream = 100 #:: 200 #:: 300#:: Stream.empty
    println(stream) //Stream(100, ?)
    val stream2 = Stream(1,2,3,4,5,6)
    println(stream2)


    //  ofDim = to create a two dimensional array
    val twoDArr = Array.ofDim[Int](3,3)


    //>>>>>>>>>>>>>>>>
    // Design patterns
    //>>>>>>>>>>>>>>>>
    /*
        Is a general reusable solution to a commonly occurring problemm in software design -  we can reuse the template
        It is a template to solve a problem that can be used in many different situation

        When we face some commonly occurring problems then we have something called as design pattern which are best practices to solve that problem


        Few design pattern:
        1) Factory design pattern:
            the main aim of a factory design pattern is that, it separates instance creation logic from the client
            we implement instance creation logic in a factory class without exposing the logic to the client
        2) Singleton: Restrict the multiple instance creation = if we want people to share same instance and save space and avoid confusion
        Just ONE object will be created and provides a global point of access to it.

        object Student{
          // class level functionality

        3. Lazy initialization design pattern:
            It is a technique to init a variable on its first access. When we access the variable only then it is init. Why to waste
            resources if it is never initialized
        }
     */


    // Factory design pattern
    trait Computer{ // first create a trait and have 3 unimplemented methods
      def ram: String
      def hdd: String
      def cpu: String
    }
    //above - super class
    // below multiple sub class - based on input we need to return one of the subclass


    // lets create a class which extends this
    case class PC(ram:String, hdd:String, cpu:String) extends Computer
    case class Laptop(ram:String, hdd:String, cpu:String) extends Computer

    object ComputerFactory{// here we will hide the instance creation logic -
      def apply(compType:String, ram:String, hdd:String, cpu:String) = compType match {
        case "PC" => PC(ram,hdd,cpu)
        case "Laptop" => Laptop(ram,hdd,cpu)
      }
    }

    ComputerFactory("PC", "16GB", "1TB", "2.3GHz") // factory design pattern - client needs to call this ti create new type of computer -50pc 50 tiems call
    ComputerFactory("Laptop", "8GB", "500B", "2.3GHz")
    /*
    Benefits of factory design pattern

    1. Offers loose coupling between object creation and the client -client only provides args
    2. Clear separation of responsibilities
    3.. Easy to change object creation logic without affecting client code


     */


    //>>>>>>>>>>>>>
    // Diff between Array and ArrayBuffer
    //>>>>>>>>>>>>>
    //  Both are mutable
    // ArrayBuffer is resizable
    // How to remove duplicated from and array

    val arr1 = Array(1,2,3,4,4,4,4)
    println(arr1.toSet)
    println(arr1.toSet.toArray)
    println(arr1.distinct)


  }

}
