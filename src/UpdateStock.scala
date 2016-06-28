/**
  * Created by Administrator on 17/06/2016.
  */

import java.io.{File, PrintWriter}

import scala.collection._
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn._

object UpdateStock {
  //specify which item and quantity of that item to decrement in stock
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
      updateStock(stockLevels, itemToRemove, itemQuantity)
    }
  }

  //remove items given from stock
  def updateStock(stockLevels: mutable.HashMap[String, Int], itemToRemove: ArrayBuffer[String], itemQuantity: ArrayBuffer[Int]): Unit = {

    val file = new PrintWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\stock.csv"))

    def findItemAndRemove(stock: mutable.HashMap[String, Int], itemToRemove: ArrayBuffer[String], itemQuantity: ArrayBuffer[Int]): Unit = {

      //make sure the stock map is not empty
      if (itemToRemove.nonEmpty) {

        //look for the key the the user would like to update
        if (stock.contains(itemToRemove.head)) {

          //if the user wants to remove more than there is in stock, print a message
          if (stock(itemToRemove.head) < itemQuantity.head) {
            println(s"There is not enough of ${itemToRemove.head} in stock to decrement its value.")
            findItemAndRemove(stock, itemToRemove.tail, itemQuantity.tail)
          }

          //otherwise decrement the stock
          else {
            //decrement that value by the amount that the customer would like to purchase
            stock(itemToRemove.head) = stock(itemToRemove.head) - itemQuantity.head
            findItemAndRemove(stock, itemToRemove.tail, itemQuantity.tail)
          }
        }

        else {
          findItemAndRemove(stock, itemToRemove.tail, itemQuantity.tail)
        }
      }

      for ((element, quantity) <- stock) {
        file.write(element + ": " + stock(element) + "\n")
      }
      file.close()

    }
    findItemAndRemove(stockLevels, itemToRemove, itemQuantity)
  }

  //decrements stock
  def decrementStock(userFound: Array[String], stockLevels: mutable.HashMap[String, Int]): Unit = {

    //holds name of customer requested items
    val itemName: ArrayBuffer[String] = new ArrayBuffer[String]
    //holds amount of items of that name
    val itemQuantity: ArrayBuffer[Int] = new ArrayBuffer[Int]
    //holds information on whether that item requires porous
    val requirePorous: ArrayBuffer[String] = new ArrayBuffer[String]
    //two temporary arrays for splitting
    var temp: Array[String] = new Array[String](25)
    var temp1: Array[String] = new Array[String](3)
    //boolean to check if there is enough stock
    var noStock: Boolean = false
    var x = 0

    //temp holds string that was split
    //each position in temp holds Product Name: Product Quantity: Requires Porous
    temp = userFound(6).split('|').map(_.trim)

    for (element <- temp) {
      temp1 = element.split(':').map(_.trim)
      itemName.append(temp1(0))
      itemQuantity.append(temp1(1).toInt)
      requirePorous.append(temp1(2))
    }

    updateStock(stockLevels, itemName, itemQuantity)
//      //check if the stock levels in IMS are less than the amount of items the customer requires
//      if (stockLevels(itemName(x)) < itemQuantity(x)) {
//        println(s"There is not enough stock for the item: ${itemName(x)}")
//        println(s"There is currently only ${stockLevels(itemName(x))} in stock.")
//
//        if (requirePorous(x) == "T") {
//          println(s"The customer needs ${itemQuantity(x)} of product ${itemName(x)} with porousware applied.")
//        }
//        else {
//          println(s"The customer needs ${itemQuantity(x)} of product ${itemName(x)} without porousware applied.")
//        }
//        //sets noStock to true since there is not enough stock
//        noStock = true
//      }
//      x += 1
//    }
//
//    x = 0
//    //if there is enough stock
//    if (!noStock) {
//      //for each product the customer ordered
//      for (productName <- itemName) {
//        //find the name of the product in the hashmap stockLevels
//        if (stockLevels.contains(productName)) {
//          //decrement that value by the amount that the customer would like to purchase
//          stockLevels(productName) = stockLevels(productName) - itemQuantity(x)
//        }
//        x += 1
//      }
//
//      //file to rewrite updated stock after a purchase
//      val file = new PrintWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\stock.csv"))
//
//      for (item <- stockLevels.keySet) {
//        file.write(item + ": " + stockLevels(item) + "\n")
//      }
//      file.close()
//    }
//    else println("We were unable to decrement stock values due to insufficient stock.")
//    noStock = false

  }
}


