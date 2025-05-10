package monads

class Option {
  //https://www.scala-lang.org/api/3.x/scala/Option.html#
  //Represents optional values. Instances of Option are either an instance of $some or the object $none.

  //The most idiomatic way to use an $option instance is to treat it as a collection or monad and use
  // map,flatMap, filter, or foreach:

  val name: Option[String] = request getParameter "name"
  val upper = name map {
    _.trim
  } filter {
    _.length != 0
  } map {
    _.toUpperCase
  }
  println(upper getOrElse "")

  //Note that this is equivalent to
  val upper = for {
    name <- request getParameter "name"
    trimmed <- Some(name.trim)
    upper <- Some(trimmed.toUpperCase) if trimmed.length != 0
  } yield upper
  println(upper getOrElse "")

  //Because of how for comprehension works, if $none is returned from request.getParameter,
  // the entire expression results in $none

  //This allows for sophisticated chaining of $option values without having to check for the existence of a value.

  //These are useful methods that exist for both $some and $none. - isDefined — True if not empty - isEmpty
  // — True if empty - nonEmpty — True if not empty - orElse — Evaluate and return alternate optional value if empty
  // - getOrElse — Evaluate and return alternate value if empty - get — Return value, throw exception if empty
  // - fold — Apply function on optional value, return default if empty - map — Apply a function on the optional
  // value - flatMap — Same as map but function must return an optional value - foreach —
  // Apply a procedure on option value - collect — Apply partial pattern match on optional value - filter —
  // An optional value satisfies predicate - filterNot — An optional value doesn't satisfy predicate - exists —
  // Apply predicate on optional value, or false if empty - forall — Apply predicate on optional value,
  // or true if empty - contains — Checks if value equals optional value, or false if empty - zip —
  // Combine two optional values to make a paired optional value - unzip — Split an optional pair to two
  // optional values - unzip3 — Split an optional triple to three optional values - toList — Unary list of
  // optional value, otherwise the empty list

  //A less-idiomatic way to use $option values is via pattern matching:
  val nameMaybe = request getParameter "name"
  nameMaybe match {
    case Some(name) =>
      println(name.trim.toUppercase)
    case None =>
      println("No name value")
  }
  //Interacting with code that can occasionally return null can be safely wrapped in $option to become
  // $none and $some otherwise.

  val abc = new java.util.HashMap[Int, String]
  abc.put(1, "A")
  bMaybe = Option(abc.get(2))
  bMaybe match {
    case Some(b) =>
      println(s"Found $b")
    case None =>
      println("Not found")
  }


}
