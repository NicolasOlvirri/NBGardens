import com.WOTS.system._
import org.scalatest.{FlatSpec, Matchers, Tag}
/**
  * Created by Luke on 10/07/2016.
  */
class PurchaseOrderTest extends FlatSpec with Matchers {
  //Check purchase order array is not empty when reading in data
  it should "read in purchase order data into purchase order array" taggedAs ReadInPurchasesSuccess in {
    PurchaseOrder.readInPurchases().isEmpty shouldEqual false
  }

  //PurchaseOrder.orderReceived()
  //when a purchase order is received, update the status of the purchase order to received
  // and increment stock levels with new stock

  //search for a single purchase order form given an ID
  it should "find a purchase order given a correct ID" taggedAs FindSinglePurchaseOrderSuccess in {
    PurchaseOrder.searchSingleOrder(PurchaseOrder.readInPurchases(), "12347").orderID shouldEqual "12347"
  }

  //search for a single purchase order for given an incorrect ID
  it should "fail to find a purchase order given an incorrect ID" taggedAs FindSinglePurchaseOrderFailure in {
    PurchaseOrder.searchSingleOrder(PurchaseOrder.readInPurchases(), "daffads") shouldBe null
  }

  //print out the details of a purchase order, given its ID
  it should "successfully return the details of a purchase order" taggedAs PrintSinglePurchaseOrderSuccess in {
    assert(PurchaseOrder.printSinglePurchaseOrder(PurchaseOrder.readInPurchases(), "12347").length > 0)
  }

  //print out the details of a purchase order, given an incorrect ID
  it should "fail to return the details of a purchase order given invalid ID" taggedAs PrintSinglePurchaseOrderFailure in {
    PurchaseOrder.printSinglePurchaseOrder(PurchaseOrder.readInPurchases(), "dsafdsf") shouldBe "ERROR"
  }

  
}

object ReadInPurchasesSuccess extends Tag("ReadInPurchasesSuccess")

object FindSinglePurchaseOrderSuccess extends Tag("FindSinglePurchaseOrderSuccess")
object FindSinglePurchaseOrderFailure extends Tag("FindSinglePurchaseOrderFailure")
object PrintSinglePurchaseOrderSuccess extends Tag("PrintSinglePurchaseOrderSuccess")
object PrintSinglePurchaseOrderFailure extends Tag("PrintSinglePurchaseOrderFailure")