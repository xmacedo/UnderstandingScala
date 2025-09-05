class ImplicitClasses {
  //https://docs.scala-lang.org/overviews/core/implicit-classes.html#inner-main
  // This doc page is specific to features shipped in Scala 2, which have either been removed in Scala 3 or replaced 
  // by an alternative. Unless otherwise stated, all the code examples in this page assume you are using Scala 2.

  //In Scala 3, implicit classes are still supported for compatibility reasons but the recommended way to achieve 
  // the same result is to use extension methods (https://docs.scala-lang.org/scala3/book/ca-extension-methods.html).
  
  //Introduction
  //Scala 2.10 introduced a new feature called implicit classes. An implicit class is a class marked with the implicit 
  // keyword. This keyword makes the class’s primary constructor available for implicit conversions when the 
  // class is in scope.
  
  //Implicit classes were proposed in SIP-13. (https://docs.scala-lang.org/sips/implicit-classes.html)
  
  //Usage
  //To create an implicit class, simply place the implicit keyword in front of an appropriate class. Here’s an example:
  object Helpers {
    implicit class IntWithTimes(x: Int) {
      def times[A](f: => A): Unit = {
        def loop(current: Int): Unit =
          if (current > 0) {
            f
            loop(current - 1)
          }

        loop(x)
      }
    }
  }

  //This example creates the implicit class IntWithTimes. This class wraps an Int value and provides a new method, times. 
  // To use this class, just import it into scope and call the times method. Here’s an example:

  import Helpers._
  import Helpers._

  5 times println("HI")
  
  //For an implicit class to work, its name must be in scope and unambiguous, like any other implicit value or conversion.
  
}
