//Program by Nicolas Olvirri
//Initially started 13/06/2016
import scala.language._

object CustomerOrders{

  def main(args: Array[String]) {

    while(true) {
      //access CSV file
      val bufferedSource = io.Source.fromFile("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\test.csv")

      Initialization.menuChoice() match {
        case 1 => //print out the whole csv
          PrintCSV.printAllOrders(Initialization.populateCSVinArray(bufferedSource))
        case 2 => //search order by name / form ID
          SearchOrderForm.searchOrder(Initialization.populateCSVinArray(bufferedSource))
        case 3 => //update status of an order
          UpdateOrderStatus.updateStatus(Initialization.populateCSVinArray(bufferedSource))
        case 4 => //add supplier delivery
          AddSupplier.addOrderDelivered()
        case 5 => //print stock levels
          PrintCSV.printStockLevels(Initialization.stock())
        case 6 => //decrement stock by given product and given amount
          UpdateStock.removeItemFromStock(Initialization.stock())
        case default =>
          sys.exit
      }
      bufferedSource.close()
    }
  }
}
