package monads

class Either {
  
  //https://www.scala-lang.org/api/3.x/scala/util/Either.html
  //Represents a value of one of two possible types (a disjoint union).
  // An instance of Either is an instance of either scala.util.Left or scala.util.Right.
  
  //A common use of Either is as an alternative to scala.Option for dealing with possibly missing values. 
  // In this usage, scala.None is replaced with a scala.util.Left which can contain useful information. 
  // scala.util.Right takes the place of scala.Some. Convention dictates that Left 
  // is used for failure and Right is used for success.
  
  //For example, you could use Either[String, Int] to indicate whether a received input is a String or an Int.

  import scala.io.StdIn._

  val in = readLine("Type Either a string or an Int: ")
  val result: Either[String, Int] =
    try Right(in.toInt)
    catch {
      case e: NumberFormatException => Left(in)
    }

  result match {
    case Right(x) => s"You passed me the Int: $x, which I will increment. $x + 1 = ${x + 1}"
    case Left(x) => s"You passed me the String: $x"
  }
  
  //Either is right-biased, which means that Right is assumed to be the default case to operate on. 
  // If it is Left, operations like map and flatMap return the Left value unchanged:
  def doubled(i: Int) = i * 2

  Right(42).map(doubled) // Right(84)
  Left(42).map(doubled) // Left(42)
  
  //Since Either defines the methods map and flatMap, it can also be used in for comprehensions:
  val right1 = Right(1): Right[Double, Int]
  val right2 = Right(2)
  val right3 = Right(3)
  val left23 = Left(23.0): Left[Double, Int]
  val left42 = Left(42.0)

  for {
    x <- right1
    y <- right2
    z <- right3
  } yield x + y + z // Right(6)

  for {
    x <- right1
    y <- right2
    z <- left23
  } yield x + y + z // Left(23.0)

  for {
    x <- right1
    y <- left23
    z <- right2
  } yield x + y + z // Left(23.0)

  // Guard expressions are not supported:
  for {
    i <- right1
    if i > 0
  } yield i
  // error: value withFilter is not a member of Right[Double,Int]

  // Similarly, refutable patterns are not supported:
  for (x: Int <- right1) yield x
  // error: value withFilter is not a member of Right[Double,Int]

  // To use a filtered value, convert to an Option first,
  // which drops the Left case, as None contains no value:
  for {
    i <- right1.toOption
    if i > 0
  } yield i
  
  //Since for comprehensions use map and flatMap, the types of function parameters used in the expression 
  // must be inferred. These types are constrained by the Either values. 
  // In particular, because of right-biasing, Left values may require an explicit type argument for type parameter B, 
  // the right value. Otherwise, it might be inferred as Nothing.

  for {
    x <- left23
    y <- right1
    z <- left42 // type at this position: Either[Double, Nothing]
  } yield x + y + z
  //            ^
  // error: ambiguous reference to overloaded definition,
  // both method + in class Int of type (x: Char)Int
  // and  method + in class Int of type (x: Byte)Int
  // match argument types (Nothing)

  for (x <- right2; y <- left23) yield x + y // Left(23.0)
  for (x <- right2; y <- left42) yield x + y // error

  for {
    x <- right1
    y <- left42 // type at this position: Either[Double, Nothing]
    z <- left23
  } yield x + y + z
  // Left(42.0), but unexpectedly a `Either[Double,String]`
}
