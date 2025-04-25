package basics

class SealedTypes {
  //https://docs.scala-lang.org/tour/pattern-matching.html#sealed-types

  //You may have noticed that in the examples above the base types are qualified with the keyword sealed.
  // This provides extra safety because the compiler checks that the cases of a match expression
  // are exhaustive when the base type is sealed.

  //For instance, in the method showNotification defined above, if we forget one case, say,
  // VoiceRecording, the compiler emits a warning:
  def showNotification(notification: Notification): String =
    notification match
      case Email(sender, title, _) =>
        s"You got an email from $sender with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"

  //This definition produces the following warning:
  //match may not be exhaustive.
  //It would fail on pattern case: VoiceRecording(_, _)

  //The compiler even provides examples of input that would fail!
  //
  //On the flip side, exhaustivity checking requires you to define all the subtypes
  // of the base type in the same file as the base type (otherwise, the compiler would not know
  // what are all the possible cases). For instance, if you try to define a new type of
  // Notification outside of the file that defines the sealed trait Notification,
  // it will produce a compilation error:

  case class Telepathy(message: String) extends Notification
             ^ Cannot extend sealed trait Notification in a different source file



}
