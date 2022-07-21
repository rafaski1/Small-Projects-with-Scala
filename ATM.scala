/*This is a simple ATM app. 
Built in Scala. 
As a user you will have to input valid card and pin number to access internal ATM menu 
and make operations on your account*/

import scala.io.StdIn.readLine
import scala.util.control.Breaks._

object Main {

  var balance = 1000

  def main(args: Array[String]): Unit = 
  {
    var cardNumber = 12345
    var pin = 1234

    var status = takeUserCredentials(cardNumber,pin)

    if (status == "Passed")
    {
      makeTransactions()
    }
  }

  def makeTransactions(): Unit ={
      var input = " "

      do{
        showMenu()
        input = readLine("Enter your option.")
        operations(input)
      }
      while(input != "4")
  }


  def operations(input: String): Unit = {
        if(input == "1")
        {
          checkBalance()
        }
        else if(input == "2")
        {
          var bal = readLine("Enter amount to withdraw: ").toInt
          withdraw(bal)
        }
        else if(input == "3")
        {
          var bal = readLine("Enter amount to deposit: ").toInt
          deposit(bal)        
        }
  }


  def showMenu(): Unit = {

        println("1. To check balance")
        println("2. To withdraw")
        println("3. To deposit")
        println("4. To quit")
  }

  def takeUserCredentials(cardNumber: Int, pin: Int) : String = {

    var status = "Failed"

    breakable
    {

      for(w<- 1 to 3)
      {
        var userCardNumber = readLine("Enter card number: ").toInt

        var userPin = readLine("Enter pin: ").toInt

        if(userCardNumber == cardNumber && userPin == pin)
        {
          status = "Passed"
          println("Valid infromation, Welcome to ATM")
          break

        }
        else
        {
          println("Invalid information, Try again.")
        }
      } 
    }

    return status

  }


  def deposit(amount: Int): Unit={
    balance = balance + amount
    checkBalance()
  }

  def withdraw(amount: Int): Unit ={
      if(amount > balance)
      {
        println("Your balance is not enough.")
      }
      else
      {
        println("Your amount has been deducted.")
        balance = balance - amount
        checkBalance()
      }
  }

  def checkBalance(): Unit = {

    println("Your balance is: ")
    println(balance)

  }
}
