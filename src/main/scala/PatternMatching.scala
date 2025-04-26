class PatternMatching {
  //https://docs.scala-lang.org/tour/pattern-matching.html
  //Pattern matching is a mechanism for checking a value against a pattern.
  // A successful match can also deconstruct a value into its constituent parts.
  // It is a more powerful version of the switch statement in Java and it can likewise
  // be used in place of a series of if/else statements.

  //A match expression has a value, the match keyword, and at least one case clause.

  import scala.util.Random

  val x: Int = Random.nextInt(10)

  x match
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"

  //The val x above is a random integer between 0 and 9.
  // x becomes the left operand of the match operator and on the right is an expression with four cases.
  // The last case _ is a “catch all” case for any other possible Int values. Cases are also
  // called alternatives.

  //Match expressions have a value.
  def matchTest(x: Int): String = x match
    case 1 => "one"
    case 2 => "two"
    case _ => "other"

  matchTest(3) // returns other
  matchTest(1) // returns one

  //This match expression has a type String because all of the cases return String.
  // Therefore, the function matchTest returns a String.

  //Matching on case classes
  //Case classes are especially useful for pattern matching.
  sealed trait Notification

  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification

  //Notification is a sealed trait which has three concrete Notification types implemented
  // with case classes Email, SMS, and VoiceRecording.
  // (A sealed trait can be extended only in the same file as its declaration.)
  // Now we can do pattern matching on these case classes:
  def showNotification(notification: Notification): String =
    notification match
      case Email(sender, title, _) =>
        s"You got an email from $sender with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"You received a Voice Recording from $name! Click the link to hear it: $link"

  val someSms = SMS("12345", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")

  println(showNotification(someSms)) 
  // prints You got an SMS from 12345! Message: Are you there?
  
  println(showNotification(someVoiceRecording)) 
  // prints You received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
  
  //The function showNotification takes as a parameter the abstract type Notification and matches 
  // on the type of Notification (i.e. it figures out whether it’s an Email, SMS, or VoiceRecording). 
  // In the case Email(sender, title, _) the fields sender and title are used in the 
  // return value but the body field is ignored with _.
}
