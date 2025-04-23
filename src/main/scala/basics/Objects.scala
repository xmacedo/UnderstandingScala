package basics

class Objects {
  //https://docs.scala-lang.org/scala3/book/domain-modeling-tools.html#objects
  
  //An object is a class that has exactly one instance. It’s initialized lazily when its members are referenced, 
  // similar to a lazy val. Objects in Scala allow grouping methods and fields under one namespace, 
  // similar to how you use static members on a class in Java, Javascript (ES6), or @staticmethod in Python.
  
  //Declaring an object is similar to declaring a class. Here’s an example of a “string utilities” 
  // object that contains a set of methods for working with strings:
  object StringUtils:
    def truncate(s: String, length: Int): String = s.take(length)

    def containsWhitespace(s: String): Boolean = s.matches(".*\\s.*")

    def isNullOrEmpty(s: String): Boolean = s == null || s.trim.isEmpty

  //We can use the object as follows:
  StringUtils.truncate("Chuck Bartowski", 5)  // "Chuck"
  
  //Importing in Scala is very flexible, and allows us to import all members of an object:
  import StringUtils.*

  truncate("Chuck Bartowski", 5) // "Chuck"
  containsWhitespace("Sarah Walker") // true
  isNullOrEmpty("John Casey") // false
  
  //or just some members:
  import StringUtils.{truncate, containsWhitespace}

  truncate("Charles Carmichael", 7) // "Charles"
  containsWhitespace("Captain Awesome") // true
  isNullOrEmpty("Morgan Grimes") // Not found: isNullOrEmpty (error)
  
  //Objects can also contain fields, which are also accessed like static members:
  object MathConstants:
    val PI = 3.14159
    val E = 2.71828

  println(MathConstants.PI) // 3.14159
}
