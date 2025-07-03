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
  
  //Relation with Expr/Type
  //Expr and Term
  //Expressions (Expr[T]) can be seen as wrappers around a Term, where T is the statically-known type of the term. 
  // Below, we use the extension method asTerm to transform an expression into a term. This extension method is only 
  // available after importing quotes.reflect.asTerm. Then we use asExprOf[Int] to transform the term back into Expr[Int].
  // This operation will fail if the term does not have the provided type (in this case, Int) or if the term 
  // is not a valid expression. For example, an Ident(fn) is an invalid term if the method fn takes type parameters,
  // in which case we would need an Apply(Ident(fn), args).

  def g[T: Type](using Quotes) =
    import quotes.reflect.*
    val tpe: TypeRepr = TypeRepr.of[T]
    tpe.asType match
      case '[t] => '{ val x: t = ${ . . . } }

  //Symbols
  //The APIs of Term and TypeRepr are relatively closed in the sense that methods produce and accept values whose types
  // are defined in the API. However, you might notice the presence of Symbols which identify definitions.

  //Both Terms and TypeReprs (and therefore Exprs and Types) have an associated symbol. Symbols make it possible to 
  // compare two definitions using == to know if they are the same. In addition, Symbol exposes and is used 
  // by many useful methods. For example:

  // - declaredFields and declaredMethods allow you to iterate on the fields and members defined inside a symbol
  // - flags allows you to check multiple properties of a symbol
  // - companionClass and companionModule provide a way to jump to and from the companion object/class
  // - TypeRepr.baseClasses returns the list of symbols of classes extended by a type
  // - Symbol.pos gives you access to the position where the symbol is defined, the source code of the definition, 
  // and even the filename where the symbol is defined
  // - many others that you can find in SymbolMethods

  //To Symbol and back
  //Consider an instance of the type TypeRepr named val tpe: TypeRepr = .... Then:
  
  //- tpe.typeSymbol returns the symbol of the type represented by TypeRepr. The recommended way to obtain a Symbol 
  // given a Type[T] is TypeRepr.of[T].typeSymbol
  //- For a singleton type, tpe.termSymbol returns the symbol of the underlying object or value
  //- tpe.memberType(symbol) returns the TypeRepr of the provided symbol
  //- On objects t: Tree, t.symbol returns the symbol associated with a tree. Given that Term <: Tree, Expr.asTerm.symbol 
  // is the best way to obtain the symbol associated with an Expr[T]
  //- On objects sym: Symbol, sym.tree returns the Tree associated to the symbol. Be careful when using this method 
  // as the tree for a symbol might not be defined. Read more on the best practices page
      
  //Macro API design
  //It will often be useful to create helper methods or extractors that perform some common logic of your macros.
  //
  //The simplest methods will be those that only mention Expr, Type, and Quotes in their signature. 
  // Internally, they may use reflection, but this will not be seen at the use site of the method.
  def f(x: Expr[Int])(using Quotes): Expr[Int] =
    import quotes.reflect.*
  
  //In some cases, it may be inevitable that some methods will expect or return Trees or other types in quotes.reflect. 
  // For these cases, the best practice is to follow the following method signature examples:
  
  //A method that takes a quotes.reflect.Term parameter
  def f(using Quotes)(term: quotes.reflect.Term): String =
    import quotes.reflect.*

  //An extension method for a quotes.reflect.Term returning a quotes.reflect.Tree
  extension (using Quotes)(term: quotes.reflect.Term)
    def g: quotes.reflect.Tree =...
  
  //An extractor that matches on quotes.reflect.Terms
    object MyExtractor:
    def unapply(using Quotes)(x: quotes.reflect.Term) =
    ...
    Some (y)
    
  //Avoid saving the Quotes context in a field. Quotes in fields inevitably make its use harder by causing errors 
  // involving Quotes with different paths.
  
  //Usually, these patterns have been seen in code that uses the Scala 2 ways to define extension methods or contextual
  // unapplies. Now that we have given parameters that can be added before other parameters, all these old workarounds 
  // are not needed anymore. The new abstractions make it simpler both at the definition site and at the use site.
  
  //Debugging
  //Runtime checks
  //Expressions (Expr[T]) can be seen as wrappers around a Term, where T is the statically-known type of the term. 
  // Hence, these checks will be done at runtime (i.e. compile-time when the macro expands).
  
  //It is recommended to enable the -Xcheck-macros flag while developing macros or on the tests for the macro. 
  // This flag will enable extra runtime checks that will try to find ill-formed trees or types as soon as they are created.
  
  //There is also the -Ycheck:all flag that checks all compiler invariants for tree well-formedness. 
  // These checks will usually fail with an assertion error.
  
  //Docs
  //https://docs.scala-lang.org/scala3/reference/metaprogramming/reflection.html
}
