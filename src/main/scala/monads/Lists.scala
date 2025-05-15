package monads

class Lists {
  //https://www.scala-lang.org/api/3.x/scala/collection/immutable/List.html

  //A class for immutable linked lists representing ordered collections of elements of type A.

  //This class comes with two implementing case classes scala.Nil and scala.::
  // that implement the abstract members isEmpty, head and tail.

  //This class is optimal for last-in-first-out (LIFO), stack-like access patterns.
  // If you need another access pattern, for example, random access or FIFO, consider using a collection
  // more suited to this than List.

  //Performance

  //Time: List has O(1) prepend and head/tail access. Most other operations are O(n) on the number
  // of elements in the list. This includes the index-based lookup of elements, length, append and reverse.

  //Space: List implements structural sharing of the tail list. This means that many operations
  // are either zero- or constant-memory cost.

  val mainList = List(3, 2, 1)
  val with4 = 4 :: mainList // re-uses mainList, costs one :: instance
  val with42 = 42 :: mainList // also re-uses mainList, cost one :: instance
  val shorter = mainList.tail // costs nothing as it uses the same 2::1::Nil instances as mainList
  
  //https://docs.scala-lang.org/overviews/collections-2.13/concrete-immutable-collection-classes.html#lists

  //A List is a finite immutable sequence. 
  // They provide constant-time access to their first element as well as the rest of the list, 
  // and they have a constant-time cons operation for adding a new element to the front of the list. 
  // Many other operations take linear time.
  
}
