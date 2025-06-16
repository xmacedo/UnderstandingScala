class TypeClasses {
  //https://docs.scala-lang.org/scala3/book/ca-type-classes.html
  //Type Classes
  //A type class is an abstract, parameterized type that lets you add new behavior
  // to any closed data type without using sub-typing. If you are coming from Java,
  // you can think of type classes as something like java.util.Comparator[T].
  
  //A type class is useful in multiple use-cases, for example:
  //- Expressing how a type you don’t own—from the standard library or a third-party library—conforms to such behavior
  //- Expressing such a behavior for multiple types without involving sub-typing relationships between those types
  
  //Type classes are traits with one or more parameters whose implementations are provided as given instances 
  // in Scala 3 or implicit values in Scala 2.
  
  //Example
  //For example, Show is a well-known type class in Haskell, and the following code shows one way to implement 
  // it in Scala. If you imagine that Scala classes don’t have a toString method, you can define a 
  // Show type class to add this behavior to any type that you want to be able to convert to a custom string.
  
  //The type class
  //The first step in creating a type class is to declare a parameterized trait that has one or more abstract methods. 
  // Because Showable only has one method named show, it’s written like this:

  // a type class
  trait Showable[A]:
    extension (a: A) def show: String
  
}
