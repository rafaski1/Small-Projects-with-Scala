/*Word count app program built in Scala.
User will enter words, program will count the presence of a word within a Map and return count.
Exploring maps in Scala.
*/

import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object Main {

  def main(args: Array[String]): Unit = {

    println("Welcome to Word Count.")
    
    var map = Map[String,Int]()
    var totalWords = readLine("How many words would you like to enter? ").toInt

    for (w <- 1 to totalWords) {
      
      var word = readLine("Enter word: ")
      
      if (map.contains(word)) {
        map(word) = map(word) + 1
      }
      else {
        map += (word -> 1)
      }
    }

    var key = " "
    
    do {
      key = readLine("Enter word to count: ")
      if (map.contains(key)) {
        println(map(key))
      }
      else {
        println("Word is not present")
      }
    }
    while (key !="QUIT")
  }
}
