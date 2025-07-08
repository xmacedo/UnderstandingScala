package basics

class SealedTraits {
  //https://docs.scala-lang.org/tour/pattern-matching.html#matching-on-case-classes
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

  val someSms1 = SMS("12345", "Are you there?")
  val someVoiceRecording1 = VoiceRecording("Tom", "voicerecording.org/id/123")

  println(showNotification(someSms1))
  // prints You got an SMS from 12345! Message: Are you there?

  println(showNotification(someVoiceRecording1))
  // prints You received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123

  //The function showNotification takes as a parameter the abstract type Notification and matches
  // on the type of Notification (i.e. it figures out whether it’s an Email, SMS, or VoiceRecording).
  // In the case Email(sender, title, _) the fields sender and title are used in the
  // return value but the body field is ignored with _.

}
