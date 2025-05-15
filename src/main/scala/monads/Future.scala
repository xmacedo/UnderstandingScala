package monads

class Future {
  //https://docs.scala-lang.org/overviews/core/futures.html#futures
  //A Future is an object holding a value which may become available at some point. 
  // This value is usually the result of some other computation:
  
  // 1. If the computation has not yet completed, we say that the Future is not completed.
  // 2. If the computation has completed with a value or with an exception, we say that the Future is completed.
  
  //Completion can take one of two forms:
  
  // 1. When a Future is completed with a value, we say that the future was successfully completed with that value.
  // 2. When a Future is completed with an exception thrown by the computation, we say that the Future 
  // was failed with that exception.
  
  //A Future has an important property that it may only be assigned once. 
  // Once a Future object is given a value or an exception, it becomes in effect immutable – it can never be overwritten.
  
  //The simplest way to create a future object is to invoke the Future.apply method which starts an asynchronous 
  // computation and returns a future holding the result of that computation. 
  // The result becomes available once the future completes.
  
  //Note that Future[T] is a type which denotes future objects, whereas Future.apply is a method which
  // creates and schedules an asynchronous computation, and then returns a future object which will be completed
  // with the result of that computation.
  
  //This is best shown through an example.
  
  //Let’s assume that we want to use a hypothetical API of some popular social network to obtain a list of friends
  // for a given user. We will open a new session and then send a request to obtain a list of friends
  // of a particular user:

  import scala.concurrent.*
  import ExecutionContext.Implicits.global

  val session = socialNetwork.createSessionFor("user", credentials)
  val f: Future[List[Friend]] = Future {
    session.getFriends()
  }

}
