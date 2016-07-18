import com.WOTS.system._
import org.scalatest.{FlatSpec, Matchers, Tag}
/**
  * Created by Luke on 10/07/2016.
  */
class OrderTest extends FlatSpec with Matchers {

  //Check product array is not empty when reading in data
  it should "read in order data into order array" taggedAs ReadInOrdersSuccess in {
    Order.readInOrders().isEmpty shouldEqual false
  }

  //check if function returns a single order, given an order ID
  it should "return a single order given an order ID" taggedAs ReturnSingleOrderSuccess in {
    Order.returnSingleOrder("24341515", Order.readInOrders()).orderID shouldEqual "24341515"
  }

  it should "return null if order was not found" taggedAs ReturnSingleOrderFail in {
    Order.returnSingleOrder("lhfs", Order.readInOrders()) shouldEqual null
  }

  it should "assign a staff to an order" taggedAs AssignStaffToOrderSuccess in {
    Order.returnSingleOrder("24341515", Order.assignStaffToOrder(Order.readInOrders(), "24341515", "0001")).staffID shouldEqual "0001"
  }

  it should "fail to assign a staff member to an order" taggedAs AssignStaffToOrderFail in {
    val newOrders = Order.assignStaffToOrder(Order.readInOrders(), "4141351", "0")
    Order.returnSingleOrder("4141351", newOrders).staffID shouldEqual "None"
  }

  //For some reason when running this test, the CSV is rewritten with one of the orders missing.
  it should "update an orders status" taggedAs UpdateOrderStatusSuccess in {
    val newOrders = Order.updateStatus(Order.readInOrders(), "4141351", "Active")
    Order.returnSingleOrder("4141351", newOrders).orderStatus shouldEqual "Active"
  }

  it should "print orders with status as dispatched only" taggedAs PrintDispatchedOrdersSuccess in {
    val dispatchedOrders = Order.printDispatchedOrders(Order.readInOrders())
    for(dispatchedOrder <- dispatchedOrders){
      dispatchedOrder.orderStatus shouldEqual "Dispatched"
    }
  }

}

object ReadInOrdersSuccess extends Tag("ReadInOrdersSuccess")
object ReturnSingleOrderSuccess extends Tag("ReturnSingleOrderSuccess")
object ReturnSingleOrderFail extends Tag("ReturnSingleOrderFail")
object AssignStaffToOrderSuccess extends Tag("AssignStaffToOrderSuccess")
object AssignStaffToOrderFail extends Tag("AssignStaffToOrderFail")
object UpdateOrderStatusSuccess extends Tag("UpdateOrderStatusSuccess")
object PrintDispatchedOrdersSuccess extends Tag("PrintDispatchedOrdersSuccess")