class Reflection {
  //https://docs.scala-lang.org/scala3/guides/macros/reflection.html#inner-main
  //The reflection API provides a more complex and comprehensive view on the structure of the code. 
  // It provides a view of Typed Abstract Syntax Trees and their properties such as types, symbols, 
  // positions and comments.
  
  //The API can be used in macros as well as for inspecting TASTy files. https://docs.scala-lang.org/scala3/reference/metaprogramming/tasty-inspect.html

  //How to use the API
  //The reflection API is defined in the type Quotes as reflect. The actual instance depends on the current scope, 
  // in which quotes or quoted pattern matching is used. Hence, every macro method receives Quotes as an additional argument. 
  // Since Quotes is contextual, to access its members we either need to name the parameter or summon it. 
  // The following definition from the standard library details the canonical way of accessing it:
  
  //package scala.quoted
  //transparent inline def quotes(using inline q: Quotes): q.type = q
  
  //We can use scala.quoted.quotes to import the current Quotes in scope:

  import scala.quoted.* // Import `quotes`, `Quotes`, and `Expr`

  def f(x: Expr[Int])(using Quotes): Expr[Int] =
    import quotes.reflect.* // Import `Tree`, `TypeRepr`, `Symbol`, `Position`, .....
    val tree: Tree = ...

  //This will import all the types and modules (with extension methods) of the API.
  
  //How to navigate the API
  

  
  //Docs
  //https://docs.scala-lang.org/scala3/reference/metaprogramming/reflection.html
}
