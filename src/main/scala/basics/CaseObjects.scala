package basics

class CaseObjects {
  //https://docs.scala-lang.org/scala3/book/domain-modeling-tools.html#case-objects
  //Case objects are to objects what case classes are to classes: 
  // they provide a number of automatically-generated methods to make them more powerful. 
  // They’re particularly useful whenever you need a singleton object that needs a little extra functionality, 
  // such as being used with pattern matching in match expressions.
  
  //Case objects are useful when you need to pass immutable messages around. 
  // For instance, if you’re working on a music player project, you’ll create a set of commands or messages like this:
  sealed trait Message
  case class PlaySong(name: String) extends Message
  case class IncreaseVolume(amount: Int) extends Message
  case class DecreaseVolume(amount: Int) extends Message
  case object StopPlaying extends Message

  //Then in other parts of your code, you can write methods like this, which use pattern matching 
  // to handle the incoming message (assuming the methods playSong, changeVolume, and stopPlayingSong are 
  // defined somewhere else):
  def handleMessages(message: Message): Unit = message match
    case PlaySong(name) => playSong(name)
    case IncreaseVolume(amount) => changeVolume(amount)
    case DecreaseVolume(amount) => changeVolume(-amount)
    case StopPlaying => stopPlayingSong()
}
