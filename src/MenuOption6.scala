/**
  * Created by Administrator on 17/06/2016.
  */
import scala.collection._
import scala.io.StdIn._

object MenuOption6{
  def removeItemFromStock(stock: mutable.HashMap[String, Int]): Unit ={

    println("What is the name of the item you would like to update?")
    //ask user what item they would like to remove
    val itemToRemove = readLine()
    println(s"How many items would you like to remove of item: $itemToRemove?")
    //ask user how many items they would like to remove of the item given
    val itemQuantity = readInt()

    println(s"Are you sure you would like to remove $itemQuantity of $itemToRemove? (Y/N)")
    //double check they want to remove these items
    val choice = readChar()

    //if they agreed, decrement IMS value by quantity given
//    if(choice == 'Y' || choice == 'y'){
//      def findItemToRemove(stock: mutable.HashMap[String, Int]): Unit ={
//        if(itemToRemove == stock.get(itemToRemove)){
//
//        }
//
//      }
//    }
    else
      println("The program will now exit.")
      sys.exit

  }
}