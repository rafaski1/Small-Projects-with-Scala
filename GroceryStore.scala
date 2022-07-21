/*This is a grocery store app built in Scala. 
User will be able to input product information (item,price, quantity) 
which will be stored in a ListBuffer within a class. The total price for a shopping cart will be calculated.
Exploring classes, objects, data structures and functions in Scala.
*/

import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer

class Data(var item:String,var price:Int,var count:Int) {
  def printData (): Unit = {
    println(item)
    println(price)
    println(count)
  }

  def total() : Int = {
    return price * count
  }
}

object Main {

  def main(args: Array[String]): Unit = {

    var listBuffer = ListBuffer[Data]()
    var input = " "
    var totalBill = 0

    println("Welcome to grocery store.")
    
    do {
      println("1. to enter new product.")
      println("2. to quit the program")
      input = readLine("Enter 1 or 2: ")
      
      if (input == "1") {
        var item = readLine("Enter item: ")
        var price = readLine("Enter price: ").toInt
        var count = readLine("Enter count: ").toInt

        var d = new Data(item,price,count)
        listBuffer += d
      }
    }
    while (input != "2")

    for (w<- listBuffer) {
      w.printData()
      totalBill += w.total()
    }
    println("Your total bill is: "+totalBill)
  }
}
