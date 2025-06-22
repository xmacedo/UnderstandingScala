class PartialFunctions {
  //https://docs.scala-lang.org/scala3/book/fun-partial-functions.html
  //A partial function is a function that may not be defined for all values of its argument type. 
  // In Scala, partial functions are unary functions implementing the PartialFunction[A, B] trait, 
  // where A is the argument type and B the result type.
  
  //To define a partial function, use a case identical to those used in match expressions:
  val doubledOdds: PartialFunction[Int, Int] = {
    case i if i % 2 == 1 => i * 2
  }
}
