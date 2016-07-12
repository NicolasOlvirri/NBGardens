import com.WOTS.system._
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Luke on 10/07/2016.
  */
class ProductTest extends FlatSpec with Matchers {
  //Check product array is not empty when reading in data
  it should "read in product data into product array" taggedAs ReadInProductsSuccess in {
    Product.readInProducts().isEmpty shouldEqual false
  }

  //given a correct product ID, return the Product object and check it is the right one by checking the ID
  it should "find product via ID" taggedAs FindProductByIDSuccess in {
    Product.findSingleProduct("3001", Product.readInProducts()).productID shouldEqual "3001"
  }

  //given an incorrect product ID, return the Product object and check it is the incorrect Product by checking the ID
  it should "fail to find product via ID" taggedAs FindProductByIDFailure in {
    Product.findSingleProduct("saadasd", Product.readInProducts()).location shouldEqual "ERROR"
  }

  //Given an array of OrderLines, return an array of products where all products in the array are listed in the order line
  it should "find products given an order lines ID" taggedAs FindAllByOrderLineSuccess in {
    Product.findAllByOrderLineID(Order.readInOrders().head.orderLine, Product.readInProducts()).isEmpty  shouldEqual false
  }

  //given an array of products and a correct product ID, find the product and increase its stock
  it should "increase the stock of a product, given its ID" taggedAs UpdateStockQuantityIncreaseSuccess in {
    Product.findSingleProduct("3001", Product.readInProducts()).quantity+10 shouldEqual
      Product.findSingleProduct("3001", Product.updateStockQuantity(Product.readInProducts(), "3001", 10, true)).quantity
  }

  //given an array of products and a correct product ID, find the product and decrease its stock
  it should "decrease the stock of a product, given its ID" taggedAs UpdateStockQuantityDecreaseSuccess in {
    Product.findSingleProduct("3001", Product.readInProducts()).quantity-10 shouldEqual
      Product.findSingleProduct("3001", Product.updateStockQuantity(Product.readInProducts(), "3001", 10, false)).quantity
  }

  //given an array of products and an incorrect productID, check
  it should "fail to increase the stock of a product, given an incorrect ID" taggedAs UpdateStockQuantityIncreaseFail in {
    Product.updateStockQuantity(Product.readInProducts(), "khhho", 10, false).isEmpty shouldEqual true
  }

  //stock could not decrement since there is not enough in stock
  it should "fail to decrease the stock of a product, given an incorrect ID" taggedAs UpdateStockQuantityDecreaseFail in {
    Product.updateStockQuantity(Product.readInProducts(), "3001", 999999999, false).isEmpty shouldEqual true
  }
}

object ReadInProductsSuccess extends Tag("ReadInProductsSuccess")
object FindProductByIDSuccess extends Tag("FindProductByIDSuccess")
object FindProductByIDFailure extends Tag("FindProductByIDFailure")
object FindAllByOrderLineSuccess extends Tag("FindAllByOrderLineSuccess")
object UpdateStockQuantityIncreaseSuccess extends Tag("UpdateStockQuantityIncreaseSuccess")
object UpdateStockQuantityDecreaseSuccess extends Tag("UpdateStockQuantityDecreaseSuccess")
object UpdateStockQuantityIncreaseFail extends Tag("UpdateStockQuantityIncreaseFail")
object UpdateStockQuantityDecreaseFail extends Tag("UpdateStockQuantityDecreaseFail")