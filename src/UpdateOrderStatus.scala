import java.io.{File, FileWriter}
import scala.collection.mutable.ArrayBuffer

object UpdateOrderStatus{

  def updateStatus(populatedArray: ArrayBuffer[Array[String]]): Unit ={

    //ask user to search for customer order they would like to update
    val userFound = SearchOrderForm.searchOrder(populatedArray)
    println("What would you like to update the status to? (Processing / New / Dispatched / Cancelled)")
    println("You may also abbreviate to P / N / D / C respectively: ")
    //ask user what they would like to update the status to
    var y = 0

    Initialization.userInput().toLowerCase() match{
      //if user wants to change status to Processing
      case "p" | "processing" =>
        //if the user's status is already processing, they cannot set it to processing again
        if("Processing" == userFound(0)){
          println("The user's status is already Processing.")
        }

        else {
          //NEED TO MODIFY STOCK IMS TO GET RID OF GNOMES THAT ARE IN THIS ORDER FROM THE STOCK LEVELS
          println("The stock has been updated.")

          //decrement3
          // stock
          UpdateStock.decrementStock(userFound, Initialization.stock())

          //set the customer order form to processing
          userFound(0) = "Processing"
          //access customer order form CSV to rewrite updated status
          val file = new FileWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\test.csv"))

          for (line <- populatedArray) {
            //rewrite csv with the customer order form status as processing
            if (userFound(1) == populatedArray(y)(1)) {
              populatedArray(y)(0) = userFound(0)
            }

            file.write(populatedArray(y)(0) + ", " +
              populatedArray(y)(1) + ", " +
              populatedArray(y)(2) + ", " +
              populatedArray(y)(3) + ", " +
              populatedArray(y)(4) + ", " +
              populatedArray(y)(5) + ", " +
              populatedArray(y)(6) + "\n")

            y += 1
          }
          file.close()
        }


      case "n" | "new" =>


        //if the user's status is already new, they cannot set it to new again
        if("New" == userFound(0)){
          println("The user's status is already New.")
        }
        else {

          //access customer order form CSV to rewrite updated status
          val file = new FileWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\test.csv"))
          //set the customer order form to new
          userFound(0) = "New"

          for (line <- populatedArray) {
            //rewrite csv with the customer order form status as new
            if (userFound(1) == populatedArray(y)(1)) {
              populatedArray(y)(0) = userFound(0)
            }

            file.write(populatedArray(y)(0) + ", " +
              populatedArray(y)(1) + ", " +
              populatedArray(y)(2) + ", " +
              populatedArray(y)(3) + ", " +
              populatedArray(y)(4) + ", " +
              populatedArray(y)(5) + ", " +
              populatedArray(y)(6) + "\n")

            y += 1
          }
          file.close()
        }

      case "d" | "dispatched" =>

        //if the user's status is already dispatched, they cannot set it to dispatched again
        if("Dispatched" == userFound(0)){
          println("The user's status is already being Dispatched.")
        }

        else {

          //access customer order form CSV to rewrite updated status
          val file = new FileWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\test.csv"))
          //set the customer order form to dispatched
          userFound(0) = "Dispatched"

          for (line <- populatedArray) {
            //rewrite csv with the customer order form status as dispatched
            if (userFound(1) == populatedArray(y)(1)) {
              populatedArray(y)(0) = userFound(0)
            }

            file.write(populatedArray(y)(0) + ", " +
              populatedArray(y)(1) + ", " +
              populatedArray(y)(2) + ", " +
              populatedArray(y)(3) + ", " +
              populatedArray(y)(4) + ", " +
              populatedArray(y)(5) + ", " +
              populatedArray(y)(6) + "\n")

            y += 1
          }
          file.close()
        }

      case "c" | "cancelled" =>
        //if the user's status is already cancelled, they cannot set it to cancelled again
        if("Cancelled" == userFound(0)){
          println("The user's status is already Cancelled.")
        }

        else {

          //access customer order form CSV to rewrite updated status
          val file = new FileWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\test.csv"))
          //set the customer order form to cancelled
          userFound(0) = "Cancelled"

          for (line <- populatedArray) {
            //rewrite csv with the customer order form status as cancelled
            if (userFound(1) == populatedArray(y)(1)) {
              populatedArray(y)(0) = userFound(0)
            }

            file.write(populatedArray(y)(0) + ", " +
              populatedArray(y)(1) + ", " +
              populatedArray(y)(2) + ", " +
              populatedArray(y)(3) + ", " +
              populatedArray(y)(4) + ", " +
              populatedArray(y)(5) + ", " +
              populatedArray(y)(6) + "\n")

            y += 1
          }
          file.close()
        }
    }
  }
}