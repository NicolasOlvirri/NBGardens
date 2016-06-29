//Program by Nicolas Olvirri
//Initially started 13/06/2016
import com.WOTS.data
import com.WOTS.system.{Menu, Order, Product}

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
        case "2" => //search order by name / form ID
//          SearchOrderForm.searchOrder(Menu.populateCSVinArray())
//        case 3 => //update status of an order
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
