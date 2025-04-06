package basics

class Variables {

  // https://docs.scala-lang.org/scala3/book/taste-vars-data-types.html
  //
  // immutable
  val a = 0

  // mutable
  var t = 1

  // Variable types
  val exp: Int = 1 // explicit
  val impl = 1 // implicit; the compiler infers the type

  // data types
  val b: Byte = 1
  val i: Int = 1
  val l: Long = 1
  val s: Short = 1
  val d: Double = 2.0
  val f: Float = 3.0

  val toLong = 1_000L // val x: Long = 1000
  val toDouble = 2.2D // val y: Double = 2.2
  val toFloat = 3.3F // val z: Float = 3.3

  // large numbers
  var toBigInt = BigInt(1_234_567_890_987_654_321L)
  var toBigDecimal = BigDecimal(123_456.789) // more precision

  //
  val name = "Bill" // String
  val c = 'a' // Char

  //String interpolation
  val firstName = "John"
  val mi = 'C'
  val lastName = "Doe"

  println(s"Name: $firstName $mi $lastName")   // "Name: John C Doe"

  //Embed arbitrary expressions inside a string
  println(s"2 + 2 = ${2 + 2}") // prints "2 + 2 = 4"

  val x = -1
  println(s"x.abs = ${x.abs}") // prints "x.abs = 1"

  //Multine strings
  val quote =
    """The essence of Scala:
                 Fusion of functional and object-oriented
                 programming in a typed setting."""
}
