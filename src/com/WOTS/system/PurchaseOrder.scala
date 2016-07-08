package com.WOTS.system

import java.io.{BufferedWriter, File, FileWriter}

/**
  * Created by Administrator on 30/06/2016.
  */
//create class with information on a purchase order from a supplier
case class PurchaseOrder(orderID: String, orderStatus: String, orderDate: String, supplierName: String, orderLine: Array[OrderLine]) {}

object PurchaseOrder{

  //CSV for purchase orders
  val fileName = "src\\com\\WOTS\\data\\purchaseOrders.csv"

  //read CSV to return an array holding all purchase orders
  def readInPurchases(): Array[PurchaseOrder] = {
    val bufferedSource = io.Source.fromFile(fileName)

    var purchaseOrders: Array[PurchaseOrder] = Array.empty

    //for each line in the CSV, split the information and store it
    for (line <- bufferedSource.getLines()) {
      var orderLine: Array[OrderLine] = Array.empty
      val orderInformation = line.split(',').map(_.trim)
      val products = orderInformation(5).split('|').map(_.trim)

      //for the orderlines in a purchase order form, store it into an orderline array
      for (productIDAndQuantity <- products){
        val orderDetails = productIDAndQuantity.split(':').map(_.trim)
        orderLine = orderLine :+ new OrderLine(orderDetails(0), orderDetails(1).toInt)
      }

      //store all purchase orders
      purchaseOrders = purchaseOrders :+ new PurchaseOrder(orderInformation(0), orderInformation(1), orderInformation(2), orderInformation(3), orderLine)

    }
    bufferedSource.close()
    purchaseOrders
  }

  //when a purchase order is received, update the status of the purchase order to received and increment stock levels with new stock
  def orderReceived(purchaseOrders: Array[PurchaseOrder], orderID: String): Array[PurchaseOrder] ={
    var isReceived: Boolean = false
    var newPurchaseOrders: Array[PurchaseOrder] = Array.empty
    val orderSearchedFor = searchSingleOrder(purchaseOrders, orderID)

    def changeOrderStatus(purchaseOrders: Array[PurchaseOrder]): Array[PurchaseOrder] ={
      if(purchaseOrders.isEmpty){
        purchaseOrders
      }
      else if(purchaseOrders.head.orderID == orderID) {
        //if the order has already been received do not update orders status
        if (orderSearchedFor.orderStatus == "Received") {
          println("The order has already been set to received.")
          purchaseOrders
        }
        else {
          println("Changing order status to Received")
          isReceived = true
          changeOrderStatus(purchaseOrders.tail) :+ purchaseOrders.head.copy(orderStatus = "Received")
        }
      }
      else
        changeOrderStatus(purchaseOrders.tail) :+ purchaseOrders.head
    }
    newPurchaseOrders = changeOrderStatus(purchaseOrders)
    //if a purchase order was not already received, then you can increment the stock levels of the given stock
    if(isReceived){
      for(item <- orderSearchedFor.orderLine) {
        Product.writeToCSV(Product.updateStockQuantity(Main.products, item.productID, item.quantity, isReceived))
      }
    }
    else{

    }
    //rewrite results to new CSV
    writeToCSV(newPurchaseOrders)
    newPurchaseOrders
  }

  //search for a single purchase order form given an ID
  def searchSingleOrder(purchaseOrders: Array[PurchaseOrder], orderID: String): PurchaseOrder ={
    def findOrder(orderID: String, purchaseOrder: Array[PurchaseOrder]): PurchaseOrder ={
      if(purchaseOrder.isEmpty){
        println(s"Could not find order with ID: $orderID")
        null
      }
      else{
        if(orderID == purchaseOrder.head.orderID)
          purchaseOrder.head
        else
          findOrder(orderID, purchaseOrder.tail)
      }
    }
    findOrder(orderID, purchaseOrders)
  }

  //rewrite changes made to a CSV
  def writeToCSV(purchaseOrders: Array[PurchaseOrder]): Unit ={
    //orderID: String, orderStatus: String, orderDate: String, supplierName: String, orderLines: Array[OrderLine]
    def writeOrderLineToCSV(order: PurchaseOrder): String ={
      var orderLineText: String = ""
      for(orderLine <- order.orderLine) {
        orderLineText += orderLine.productID + ": " + orderLine.quantity + " | "
      }
      orderLineText
    }
    val file = new File(fileName)
    val writer = new BufferedWriter(new FileWriter(file))
    var text = ""
    for(item <- purchaseOrders){
      text = item.orderID + ", " + item.orderStatus + ", " + item.orderDate + ", " + item.supplierName + ", " + item.orderDate + ", " + writeOrderLineToCSV(item) + "\n"
      writer.write(text)
    }
    writer.close()
  }

  //print out the details given the purchase orders ID
  def printSinglePurchaseOrder(purchaseOrders: Array[PurchaseOrder], orderID: String): String ={

    val orderSearchedFor: PurchaseOrder = searchSingleOrder(purchaseOrders, orderID)
    var text = ""

    println("Purchase Order ID  |  Order Status  |  Order Date  |  Supplier Name  |  Products Ordered")
    text += orderSearchedFor.orderID + "  |  " + orderSearchedFor.orderStatus + "  |  " + orderSearchedFor.orderDate + "  |  " + orderSearchedFor.supplierName + "  |  " + printAllOrderLines(orderSearchedFor)
    text
  }

  //print out all products purchased listed on the purchase order
  def printAllOrderLines(purchaseOrder: PurchaseOrder): String ={
    var text = ""
    for (orderLine <- purchaseOrder.orderLine) {
      text += orderLine.productID + ": " + orderLine.quantity + " | "
    }
    text
  }

  //return an array holding all purchase orders which have a received status
  def returnAllReceivedOrders(purchaseOrder: Array[PurchaseOrder]): Array[PurchaseOrder] ={
    var receivedOrders: Array[PurchaseOrder] = Array.empty
    def findReceived(orders: Array[PurchaseOrder]): Array[PurchaseOrder] ={
      if(orders.isEmpty){
        orders
      }
      else if(orders.head.orderStatus == "Received"){
        receivedOrders = receivedOrders :+ orders.head
        findReceived(orders.tail)
      }
      else{
        findReceived(orders.tail)
      }
    }
    findReceived(purchaseOrder)
    receivedOrders
  }

  //print all the details of all purchase orders
  def printAllDetails(purchaseOrder: Array[PurchaseOrder]): Array[String] ={
    var text: Array[String] = Array.empty
    for(order <- purchaseOrder) {
      text = text :+ order.orderID + "  |  " + order.orderStatus + "  |  " + order.orderDate + "  |  " + order.supplierName + "  |  " + printAllOrderLines(order)
    }
    text
  }

}