package basics.functions

class AnonymousFunctions {
  //https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
  //Anonymous Functions
  val ints = List(1, 2, 3)
  
  val doubledInts = ints.map(_ * 2)   // List(2, 4, 6)

  //Longer forms (same result as the previous one)
  val doubledIntsA = ints.map((i: Int) => i * 2)
  val doubledIntsB = ints.map((i) => i * 2)
  val doubledIntsC = ints.map(i => i * 2)


  //more explict
  val doubledIntsD = ints.map((i: Int) => i * 2)

  //Because the Scala compiler can infer from the data in ints that i is an Int, the Int declaration can be removed:
  val doubledIntsE = ints.map((i) => i * 2)

  //Because there’s only one argument, the parentheses around the parameter i aren’t needed:
  val doubledIntsF = ints.map(i => i * 2)

  //Because Scala lets you use the _ symbol instead of a variable name when the parameter appears only once in your function,
  // the code can be simplified even more:
  val doubledIntsG = ints.map(_ * 2)   // List(2, 4, 6)


  //Going even shorter
  ints.foreach((i: Int) => println(i))

  //As before, the Int declaration isn’t required, and because there’s only one argument,
  // the parentheses around i aren’t needed:
  ints.foreach(i => println(i))

  //Because i is used only once in the body of the function, the expression can be further simplified with the _ symbol:
  ints.foreach(println(_))

  //Finally, if an anonymous function consists of one method call that takes a single argument,
  // you don’t need to explicitly name and specify the argument, so you can finally write only
  // the name of the method (here, println):
  ints.foreach(println)
}
