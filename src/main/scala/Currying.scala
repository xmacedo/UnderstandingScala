class Currying {
  //https://docs.scala-lang.org/tour/multiple-parameter-lists.html#comparison-with-currying
  //You may sometimes see a method with multiple parameter lists referred to as “curried”.
  
  //As the Wikipedia article on currying states:
  
  //Currying is the technique of converting a function that takes multiple arguments into a sequence 
  // of functions that each takes a single argument
  
  //We discourage the use of the word “curry” in reference to Scala’s multiple parameter lists, for two reasons:
  
  //1) In Scala, multiple parameters and multiple parameter lists are specified and implemented directly, 
  // as part of the language, rather being derived from single-parameter functions.
  
  //2) There is danger of confusion with the Scala standard library’s curried and uncurried methods, 
  // which don’t involve multiple parameter lists at all.
  
  //Regardless, there are certainly similarities to be found between multiple parameter lists and currying. 
  // Though they are different at the definition site, the call site might nonetheless look identical,
  // as in this example:

  // version with multiple parameter lists
  def addMultiple(n1: Int)(n2: Int) = n1 + n2

  // two different ways of arriving at a curried version instead
  def add(n1: Int, n2: Int) = n1 + n2

  val addCurried1 = (add _).curried
  val addCurried2 = (n1: Int) => (n2: Int) => n1 + n2
  // regardless, all three call sites are identical
  addMultiple(3)(4) // 7
  addCurried1(3)(4) // 7
  addCurried2(3)(4) // 7
}
