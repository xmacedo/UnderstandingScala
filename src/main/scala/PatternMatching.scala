class PatternMatching {
  //https://docs.scala-lang.org/tour/pattern-matching.html
  //Pattern matching is a mechanism for checking a value against a pattern.
  // A successful match can also deconstruct a value into its constituent parts.
  // It is a more powerful version of the switch statement in Java and it can likewise
  // be used in place of a series of if/else statements.

  //A match expression has a value, the match keyword, and at least one case clause.

  import scala.util.Random

  val x: Int = Random.nextInt(10)

  x match
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"

  //The val x above is a random integer between 0 and 9.
  // x becomes the left operand of the match operator and on the right is an expression with four cases.
  // The last case _ is a “catch all” case for any other possible Int values. Cases are also
  // called alternatives.

  //Match expressions have a value.
  def matchTest(x: Int): String = x match
    case 1 => "one"
    case 2 => "two"
    case _ => "other"

  matchTest(3) // returns other
  matchTest(1) // returns one

  //This match expression has a type String because all of the cases return String.
  // Therefore, the function matchTest returns a String.

}
