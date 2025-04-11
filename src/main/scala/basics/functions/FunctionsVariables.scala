package basics.functions

class FunctionsVariables {
  //https://docs.scala-lang.org/scala3/book/fun-function-variables.html
  val double = (i: Int) => i * 2


  //Now you can call the double function like this:
  val x = double(2)   // 4

  //You can also pass double into a map call:
  List(1, 2, 3).map(double)   // List(2, 4, 6)

  val triple = (i: Int) => i * 3

  //you can store them in a List or Map:
  val functionList = List(double, triple)

  val functionMap = Map(
    "2x" -> double,
    "3x" -> triple
  )

  //If you paste those expressions into the REPL, you’ll see that they have these types:
  // a List that contains functions of the type `Int => Int`
  functionList: List[Int => Int]

  // a Map whose keys have the type `String`, and whose
  // values have the type `Int => Int`
  functionMap: Map[String, Int => Int]

}
