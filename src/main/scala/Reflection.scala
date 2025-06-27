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
  
  //The full API can be found in the API documentation for scala.quoted.Quotes.reflectModule. 
  // Unfortunately, at this stage, this automatically-generated documentation is not very easy to navigate.
  
  //The most important element on the page is the hierarchy tree which provides a synthetic overview 
  // of the subtyping relationships of the types in the API. For each type Foo in the tree:
  
  // - the trait FooMethods contains the methods available on the type Foo
  // - the trait FooModule contains the static methods available on the object Foo. Most notably, 
  // constructors (apply/copy) and the unapply method which provides the extractor(s) required 
  // for pattern matching are found here
  // - For all types Upper such that Foo <: Upper, the methods defined in UpperMethods are also available on Foo
  
  //For example, TypeBounds, a subtype of TypeRepr, represents a type tree of the form T >: L <: U: a type T which is 
  // a super type of L and a subtype of U. In TypeBoundsMethods, you will find the methods low and hi, 
  // which allow you to access the representations of L and U. In TypeBoundsModule, you will find the unapply method,
  // which allows you to write:

  def f(tpe: TypeRepr) =
    tpe match
      case TypeBounds(l, u) =>
  
  //Because TypeBounds <: TypeRepr, all the methods defined in TypeReprMethods are available on TypeBounds values:
  def f(tpe: TypeRepr) =
    tpe match
      case tpe: TypeBounds =>
        val low = tpe.low
        val hi = tpe.hi
  
  
  //Docs
  //https://docs.scala-lang.org/scala3/reference/metaprogramming/reflection.html
}
