class Tuples {
  //https://docs.scala-lang.org/tour/tuples.html

  //Tuples

  //In Scala, a tuple is a value that contains a fixed number of elements, each with its own type.
  // Tuples are immutable.

  //Tuples are especially handy for returning multiple values from a method.
  //A tuple with two elements can be created as follows:
  val ingredient = ("Sugar", 25)

  //This creates a tuple containing a String element and an Int element.
  //The inferred type of ingredient is (String, Int).

  //Accessing the elements
  //One way of accessing tuple elements is their positions. 
  // The individual elements are accessed with tuple(0), tuple(1), and so forth.
  println(ingredient(0)) // Sugar
  println(ingredient(1)) // 25
  
  //Pattern matching on tuples
  //A tuple can also be taken apart using pattern matching:
  val (name, quantity) = ingredient
  println(name) // Sugar
  println(quantity) // 25
  
  //Here name’s inferred type is String and quantity’s inferred type is Int.
  
  //Here is another example of pattern-matching a tuple:
  val planets =
    List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6),
      ("Mars", 227.9), ("Jupiter", 778.3))
  planets.foreach {
    case ("Earth", distance) =>
      println(s"Our planet is $distance million kilometers from the sun")
    case _ =>
  }
  //Or, in a for comprehension:
  val numPairs = List((2, 5), (3, -7), (20, 56))
  for (a, b) <- numPairs do
    println(a * b)
    
  
  
}
