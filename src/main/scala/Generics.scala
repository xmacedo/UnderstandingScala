class Generics {
  //https://docs.scala-lang.org/scala3/book/types-generics.html#inner-main

  //Generic classes (or traits) take a type as a parameter within square brackets [...].
  // The Scala convention is to use a single letter (like A) to name those type parameters.
  // The type can then be used inside the class as needed for method instance parameters, or on return types:

  // here we declare the type parameter A
  //          v
  class Stack[A]:
    private var elements: List[A] = Nil

    //                         ^
    //  Here we refer to the type parameter
    //          v
    def push(x: A): Unit =
      elements = elements.prepended(x)

    def peek: A = elements.head

    def pop(): A =
      val currentTop = peek
      elements = elements.tail
      currentTop

  //This implementation of a Stack class takes any type as a parameter.
  // The beauty of generics is that you can now create a Stack[Int], Stack[String], and so on,
  // allowing you to reuse your implementation of a Stack for arbitrary element types.

  //This is how you create and use a Stack[Int]:
  val stack = Stack[Int]
  stack.push(1)
  stack.push(2)
  println(stack.pop()) // prints 2
  println(stack.pop()) // prints 1
}
