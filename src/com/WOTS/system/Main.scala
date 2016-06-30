package com.WOTS.system

//Program by Nicolas Olvirri
//Initially started 13/06/2016
import scala.language._

object Main{

  var products: Array[Product] = Product.readInProducts()
  var orders: Array[Order] = Order.readInOrders()

  def main(args: Array[String]) {

    while(true) {

      Menu.mainMenu() match {

        case "1" => //print order details
          Menu.printOrderMenu() match{
            case "1" => //print all orders
              Order.printAllDetails(orders)
            case "2" => //print single order
              println("Please enter order ID: ")
              Order.printSingleOrder(Menu.userInput().toUpperCase(), orders)

          }

        case "2" => //update order status
              orders = Order.updateOrderStatus(orders)

        case "3" => //increment or decrement stock
          Menu.updateOrderMenu() match {
            case "1" =>
              println("Enter product ID to update: ")
              val productID = Menu.userInput()
              println("Enter the quantity you would like to add: ")
              val productQuantity = Menu.userInput().toInt
              val bool: Boolean = true
              products = Product.updateStockQuantity(products, productID, productQuantity, bool)
            case "2" =>
              println("Enter product ID to update: ")
              val productID = Menu.userInput()
              println("Enter the quantity you would like to decrement by: ")
              val productQuantity = Menu.userInput().toInt
              val bool: Boolean = false
              products = Product.updateStockQuantity(products, productID, productQuantity, bool)

          }

        case "4" => //add new stock item
              products = Product.addNewStock(products)




//          UpdateOrderStatus.updateStatus(Menu.populateCSVinArray())
//        case 4 => //add supplier delivery
//          Supplier.addSupplier()
//        case 5 => //print stock levels
//          PrintCSV.printStockLevels(Menu.stock())
//        case 6 => //decrement stock by given product and given amount
//          UpdateStock.removeItemFromStock(Product.readInProducts())
          //products = Product.updateStockQuantity(products, "dwedw", 3)
        case "0" =>
          sys.exit
        case _ =>
          println("Invalid input. Please try again.\n")
      }
    }
  }
}
