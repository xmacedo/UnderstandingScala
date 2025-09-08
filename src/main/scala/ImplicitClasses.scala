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
 
  //Restrictions
  //Implicit classes have the following restrictions:
  
  //1. They must be defined inside another trait/class/object.
  object HelpersB {
    implicit class RichInt(x: Int) // OK!
  }

  implicit class RichDouble(x: Double) // BAD!
  
  //2. They may only take one non-implicit argument in their constructor.
  implicit class RichDate(date: java.time.LocalDate) // OK!
  implicit class IndexerB[T](collection: Seq[T], index: Int) // BAD!
  implicit class IndexerC[T](collection: Seq[T])(implicit index: Index) // OK!
  
  //While it’s possible to create an implicit class with more than one non-implicit argument, 
  // such classes aren’t used during implicit lookup.
  
  //3. The implicit def introduced by implicit class must not be ambiguous with respect to other term members.
  //Note: This means an implicit class cannot be a case class, since the implicit def would be ambiguous with 
  // the companion apply.

  object Bar

  implicit class Bar(x: Int) // BAD!

  val x = 5

  implicit class x(y: Int) // BAD!

  implicit case class Baz(x: Int) // BAD!
}
