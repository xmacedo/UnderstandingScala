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
  
  //Above, we first import the contents of the scala.concurrent package to make the type Future visible. 
  // We will explain the second import shortly.

  //We then initialize a session variable which we will use to send requests to the server, 
  // using a hypothetical createSessionFor method. To obtain the list of friends of a user, 
  // a request has to be sent over a network, which can take a long time. 
  // This is illustrated with the call to the method getFriends that returns List[Friend]. 
  // To better utilize the CPU until the response arrives, we should not block the rest of the program – 
  // this computation should be scheduled asynchronously. 
  // The Future.apply method does exactly that – it performs the specified computation block concurrently, 
  // in this case sending a request to the server and waiting for a response.
  
  //The list of friends becomes available in the future f once the server responds.
  
  //An unsuccessful attempt may result in an exception. In the following example, 
  // the session value is incorrectly initialized, so the computation in the Future 
  // block will throw a NullPointerException. 
  // This future f is then failed with this exception instead of being completed successfully:  

  val session = null
  val f: Future[List[Friend]] = Future {
    session.getFriends()
  }
  
  //The line import ExecutionContext.Implicits.global above imports the default global execution context. 
  // Execution contexts execute tasks submitted to them, and you can think of execution contexts as thread pools. 
  // They are essential for the Future.apply method because they handle how and when the asynchronous 
  // computation is executed. 
  // You can define your own execution contexts and use them with Future, 
  // but for now it is sufficient to know that you can import the default execution context as shown above.
  //
  //Our example was based on a hypothetical social network API where the computation consists of sending a 
  // network request and waiting for a response. 
  // It is fair to offer an example involving an asynchronous computation which you can try out of the box. 
  // Assume you have a text file, and you want to find the position of the first occurrence of a particular keyword.
  // This computation may involve blocking while the file contents are being retrieved from the disk, 
  // so it makes sense to perform it concurrently with the rest of the computation.

  val firstOccurrence: Future[Int] = Future {
    val source = scala.io.Source.fromFile("myText.txt")
    source.toSeq.indexOfSlice("myKeyword")
  }
}
