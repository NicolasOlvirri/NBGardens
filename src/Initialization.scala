import scala.collection.mutable._
import scala.collection._
import scala.io.BufferedSource
import scala.io.StdIn._

object Initialization{

  //populate a two dimensional array with information from the CSV of the customers orders
  def populateCSVinArray(bufferedSource: BufferedSource): ArrayBuffer[Array[String]] ={

    var orderInformation: Array[String] = new Array[String](7)
    val populated: ArrayBuffer[Array[String]] = new ArrayBuffer[Array[String]]

    for(line <- bufferedSource.getLines()) {

      orderInformation = line.split(',').map(_.trim)
      populated.append(orderInformation)

    }
    //return that array
    populated
  }

  //menu for the user to be able to choose what they would like to do
  def menuChoice(): Int ={
    println("Please enter one of the following:")
    println("1: Print out all orders")
    println("2: Search an order by either the customer name or customer order ID")
    println("3: Update an order's status")
    println("4: Add order delivered by supplier")
    println("5: Print current stock levels")
    println("6: Remove an item with certain quantity from stock")
    println("If you would like to exit, simply press any other key and press enter.")
    val userInput = readInt()
    userInput
  }

  //holds information about how much stock is currently available
  def stock(): mutable.HashMap[String, Int] ={

    //access stock CSV
    val bufferedStock = io.Source.fromFile("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\stock.csv")
    var gnomeStock: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]
    var gnomeInformation: Array[String] = new Array[String](2)

    for(line <- bufferedStock.getLines()){
      gnomeInformation = line.split(':').map(_.trim)
      gnomeStock += gnomeInformation(0) -> gnomeInformation(1).toInt
    }
    //return hashmap of Product Name -> Product Quantity in inventory
    gnomeStock
  }

  //MENUOPTION 5
  //too short of code so no need to create another file for it
  //print current stock levels
  def printStockLevels(gnomeStock: mutable.HashMap[String, Int]): Unit ={
    for(element <- gnomeStock.keySet){
      println(element + ": " + gnomeStock(element))
    }
  }

  //ask for user input
  def userInput(): String ={
    val userInput = readLine()
    userInput
  }

}