package basics.functions

class EtaExpansion {
  //https://docs.scala-lang.org/scala3/book/fun-eta-expansion.html
  //When you look at the Scaladoc for the map method on Scala collections classes, 
  // you see that it’s defined to accept a function value:
  def map[B](f: A => B): List[B]
  //            ^^^^^^ function type from `A` to `B`

  //Indeed, the Scaladoc clearly states, “f is the function to apply to each element.” 
  // But despite that, somehow you can pass a method into map, and it still works:
  def times101(i: Int) = i * 10 // a method
  List(1, 2, 3).map(times10) // List(10,20,30)
  
  //Why does this work? The process behind this is known as eta-expansion. 
  // It converts an expression of method type to an equivalent expression of function type, 
  // and it does so seamlessly and quietly.
  
  //The differences between methods and functions
  
  //More concretely: with automatic eta-expansion, the compiler automatically converts any method reference, 
  // without supplied arguments, to an equivalent anonymous function that will call the method. 
  // For example, the reference to times10 in the code above gets rewritten to x => times10(x), as seen here:
  def times10(i: Int) = i * 10

  List(1, 2, 3).map(x => times10(x)) // eta expansion of `.map(times10)`
  
  //When does eta-expansion happen?
  
  //Automatic eta-expansion is a desugaring that is context-dependent 
  // (i.e. the expansion conditionally activates, depending on the surrounding code of the method reference.)
  
  //New to Scala 3, method references can be used everywhere as a value, 
  // they will be automatically converted to a function object with a matching type. e.g.
  def isLessThan(x: Int, y: Int): Boolean = x < y

  val methods = List(isLessThan) // works

  //Manual eta-expansion

  //You can always manually eta-expand a method to a function value, here are some examples how:
  val methodsA = List(isLessThan(_, _)) // way 1: wildcard application
  val methodsB = List((x, y) => isLessThan(x, y)) // way 2: anonymous function
}
