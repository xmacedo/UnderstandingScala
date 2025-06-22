class PartialFunctions {
  //https://docs.scala-lang.org/scala3/book/fun-partial-functions.html
  //A partial function is a function that may not be defined for all values of its argument type. 
  // In Scala, partial functions are unary functions implementing the PartialFunction[A, B] trait, 
  // where A is the argument type and B the result type.
  
  //To define a partial function, use a case identical to those used in match expressions:
  val doubledOdds: PartialFunction[Int, Int] = {
    case i if i % 2 == 1 => i * 2
  }

  //To check if a partial function is defined for an argument, use the isDefinedAt method:
  doubledOdds.isDefinedAt(3) // true
  doubledOdds.isDefinedAt(4) // false

  //Trying to apply a partial function to an argument not belonging to its domain results in MatchError:
  doubledOdds(4) // Exception in thread "main" scala.MatchError: 4

  //Using partial functions
  //A partial function can be passed as an argument to a method:
  val res = List(1, 2, 3).collect({ case i if i % 2 == 1 => i * 2 }) // List(2, 6)
  
  //You can define a default value for arguments not in domain with applyOrElse:
  doubledOdds.applyOrElse(4, _ + 1) // 5
  
  //Two partial function can be composed with orElse, the second function will be applied 
  // for arguments where the first one is not defined:
  val incrementedEvens: PartialFunction[Int, Int] = {
    case i if i % 2 == 0 => i + 1
  }

  val res2 = List(1, 2, 3).collect(doubledOdds.orElse(incrementedEvens)) // List(2, 3, 6)
  
}
