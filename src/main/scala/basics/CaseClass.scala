package basics

class CaseClass {
  //https://docs.scala-lang.org/tour/case-classes.html#inner-main
  //https://docs.scala-lang.org/scala3/book/domain-modeling-tools.html#case-classes
  
  //Case classes are used to model immutable data structures. Take the following example:
  case class Person(name: String, relation: String)
  
  //Since we declare Person as a case class, the fields name and relation are public and immutable by default. 
  // We can create instances of case classes as follows:
  val christina = Person("Christina", "niece")
  
  //Note that the fields can’t be mutated:
  christina.name = "Fred"   // error: reassignment to val
  
  //Since the fields of a case class are assumed to be immutable, the Scala compiler 
  // can generate many helpful methods for you:
  
  // - An unapply method is generated, which allows you to perform pattern matching on a case class 
  // (that is, case Person(n, r) => ...).
  // - A copy method is generated in the class, which is very useful to create modified copies of an instance.
  //- equals and hashCode methods using structural equality are generated, allowing you to use instances 
  // of case classes in Maps.
  // - A default toString method is generated, which is helpful for debugging.
  
  //These additional features are demonstrated in the below example:
  // Case classes can be used as patterns
  christina match
    case Person(n, r) => println("name is " + n)

  // `equals` and `hashCode` methods generated for you
  val hannah = Person("Hannah", "niece")
  christina == hannah // false

  // `toString` method
  println(christina) // Person(Christina,niece)

  // built-in `copy` method
  case class BaseballTeam(name: String, lastWorldSeriesWin: Int)

  val cubs1908 = BaseballTeam("Chicago Cubs", 1908)
  val cubs2016 = cubs1908.copy(lastWorldSeriesWin = 2016)
  // result:
  // cubs2016: BaseballTeam = BaseballTeam(Chicago Cubs,2016)
  
  //Support for functional programming
  //As mentioned, case classes support functional programming (FP):
  //
  // - In FP, you try to avoid mutating data structures. It thus makes sense that constructor fields default to val. 
  // Since instances of case classes can’t be changed, they can easily be shared without fearing mutation 
  // or race conditions.
  // - Instead of mutating an instance, you can use the copy method as a template to create 
  // a new (potentially changed) instance. This process can be referred to as “update as you copy.”
  // - Having an unapply method auto-generated for you also lets case classes be used in advanced 
  // ways with pattern matching.
}
