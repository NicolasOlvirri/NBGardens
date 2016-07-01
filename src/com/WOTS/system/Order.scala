package com.WOTS.system

import java.io.{BufferedWriter, File, FileWriter}

/**
  * Created by Administrator on 28/06/2016.
  * var orderLines:Array[com.WOTS.system.OrderLine] = Array.Empty
  * for (loop) {
  * orderLines = orderLines :+ new com.WOTS.system.OrderLine(col(x), col (y))
  * }
  * new com.WOTS.system.Order(_, _, _ ...., orderLines
  */
case class Order(orderStatus: String, orderID: String, staffID: String, customerName: String, orderDate: String, address: String, paymentMethod: String, orderLine: Array[OrderLine]) {}

object Order {

  val fileName = "src\\com\\WOTS\\data\\customerOrderForm.csv"

  //populate array with orders
  def readInOrders(): Array[Order] = {
    val bufferedSource = io.Source.fromFile(fileName)

    var orders: Array[Order] = Array.empty

    for (line <- bufferedSource.getLines()) {
      var orderLine: Array[OrderLine] = Array.empty
      val orderInformation = line.split(',').map(_.trim)
      val products = orderInformation(7).split('|').map(_.trim)

      for (productIDAndQuantity <- products){
        val orderDetails = productIDAndQuantity.split(':').map(_.trim)
        orderLine = orderLine :+ new OrderLine(orderDetails(0), orderDetails(1).toInt)
      }

      orders = orders :+ new Order(orderInformation(0), orderInformation(1), orderInformation(2), orderInformation(3), orderInformation(4), orderInformation(5), orderInformation(6), orderLine)

    }
    bufferedSource.close()
    orders
  }

  def printAllOrderLines(order: Order): Unit ={
    for (orderLine <- order.orderLine) {
      print(orderLine.productID + ": " + orderLine.quantity + " | ")
    }
    println()
  }

  def printAllDetails(orders: Array[Order]): Unit ={


    for(order <- orders) {
      println("Order Status  |  Order ID  |  Staff ID  |  Customer Name  |  Order Date  |  Address  |  Payment Method")
      println(order.orderStatus + "  |  " + order.orderID + "  |  " + order.staffID + "  |  " + order.customerName + "  |  " + order.orderDate + "  |  " + order.address + "  |  " + order.paymentMethod)
      println("Product ID : Quantity")
      printAllOrderLines(order)
      println()
    }
  }

  def printSingleOrder(orderID: String, orders: Array[Order]): Unit ={

    def findOrder(orderID: String, arrayOfOrders: Array[Order]): Unit ={
      if(arrayOfOrders.isEmpty){
        println(s"Could not find order with given order ID: $orderID")
      }
      else if(orderID == arrayOfOrders.head.orderID){
        println("Order Status  |  Order ID  |  Staff ID  |  Customer Name  |  Order Date  |  Payment Method")
        println(arrayOfOrders.head.orderStatus + "  |  " + arrayOfOrders.head.orderID + "  |  " + arrayOfOrders.head.staffID + "  |  " +
          arrayOfOrders.head.customerName + "  |  " + arrayOfOrders.head.orderDate + " | " + arrayOfOrders.head.address + "  |  " + arrayOfOrders.head.paymentMethod)
        println("Product ID : Quantity")
        printAllOrderLines(arrayOfOrders.head)
        println()
      }
      else {
        findOrder(orderID, arrayOfOrders.tail)
      }
    }
    findOrder(orderID, orders)

  }

  def returnSingleOrder(orderID: String, orders: Array[Order]): Order ={
    def findOrder(orderID: String, orders: Array[Order]): Order ={
      if(orders.isEmpty){
        println(s"Could not find order with ID: $orderID")
        null
      }
      else{
        if(orderID == orders.head.orderID)
          orders.head
        else
          findOrder(orderID, orders.tail)
      }
    }
    findOrder(orderID, orders)
  }

