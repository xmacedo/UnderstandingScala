class ForComprehensions {
  //https://docs.scala-lang.org/tour/for-comprehensions.html#inner-main
  
  //Scala offers a lightweight notation for expressing sequence comprehensions. 
  // Comprehensions have the form for (enumerators) yield e, where enumerators refers to a list of enumerators. 
  // An enumerator is either a generator, or it is a guard (see: Control Structures). 
  // A comprehension evaluates the body e for each binding generated 
  // by the enumerators and returns a sequence of these values.
  case class User(name: String, age: Int)

  val userBase = List(
    User("Travis", 28),
    User("Kelly", 33),
    User("Jennifer", 44),
    User("Dennis", 23))

  val twentySomethings =
    for user <- userBase if user.age >= 20 && user.age < 30
      yield user.name // i.e. add this to a list

  twentySomethings.foreach(println) // prints Travis Dennis

}
