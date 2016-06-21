import java.io.{File, FileWriter}
import java.util.Calendar
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn._

object MenuOption4{

  def addOrderDelivered(): Unit ={

    //access suppliers CSV
    val bufferedSource = io.Source.fromFile("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\testsupplier.csv")
    val currentCSV: ArrayBuffer[String] = new ArrayBuffer[String]

    //add information to variables
    println("Please give the information for the order: ")
    println("Please enter the suppliers name: ")
    val supplierName = readLine()
    //Date should automatically be entered
    println("Please enter the suppliers address: ")
    val address = readLine()
    println("Please enter the suppliers phone number: ")
    val paymentInfo = readLine()
    println("Please enter the product name followed by the quantity: ")
    val productName_Quantity = readLine()

    //create array holding all information given
    val newOrder = supplierName.toLowerCase().split(' ').map(_.trim).map(_.capitalize).mkString(" ") + ", " +
      address.capitalize + ", " +
      paymentInfo.capitalize + ", " +
      Calendar.getInstance().getTime + ", " +
      productName_Quantity.toLowerCase().split(' ').map(_.capitalize).mkString(" ")

    for(line <- bufferedSource.getLines()) {
      currentCSV.append(line)
    }

    currentCSV.append(newOrder)
    val file = new FileWriter(new File("C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\testsupplier.csv"))

    //write new line in CSV holding new suppliers information
    for(element <- currentCSV){
      file.write(element + "\n")
    }

    file.close()
  }
}
