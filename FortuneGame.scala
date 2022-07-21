/*This is a number guessing game built in Scala. 
As a user you will have to guess a random number. You will have 5 guesses.
Program will prompt you if you guessed correctly or if your guess is less 
or more than the fortune number..*/

import scala.io.StdIn.readLine
import scala.util.control.Breaks._
import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {

   println("Welcome to the game of Fortune.")
    println("You will be given 5 turns to guess a number.")

    var number = Random.nextInt(101)
    var turns = 5
    var gameStatus = "Lost"

    breakable {
      for (guesses <- 1 to 5)
      {
        var x = readLine("Enter a number: ").toInt
        turns = turns - 1
        
        if (x > number)
        {
          println("Your guess is more than the number X. Try again")
          println("You have "+turns+ " turns left")
        }
        else if (x < number)
        {
          println("Your guess is less than the number X. Try again")
          println("You have "+turns+ " turns left")
        }
        else
        {
          println("You win")
          if (guesses == 1)
          {
            println("It took you "+guesses+" turn")
          }
          else
          {
            println("It took you "+guesses+" turns")
            gameStatus = "Win"
            break
          }
      }
    }
    }
    
    if (gameStatus == "Lost")
    {
      println("You lost a game")
    }
    println("The Fortune number was "+number)
  }
}
