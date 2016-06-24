import java.io.{File, FileWriter, PrintWriter}

import scala.collection.mutable._
import scala.collection._

/**
  * Created by Administrator on 16/06/2016.
  */

object Product {

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

      //check if the stock levels in IMS are less than the amount of items the customer requires
      if (stockLevels(itemName(x)) < itemQuantity(x)) {
        println(s"There is not enough stock for the item: ${itemName(x)}")
        println(s"There is currently only ${stockLevels(itemName(x))} in stock.")

        if (requirePorous(x) == "T") {
          println(s"The customer needs ${itemQuantity(x)} of product ${itemName(x)} with porousware applied.")
        }
        else {
          println(s"The customer needs ${itemQuantity(x)} of product ${itemName(x)} without porousware applied.")
        }
        //sets noStock to true since there is not enough stock
        noStock = true
      }
      x += 1
    }

    x = 0
    //if there is enough stock
    if (!noStock) {
      //for each product the customer ordered
      for (productName <- itemName) {
        //find the name of the product in the hashmap stockLevels
        if (stockLevels.contains(productName)) {
          //decrement that value by the amount that the customer would like to purchase
          stockLevels(productName) = stockLevels(productName) - itemQuantity(x)
        }
        x += 1
      }

      //file to rewrite updated stock after a purchase
      val file = new PrintWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\stock.csv"))

      for (item <- stockLevels.keySet) {
        file.write(item + ": " + stockLevels(item) + "\n")
      }
      file.close()
    }
    else println("We were unable to decrement stock values due to insufficient stock.")
    noStock = false

  }

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
}