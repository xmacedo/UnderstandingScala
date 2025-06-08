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
- Then, inside that directory, create a file named [MathUtils.scala](ScalaTest.scala) with these contents:

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