package basics;

public class Classes {
    //https://docs.scala-lang.org/scala3/book/domain-modeling-oop.html#classes

    //Defining Classes
    //Like traits, classes can extend multiple traits (but only one super class):

    class MyService(name: String) extends ComposedService, Showable:
    def show = s"$name says $sayHello"

    //Subtyping
    //We can create an instance of MyService as follows:
    val s1: MyService = MyService("Service 1")

    //Through the means of subtyping, our instance s1 can be used everywhere that any of
    // the extended traits is expected:
    val s2: GreetingService = s1
    val s3: TranslationService = s1
    val s4: Showable = s1
    // ... and so on ...

    //Planning for Extension
    //As mentioned before, it is possible to extend another class:
    class Person(name: String)
    class SoftwareDeveloper(name: String, favoriteLang: String)
            extends Person(name)

    //However, since traits are designed as the primary means of decomposition,
    // it is not recommended to extend a class that is defined in one file from another file.

    //Open Classes (Scala 3 only)
    //In Scala 3 extending non-abstract classes in other files is restricted.
    // In order to allow this, the base class needs to be marked as open:
    open class Person(name: String)

    //Marking classes with open is a new feature of Scala 3. Having to explicitly mark classes
    // as open avoids many common pitfalls in OO design. In particular, it requires library
    // designers to explicitly plan for extension and for instance document the classes
    // that are marked as open with additional extension contracts.

    //Instances and Private Mutable State
    //Like in other languages with support for OOP, traits and classes in Scala can define mutable fields:

    class Counter:
    // can only be observed by the method `count`
    private var currentCount = 0

    def tick(): Unit = currentCount += 1
    def count: Int = currentCount

    //Every instance of the class Counter has its own private state that can only be observed through the method count,
    // as the following interaction illustrates:
    val c1 = Counter()
    c1.count // 0
    c1.tick()
    c1.tick()
    c1.count // 2

    //Access Modifiers
    //By default, all member definitions in Scala are publicly visible. To hide implementation details,
    // it’s possible to define members (methods, fields, types, etc.) to be private or protected.
    // This way you can control how they are accessed or overridden. Private members are only visible to the class/trait
    // itself and to its companion object. Protected members are also visible to subclasses of the class.

    //Advanced Example: Service Oriented Design
    //Our goal is to define a software component with a family of types that can be refined later
    // in implementations of the component. Concretely, the following code defines the component SubjectObserver as
    // a trait with two abstract type members, S (for subjects) and O (for observers):
    trait SubjectObserver:

        type S <: Subject
        type O <: Observer

        trait Subject:
            self: S =>
                private var observers: List[O] = List()
                    def subscribe(obs: O): Unit =
                        observers = obs :: observers
                    def publish() =
                        for obs <- observers do obs.notify(this)

        trait Observer:
            def notify(sub: S): Unit

    //Abstract Type Members
    //The declaration type S <: Subject says that within the trait SubjectObserver we can refer to some unknown
    // (that is, abstract) type that we call S. However, the type is not completely unknown: we know at least
    // that it is some subtype of the trait Subject. All traits and classes extending SubjectObserver are free
    // to choose any type for S as long as the chosen type is a subtype of Subject.
    // The <: Subject part of the declaration is also referred to as an upper bound on S.

    //Nested Traits
    //Within trait SubjectObserver, we define two other traits. Let us begin with trait Observer,
    // which only defines one abstract method notify that takes an argument of type S. As we will see momentarily,
    // it is important that the argument has type S and not type Subject.

    //The second trait, Subject, defines one private field observers to store all observers
    // that subscribed to this particular subject. Subscribing to a subject simply stores the object into this list.
    // Again, the type of parameter obs is O, not Observer.

    //Self-type Annotations
    //Finally, you might have wondered what the self: S => on trait Subject is supposed to mean.
    // This is called a self-type annotation. It requires subtypes of Subject to also be subtypes of S.
    // This is necessary to be able to call obs.notify with this as an argument, since it requires a value of type S.
    // If S was a concrete type, the self-type annotation could be replaced by trait Subject extends S.

    //Implementing the Component
    //We can now implement the above component and define the abstract type members to be concrete types:
    object SensorReader extends SubjectObserver:
        type S = Sensor
        type O = Display

        class Sensor(val label: String) extends Subject:
            private var currentValue = 0.0
            def value = currentValue
            def changeValue(v: Double) =
                currentValue = v
                publish()

        class Display extends Observer:
            def notify(sub: Sensor) =
                println(s"${sub.label} has value ${sub.value}")

    //Specifically, we define a singleton object SensorReader that extends SubjectObserver.
    // In the implementation of SensorReader, we say that type S is now defined as type Sensor,
    // and type O is defined to be equal to type Display. Both Sensor and Display are defined
    // as nested classes within SensorReader, implementing the traits Subject and Observer, correspondingly.

    //Besides, being an example of a service oriented design, this code also highlights many aspects
    // of object-oriented programming:

    // - The class Sensor introduces its own private state (currentValue) and encapsulates modification
    // of the state behind the method changeValue.
    // - The implementation of changeValue uses the method publish defined in the extended trait.
    // - The class Display extends the trait Observer, and implements the missing method notify.

    //It is important to point out that the implementation of notify can only safely access the label and value of sub,
    // since we originally declared the parameter to be of type S.

    //Using the Component
    //Finally, the following code illustrates how to use our SensorReader component:
    import SensorReader.*

    // setting up a network
    val s1 = Sensor("sensor1")
    val s2 = Sensor("sensor2")
    val d1 = Display()
    val d2 = Display()
    s1.subscribe(d1)
    s1.subscribe(d2)
    s2.subscribe(d1)

    // propagating updates through the network
    s1.changeValue(2)
    s2.changeValue(3)

    // prints:
    // sensor1 has value 2.0
    // sensor1 has value 2.0
    // sensor2 has value 3.0

}
