package com.WOTS.system

import java.io.{BufferedWriter, File, FileWriter}

import scala.reflect.internal.util.Statistics.Quantity

/**
  * Created by Administrator on 28/06/2016.
  */
case class Product(location: String, productID: String, name: String, quantity: Int) {}

object Product {

  val fileName = "C:\\Users\\Administrator\\IdeaProjects\\Prog1\\src\\com\\WOTS\\data\\stock.csv"

  //holds information about how much stock is currently available
  def readInProducts(): Array[Product] ={
    val bufferedSource = io.Source.fromFile(fileName)

    var productsInWarehouse: Array[Product] = Array.empty
    //val populated: ArrayBuffer[Array[String]] = new ArrayBuffer[Array[String]]

    for(line <- bufferedSource.getLines()) {

      val cols = line.split(',').map(_.trim)

      productsInWarehouse = productsInWarehouse :+ new Product(cols(0), cols(1), cols(2), cols(3).toInt)

    }
    bufferedSource.close()
    //return that array
    productsInWarehouse
  }

  def findAllByOrderLineID(orderLines: Array[OrderLine], products: Array[Product]): Array[Product] ={

    var foundProducts: Array[Product] = Array.empty

    def findProduct(oL: OrderLine, productsArray: Array[Product]): Unit ={
      if(productsArray.isEmpty) {
        //exit
      }
      else if(oL.productID == productsArray.head.productID){
        foundProducts = foundProducts :+ productsArray.head
        findProduct(oL, productsArray.tail)
      }
      else {
        findProduct(oL, productsArray.tail)
      }
    }

    println("Searching for product...")
    for(orderLine <- orderLines) {
      findProduct(orderLine, products)
    }
     //return product array
    foundProducts
  }

  def findSingleProduct(productID: String, productArray: Array[Product]): Product ={

    def findProduct(productID: String, arrayOfProducts: Array[Product]): Product ={
      if(arrayOfProducts.isEmpty){
        println("Product not found.")
        new Product("ERROR", " ", " ", 0)
      }
      else if(productID == arrayOfProducts.head.productID){
        arrayOfProducts.head
      }
      else {
        findProduct(productID, arrayOfProducts.tail)
      }
    }
    findProduct(productID, productArray)

  }

  def printAllDetails(products: Array[Product]): Unit ={

    println("ProductID  |  ProductName  |  Quantity in Stock  |  Product Location")
    for(product <- products) {
      println(product.productID + "   " + product.name + "   " + product.quantity + "   " + product.location)
    }
  }

  def writeToCSV(products: Array[Product]): Unit ={
    val file = new File(fileName)
    val writer = new BufferedWriter(new FileWriter(file))
    var text = ""

    for(item <- products){
      text = item.location + ", " + item.productID + ", " + item.name + ", " + item.quantity + "\n"
      writer.write(text)
    }
    writer.close()
  }

  def updateStockQuantity(products: Array[Product], productID: String, quant: Int, increment: Boolean): Array[Product] ={

    val item = findSingleProduct(productID, products)
    var newProductList: Array[Product] = Array.empty
    //Product(location: String, productID: String, name: String, quantity: Int)
    def updateStockArray(productArray: Array[Product], product: Product): Array[Product] ={
      if(productArray.isEmpty) {
        productArray
      }
      else if(productArray.head.productID == product.productID){
        if(increment)
          updateStockArray(productArray.tail, product) :+ productArray.head.copy(quantity = productArray.head.quantity + quant)
        else
          updateStockArray(productArray.tail, product) :+ productArray.head.copy(quantity = productArray.head.quantity - quant)
        }
        else {
          updateStockArray(productArray.tail, product) :+ productArray.head
        }
    }

    if(item.location != "ERROR") {
      if(item.quantity < quant) {
        println(s"There is not enough stock for this item. ${item.name}: ${item.quantity}")
      }
      else {
        newProductList = updateStockArray(products, item)
        writeToCSV(newProductList)
      }
    }
    else {
      println("This item was not found.")
    }
    newProductList
  }

  //location: String, productID: String, name: String, quantity: Int
  def addNewStock(products: Array[Product]): Array[Product] ={

    var productArray: Array[Product] = products

    println("Enter the name of the product: ")
    val productName = Menu.userInput()

    println("Enter product ID:")
    val productID = Menu.userInput()

    println("Enter quantity to add: ")
    val stockQuantity = Menu.userInput()

    println("Enter the zone which this item will be located in: ")
    val location = Menu.userInput()

    productArray = productArray :+ new Product(location, productID, productName, stockQuantity.toInt)

    writeToCSV(productArray)
    productArray
  }

  def printSingleProduct(product: Product): Unit ={
    println("ProductID  |  ProductName  |  Quantity in Stock  |  Product Location")
    println(product.productID + "   " + product.name + "   " + product.quantity + "   " + product.location)
  }

}
