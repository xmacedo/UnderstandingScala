package advanced.monads

class state {
  //In functional programming, functions are pure — they don’t mutate state in place.
  // But sometimes you need to carry some state through a sequence of computations 
  // (like a random number generator, counters, parser contexts, etc.).
  
  //Instead of manually threading that state through every function parameter and return value, 
  // the State monad abstracts this pattern.
  
  //It lets you write code that looks imperative (updating state step by step) but is actually pure and referentially 
  // transparent.
  
  //How it works conceptually
  //Mathematically, State[S, A] represents a function:
  S => (S, A)

  //That is:
  //- It takes some state S,
  //- Returns a tuple (newState, resultOfTypeA).
  
  //You can then chain these computations with flatMap, automatically passing the updated state along.
  
  //State Monad in Cats
  //Cats provides cats.data.State. You can import and use it like this:

  import cats.data.State

  // State[S, A]
  val increment: State[Int, String] = State { count =>
    val newCount = count + 1
    (newCount, s"Counted to $newCount")
  }

  //This represents a computation that, given a starting Int, returns a new Int (the incremented state) 
  // and a message (String).
  
  //You can run it:
  val (state, result) = increment.run(10).value
  // state: 11
  // result: "Counted to 11"
  
  //Composing State with flatMap / for-comprehension


}