  def updateOrderStatus(orders: Array[Order]): Array[Order] = {
    var newOrders: Array[Order] = Array.empty
    def updateStatus(orderArray: Array[Order], orderID: String, newStatus: String): Array[Order] = {
      if (orderArray.isEmpty) {
        orderArray
      }
      else if (orderArray.head.orderID == orderID) {
        updateStatus(orderArray.tail, orderID, newStatus) :+ orderArray.head.copy(orderStatus = newStatus)
      }
      else {
        updateStatus(orderArray.tail, orderID, newStatus) :+ orderArray.head
      }
    }
    println("Enter the orders ID to change the status: ")
    val orderID = Menu.userInput().toUpperCase()
    val orderSearchedFor = returnSingleOrder(orderID, orders)

    if (orderSearchedFor != null) {
      println("Enter the new status of the order: (Open / Active / Packaged / Dispatched)")
      println("You may enter (O / A / P / D) for short: ")
      Menu.userInput().toLowerCase() match {
        case "o" | "open" =>
          if (orderSearchedFor.orderStatus == "Open") {
            println(s"The order with ID: $orderID, is already set with status Open")
            orders
          }
          else {
            newOrders = updateStatus(orders, orderID, "Open")
            writeToCSV(newOrders)
            newOrders
          }

        case "a" | "active" =>
          if (orderSearchedFor.orderStatus == "Active") {
            println(s"The order with ID: $orderID, is already set with status Active")
            orders
          }
          else {
            println("Enter your staff ID: ")
            val staffID = Menu.userInput()

            for(item <- orderSearchedFor.orderLine) {
              Product.writeToCSV(Product.updateStockQuantity(Main.products, item.productID, item.quantity, false))
            }

            newOrders = updateStatus(orders, orderID, "Active")
            newOrders = assignStaffToOrder(newOrders, orderID, staffID)
            writeToCSV(newOrders)
            newOrders
          }

        case "p" | "packaged" =>
          if (orderSearchedFor.orderStatus == "Packaged") {
            println(s"The order with ID: $orderID, is already set with status Packaged")
            orders
          }
          else {
            updateStatus(orders, orderID, "Packaged")
            writeToCSV(newOrders)
            newOrders
          }

        case "d" | "dispatched" =>
          if (orderSearchedFor.orderStatus == "Dispatched") {
            println(s"The order with ID: $orderID, is already set with status Dispatched")
            orders
          }
          else {
            updateStatus(orders, orderID, "Dispatched")
            writeToCSV(newOrders)
            newOrders
          }
        case _ =>
          println("There was an input error.")
          orders
      }

    }
    else {
      println("Order not found")
      orders
    }
  }

  def assignStaffToOrder(orders: Array[Order], orderID: String, newStaffID: String): Array[Order] ={
    var newOrderArray: Array[Order] = Array.empty

    def updateStaffID(orderArray: Array[Order], newStatus: String): Array[Order] = {
      if (orderArray.isEmpty) {
        orderArray
      }
      else if (orderArray.head.orderID == orderID) {
        updateStaffID(orderArray.tail, newStatus) :+ orderArray.head.copy(staffID = newStaffID)
      }
      else {
        updateStaffID(orderArray.tail, newStatus) :+ orderArray.head
      }
    }
    newOrderArray = updateStaffID(orders, newStaffID)
    newOrderArray
  }

  def writeToCSV(orders: Array[Order]): Unit ={
    def writeOrderLineToCSV(order: Order): String ={
      var orderLineText: String = ""
      for(orderLine <- order.orderLine) {
        orderLineText = orderLine.productID + ": " + orderLine.quantity + " | "
      }
      orderLineText
    }
    val file = new File(fileName)
    val writer = new BufferedWriter(new FileWriter(file))
    var text = ""
    for(item <- orders){
      text = item.orderStatus + ", " + item.orderID + ", " + item.staffID + ", " + item.customerName + ", " + item.orderDate + ", " + item.address + ", " + item.paymentMethod + ", " + writeOrderLineToCSV(item) +"\n"
      writer.write(text)
    }
    writer.close()
  }

  def printDispatchedOrders(orders: Array[Order]): Array[Order] ={
    var dispatchedOrders: Array[Order] = Array.empty
    for(status <- orders){
      if(status.orderStatus == "Dispatched"){
        dispatchedOrders = dispatchedOrders :+ status
      }
    }
    printAllDetails(dispatchedOrders)
    dispatchedOrders
  }

}