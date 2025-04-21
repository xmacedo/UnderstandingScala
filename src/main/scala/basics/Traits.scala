package basics

class Traits {
  //https://docs.scala-lang.org/scala3/book/domain-modeling-oop.html#traits

  //Traits
  //Perhaps different from other languages with support for OOP, such as Java,
  // the primary tool of decomposition in Scala is not classes, but traits.
  // They can serve to describe abstract interfaces like:
  trait Showable:
    def show: String

  //and can also contain concrete implementations:
  trait Showable:
    def show: String

    def showHtml = "<p>" + show + "</p>"

  //You can see that we define the method showHtml in terms of the abstract method show.

  //Odersky and Zenger present the service-oriented component model and view:

  // - abstract members as required services: they still need to be implemented by a subclass.
  // - concrete members as provided services: they are provided to the subclass.

  //We can already see this with our example of Showable:
  // defining a class Document that extends Showable, we still have to define show,
  // but are provided with showHtml:
  class Document(text: String) extends Showable:
    def show = text
  //bstract Members
  //Abstract methods are not the only thing that can be left abstract in a trait.
  // A trait can contain:

  // - abstract methods (def m(): T)
  // - abstract value definitions (val x: T)
  // - abstract type members (type T), potentially with bounds (type T <: S)
  // - abstract givens (given t: T) Scala 3 only

  //Each of the above features can be used to specify some form of requirement on the implementor
  // of the trait.
  
}
