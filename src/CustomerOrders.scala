//Program by Nicolas Olvirri
//Initially started 13/06/2016
import scala.language._
import scala.io.StdIn._

object CustomerOrders{

  def main(args: Array[String]) {

    //print user menu
    Initialization.menu()
    //user input to choose from menu
    var userInput = readInt()

    while(userInput == 1 || userInput == 2 || userInput == 3 || userInput == 4 || userInput == 5) {
      //access CSV file
      val bufferedSource = io.Source.fromFile("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\test.csv")

      userInput match {
        case 1 => //print out the whole csv
          MenuOption1.printAllOrders(Initialization.populateCSVinArray(bufferedSource))
        case 2 => //search order by name / form ID
          MenuOption2.searchOrder(Initialization.populateCSVinArray(bufferedSource))
        case 3 => //update status of an order
          MenuOption3.updateStatus(Initialization.populateCSVinArray(bufferedSource))
        case 4 => //add supplier delivery
          MenuOption4.addOrderDelivered()
        case 5 => //print stock levels
          Initialization.printStockLevels(Initialization.stock())
        case default =>
          sys.exit
      }

      Initialization.menu()
      userInput = readInt()
      bufferedSource.close()
    }
  }
}
