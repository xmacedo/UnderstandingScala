### Using sbt with ScalaTest
[Link reference](https://docs.scala-lang.org/scala3/book/tools-sbt.html#using-sbt-with-scalatest)
- [ScalaTest](https://www.scalatest.org/) is one of the main testing libraries for Scala projects. In this section you’ll see the steps necessary to create a Scala/sbt project that uses ScalaTest.

1) Create the project directory structure
As with the previous lesson, create an sbt project directory structure for a project named HelloScalaTest with the following commands:

`$ mkdir HelloScalaTest
$ cd HelloScalaTest
$ mkdir -p src/{main,test}/scala
$ mkdir project`

2) Create the build.properties and build.sbt files
- Next, create a build.properties file in the project/ subdirectory of your project with this line:
```
  sbt.version=1.10.11
```
- Next, create a build.sbt file in the root directory of your project with these contents:

```
name := "HelloScalaTest"
version := "0.1"
scalaVersion := "3.7.1"

libraryDependencies ++= Seq(
"org.scalatest" %% "scalatest" % "3.2.19" % Test
)
```
The first three lines of this file are essentially the same as the first example. The _**libraryDependencies**_ lines tell sbt to include the dependencies (JAR files) that are needed to include ScalaTest.

**_The ScalaTest documentation has always been good, and you can always find the up to date information on what those lines should look like on the Installing ScalaTest page._**

3) Create a Scala source code file
- Next, create a Scala program that you can use to demonstrate ScalaTest. First, create a directory under src/main/scala named math:

```
$ mkdir src/main/scala/math
            ---
```
- Then, inside that directory, create a file named [MathUtils.scala](math/MathUtils.scala) with these contents:

```
package math

object MathUtils:
  def double(i: Int) = i * 2
```

That method provides a simple way to demonstrate ScalaTest.

4. Create your first ScalaTest tests
- ScalaTest is very flexible, and offers several different ways to write tests. A simple way to get started is to write tests using the ScalaTest AnyFunSuite. To get started, create a directory named math under the src/test/scala directory:

```scala
$ mkdir src/test/scala/math
            ----
```

- Next, create a file named [MathUtilsTests.scala](../../test/scala/math/MathUtilsTests.scala) in that directory with the following contents:

This code demonstrates the ScalaTest **_AnyFunSuite_** approach. A few important points:
- Your test class should extend **_AnyFunSuite_**
- You create tests as shown, by giving each _**test**_ a unique name
- At the end of each test you should call **_assert_** to test that a condition has been satisfied
- When you know you want to write a test, but you don’t want to write it right now, create the test as “pending,” with the syntax shown

Using ScalaTest like this is similar to JUnit, so if you’re coming to Scala from Java, hopefully this looks similar.

Now you can run these tests with the `sbt test` command. Skipping the first few lines of output, the result looks like this:

```shell
sbt:HelloScalaTest> test

[info] Compiling 1 Scala source ...
[info] MathUtilsTests:
[info] - 'double' should handle 0
[info] - 'double' should handle 1
[info] - test with Int.MaxValue (pending)
[info] Total number of tests run: 2
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 2, failed 0, canceled 0, ignored 0, pending 1
[info] All tests passed.
[success] Total time: 1 s
```

If everything works well, you’ll see output that looks like that. Welcome to the world of testing Scala applications with sbt and ScalaTest.

### Support for many types of tests
This example demonstrates a style of testing that’s similar to xUnit Test-Driven Development (TDD) style testing, with a few benefits of the Behavior-Driven Development (BDD) style.

As mentioned, _**ScalaTest**_ is flexible and you can also write tests using other styles, such as a style similar to Ruby’s RSpec. You can also use mock objects, property-based testing, and use ScalaTest to test Scala.js code.
