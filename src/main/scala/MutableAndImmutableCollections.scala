class MutableAndImmutableCollections {
  //https://docs.scala-lang.org/overviews/collections-2.13/overview.html
  //Mutable and Immutable Collections
  
  //Scala collections systematically distinguish between mutable and immutable collections. 
  // A mutable collection can be updated, reduced or extended in place. This means you can change, add, 
  // or remove elements of a collection as a side effect. Immutable collections, by contrast, never change. 
  // You still have operations that simulate additions, removals, or updates, but those operations will in 
  // each case return a new collection and leave the old collection unchanged.
  
  //All collection classes are found in the package scala.collection or one of its sub-packages mutable and immutable. 
  // Most collection classes needed by client code exist in three variants, which are located in packages 
  // scala.collection, scala.collection.immutable, and scala.collection.mutable, respectively.
  // Each variant has different characteristics with respect to mutability.

  //A collection in package scala.collection.immutable is guaranteed to be immutable for everyone.
  // Such a collection will never change after it is created. Therefore, you can rely on the fact that accessing the
  // same collection value repeatedly at different points in time will always yield a collection with the same elements.

  //A collection in package scala.collection.mutable is known to have some operations that change the collection in place.
  // So dealing with a mutable collection means you need to understand which code changes which collection when.
  
  //A collection in package scala.collection can be either mutable or immutable. 
  // For instance, collection.IndexedSeq[T] is a superclass of both collection.immutable.IndexedSeq[T] 
  // and collection.mutable.IndexedSeq[T]. 
  // Generally, the root collections in package scala.collection support transformation operations affecting 
  // the whole collection, the immutable collections in package scala.collection.immutable typically 
  // add operations for adding or removing single values, and the mutable collections in package 
  // scala.collection.mutable typically add some side-effecting modification operations to the root interface.
  
  //Another difference between root collections and immutable collections is that clients of an immutable collection 
  // have a guarantee that nobody can mutate the collection, whereas clients of a root collection only 
  // promise not to change the collection themselves. 
  // Even though the static type of such a collection provides no operations for modifying the collection, 
  // it might still be possible that the run-time type is a mutable collection which can be changed by other clients.


}
