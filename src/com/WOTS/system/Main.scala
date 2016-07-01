package com.WOTS.system

//Program by Nicolas Olvirri
//Initially started 13/06/2016
import scala.language._

object Main{

  var products: Array[Product] = Product.readInProducts()
  var orders: Array[Order] = Order.readInOrders()
  var purchaseOrders: Array[PurchaseOrder] = PurchaseOrder.readInPurchases()

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
              products = Product.readInProducts()

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

        case "5" => //print dispatched orders if logged in with accounts
          println("Enter your staff ID: ")
          val staffID = Menu.userInput()
          if(Staff.returnRole(staffID)){
            Order.printDispatchedOrders(orders)
          }
          else println("This staff member is not a part of Accounts\n")

        case "6" => //print single product details
          println("Enter the product ID you would like to search for: ")
          Product.printSingleProduct(Product.findSingleProduct(Menu.userInput(), products))

        case "7" => //update stock when purchase order received
          println("Enter the ID of the purchase order: ")
          purchaseOrders = PurchaseOrder.orderReceived(purchaseOrders, Menu.userInput())

        case "8" => //print purchase order
          println("Enter the order you would like to print out")
          println(PurchaseOrder.printSinglePurchaseOrder(purchaseOrders, Menu.userInput()))

        case "9" => //print all received orders if from accounts
          println("Enter your staff ID: ")
          val staffID = Menu.userInput()
          if(Staff.returnRole(staffID)){
            PurchaseOrder.printAllDetails(PurchaseOrder.returnAllReceivedOrders(purchaseOrders))
          }
          else println("This staff member is not a part of Accounts\n")

        case "0" =>
          sys.exit
        case _ =>
          println("Invalid input. Please try again.\n")
      }

    }
  }
}
