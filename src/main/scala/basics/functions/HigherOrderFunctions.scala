package basics.functions

class HigherOrderFunctions {
  //https://docs.scala-lang.org/scala3/book/fun-hofs.html
  //Understanding filter’s Scaladoc
  //To understand how higher-order functions work, it helps to dig into an example.
  // For instance, you can understand the type of functions filter accepts by looking at its Scaladoc.
  // Here’s the filter definition in the List[A] class:
  def filter(p: A => Boolean): List[A]

  //This states that filter is a method that takes a function parameter named p.
  // By convention, p stands for a predicate, which is just a function that returns a Boolean value.
  // So filter takes a predicate p as an input parameter, and returns a List[A],
  // where A is the type held in the list; if you call filter on a List[Int], A is the type Int.
  //
  //At this point, if you don’t know the purpose of the filter method, all you’d know is that
  // its algorithm somehow uses the predicate p to create and return the List[A].
  //
  //Looking specifically at the function parameter p, this part of filter’s description:
  p: A => Boolean

  //means that whatever function you pass in must take the type A as an input parameter and return
  // a Boolean. So if your list is a List[Int], you can replace the type parameter A with Int,
  // and read that signature like this:
  p: Int => Boolean

  //Because isEven has this type—it transforms an input Int into a resulting
  // Boolean—it can be used with filter.

  //Writing methods that take function parameters

  //A first example
  //To create a method that takes a function parameter, all you have to do is:

  //1. In your method’s parameter list, define the signature of the function you want to accept
  //2. Use that function inside your method

  //To demonstrate this, here’s a method that takes an input parameter named f, where f is a function:
  def sayHello(f: () => Unit): Unit = f()

  //This portion of the code—the type signature—states that f is a function, and defines the
  // types of functions the sayHello method will accept:
  f: () => Unit

  //Here’s how this works:
  //
  // - f is the name of the function input parameter. It’s just like naming a String parameter s or an
  // Int parameter i.
  // - The type signature of f specifies the type of the functions this method will accept.
  // - The () portion of f’s signature (on the left side of the => symbol) states that f takes
  // no input parameters.
  // - The Unit portion of the signature (on the right side of the => symbol) indicates that f should
  // not return a meaningful result.
  // - Looking back at the body of the sayHello method (on the right side of the = symbol),
  // the f() statement there invokes the function that’s passed in.

  //Now that we’ve defined sayHello, let’s create a function to match f’s signature so we can test it.
  // The following function takes no input parameters and returns nothing, so it matches f’s
  // type signature:

  def helloJoe(): Unit = println("Hello, Joe")


}
