package advanced.monads

class IO {
  //The IO monad is a data type used in functional programming (and provided by Cats Effect in Scala) to represent and
  // control side effects in a pure, referentially transparent way.
  
  //Instead of executing effects immediately, IO describes computations that may interact with the outside world 
  // (e.g., console, files, network). These computations are only executed when explicitly run by the runtime.

  //Key Characteristics
  //Type
  IO[A]
  
  //Represents a computation that, when run, will produce a value of type A (or fail with an error).
  
  //Purity:
  //Side effects are not executed eagerly. They are suspended inside IO.
  
  //Composability:
  //Supports monadic operations (map, flatMap) so multiple effects can be sequenced in a declarative style.
  
  //Laziness:
  //IO values do nothing until executed by the runtime (e.g., via unsafeRunSync or IOApp).
  
  //Safety:
  //Provides controlled error handling and resource management.
  
  //Constructors
  
}
