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

}
