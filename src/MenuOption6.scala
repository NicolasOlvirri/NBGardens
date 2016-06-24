/**
  * Created by Administrator on 17/06/2016.
  */

import java.io.{File, PrintWriter}

import scala.collection._
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn._

object MenuOption6 {
  def removeItemFromStock(stockLevels: mutable.HashMap[String, Int]): Unit = {

    val itemToRemove: ArrayBuffer[String] = new ArrayBuffer[String]
    val itemQuantity: ArrayBuffer[Int] = new ArrayBuffer[Int]
    var input = "Y"
    var update: Boolean = false

    while (input == "y" || input == "Y") {
      println("What is the name of the item you would like to update?")
      //ask user what item they would like to remove
      itemToRemove += readLine()

      println(s"How many items would you like to remove of item: $itemToRemove?")
      //ask user how many items they would like to remove of the item given
      itemQuantity += readInt()

      //double check they want to remove these items
      println(s"Are you sure you would like to remove $itemQuantity of $itemToRemove? (Y/N)")
      input = Initialization.userInput()

      if (input == "Y" || input == "y") {
        //ask if they would like to update any other items
        println("Would you like to update any other items in inventory? (Y/N)")
        input = Initialization.userInput()
        update = true
      }
      else {
        println("No changes will be made. The program will now exit.")
        update = false
        sys.exit
      }
    }

    if(update) {
      //recursive function to find item and decrement its stock
      Product.updateStock(stockLevels, itemToRemove, itemQuantity)
    }
  }
}
