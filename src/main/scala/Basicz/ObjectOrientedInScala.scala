package Basicz
//redo this recording // very imp
object  ObjectOrientedInScala extends App{

  object Person{
    // class level functionality same like static in Java
    val N_EYES = 2
    def calFly :Boolean = false
  } // only one instance is created for this - no one can create more instances of this
  // When we create object then we get a singleton referece with the same nae
  // object person is instance of class Person = Singleton deising pattern and everyone shares this instance

  class Person(val name:String, val age:Int){
    //Instance specific functionality - 50 instances 50 different features
    def salaryDoubler(salary:Int) = salary*2
  }
  println(Person.N_EYES)
  println(Person.calFly)

  val mary = Person
  val john = Person
  println(mary == john)

//Companion objects = companion design patterns = in a file when we have class(new for all) and object(same for all) both
  val jim = new Person("Jim", 43)
  val shawm = new Person("Shawn", 41)

  Person.N_EYES // any person will have two eyes
  println (jim.age) // but person jim will have different salary than shawn
  println (shawm.age)
  println(jim==shawm)
//>>>>>>>>>>>>>>>>>>>>>>>>>>>
//  How to create a class
//  How to create class instance
//  How to create class parameters and promoting them to feilds - putting val/var before it
//  Class level functionality in a object
//  design pattern - in a single file we have object and class with same name - this is called companion design patter
//  Obejct isused for class level functionality and class us used for instance level fucntionaliy
//
//  Singleton design pattern is implemented using object
//
  //>>>>>>>>>>>>>>>>>>>>>>>>>>>
  // Inheritance
  // access modifiers
  // abstract class
  // trait


  class Animal{
    def eats = println("Animals eat grass") // if declared as private then child cannot access it
  }

  //Cow class is child class - parent class is Animal - cat inherits from animal
  class Cow extends Animal{
    def preferredMeal = println("But I eat Fodder")
  }

  val obergene = new Cow
  obergene.eats // child class calls method of parent class
  obergene.preferredMeal //
  // Inherticnce - child class inherting from parent class
  // Access modifiers allowed in Scala - private and public(also no modifier)
  // protected - only wihtin child class we can use it but not outside
  // private  // if declared as private then child cannot access it
  //
  //
  // /


  // 3. Abstract class
  abstract class Fish{
    def sleep = print("Fish sleeps a lot") // some implemented
    val creatureType:String
    def eat  // some not implemented
  }
// Abstract class can contain Undefined unimplemented methods/data members
  // the whole purpose is to implement it later by inherting it in child class
  // instantiation of abstract class is not possible
  // cannot do new Animal
  // we need to extend this class and then override the implemented methid


   class Whale extends Fish{
     val creatureType: String = "vertibrate"
     def eat: Unit = println("they never sleep")
     override def sleep = println("in tanker I sleep")
  }
  val neemo = new Whale
  print(neemo.creatureType)
  print (neemo.eat)
  neemo.sleep

  // what if we want to inherit multple instances?
  // Multiple inheritance is not possible
  // child class cannot inherit two or more classes


  // trait: like abstract class only
    trait Carnivore {
    def preferredMeal
  }

  trait ColdBlodded{
    def hasPlatesOnSkin = print("Has long tail")
  }

  // we cannot inherit from multiple class
  // we can extend multiple traits
  class Crocodile extends Fish with Carnivore with ColdBlodded{
    override def eat: Unit = println("Fish")

    override val creatureType: String = "Sea animal"

    override def preferredMeal: Unit = print ("Hiren")
  }

  val croc = new Crocodile
  println(croc.sleep)
  println(croc.creatureType)
  println(croc.preferredMeal)
  croc.eat
  croc.hasPlatesOnSkin
println()
// traits are behavior - they normally identify a behvior
// they mark some behavior
// we cannot have a constructor parameter(Arguments)
// Abstract class is same as traits -- both can have implemented as well as unimplemented methods
// In java, we have interfaces which are totally unimplemented
// but in traits we can have implemented and unimplemented methods
// Muiltiple inhertiance is possible in scala with help of traits
//Expect traits in interview
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
// Case class in Scala
  // Are special kind of classes where you need to write less code
  // Avoids the need of boiler plate code
case class Insan(name:String, age:Int)
//Advantage 1:
//  to promote a class parameter to a field we need to add a val or a var
//  but in case class we do not have to do that -  in case class all the parameters are promoted to field
val gem = new Insan("Nikhil", 33)
  println(gem.age)
  print(gem.name)
// Advantage 2:
  //  case classed have sensible toString
  println(mary.toString) // useles NikhilBasicz.OO$Person$@b97c004
  println (gem.toString) // much better not cryptic
  println (gem) // much better not cryptic

  // Advantage 3:
  // equals and hashCode method implemented already
  println(jim == shawm) // here references are compared and cryptic values are checked
val broc = new Insan("Nikhil", 33)
  println(gem==broc)


  // Advantage 4:
  // Companion objects
//when we create a case class we aautomatically create a companion object
  val gem2 = Insan.apply("Nikhilsdasd", 32) //  no new keyword is used ... companion object is used

  // Advantage 5: they have a handy copy method
  val heer = gem.copy(age = 3234)
  println(heer.name)
  println(heer.age)

  // Advantage 6: case classes are serialiazabe and can be trasferred over the network

}
