class ScalaCheck {
  //https://docs.scala-lang.org/contribute/partest-guide.html#scalacheck-tests
  
  //Tests that depend on https://github.com/typelevel/scalacheck can be added under folder ./test/files/scalacheck. 
  
  // A sample test:

  import org.scalacheck.*

  object Test {
    val prop_ConcatLists = property { (l1: ListInt, l2: ListInt) =>
      l1.size + l2.size == (l1 ::: l2).size
    }

    val tests = List(("prop_ConcatLists", prop_ConcatLists))
  }

}
