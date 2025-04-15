package basics.functions

class OwnMapMethod {
  //https://docs.scala-lang.org/scala3/book/fun-write-map-function.html

  //Given that statement, you start to write the method signature. First, 
  // you know that you want to accept a function as a parameter, 
  // and that function should transform an Int into some type A, so you write:
  def map(f: (Int) => A)
  
  //The syntax for using a type parameter requires declaring it in square brackets [] before the parameter list, so you add that:
  def map[A](f: (Int) => A)
  
  //Next, you know that map should also accept a List[Int]:
  def map[A](f: (Int) => A, xs: List[Int])

  //Finally, you also know that map returns a transformed List that contains elements of the type A:
  def map[A](f: (Int) => A, xs: List[Int]): List[A] = ???
  
  //That takes care of the method signature. Now all you have to do is write the method body. 
  // A map method applies the function it’s given to every element in the list it’s given to produce a new, 
  // transformed list. One way to do this is with a for expression:
  for x <- xs yield f(x)
  
  //for expressions often make code surprisingly simple, and for our purposes, that ends up being the entire method body.
  //
  //Putting it together with the method signature, you now have a standalone map method that works with a List[Int]:
  def map[A](f: (Int) => A, xs: List[Int]): List[A] =
    for x <- xs yield f(x)
    
  //Make it generic
  //As a bonus, notice that the for expression doesn’t do anything that depends on the type inside the List being Int. 
  // Therefore, you can replace Int in the type signature with the type parameter B:
  def map[A, B](f: (B) => A, xs: List[B]): List[A] =
    for x <- xs yield f(x)
    
  //Now you have a map method that works with any List.
  //
  //These examples demonstrate that map works as desired:
  def double(i : Int): Int = i * 2
  map(double, List(1, 2, 3))            // List(2, 4, 6)

  def strlen(s: String): Int = s.length
  map(strlen, List("a", "bb", "ccc"))   // List(1, 2, 3)
  
}
