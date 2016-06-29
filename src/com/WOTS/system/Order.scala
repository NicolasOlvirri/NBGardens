package com.WOTS.system

/**
  * Created by Administrator on 28/06/2016.
  * var orderLines:Array[com.WOTS.system.OrderLine] = Array.Empty
  * for (loop) {
  * orderLines = orderLines :+ new com.WOTS.system.OrderLine(col(x), col (y))
  * }
  * new com.WOTS.system.Order(_, _, _ ...., orderLines
  */
case class Order(orderStatus: String, orderID: String, customerID: String, customerName: String, orderDate: String, orderLine: Array[OrderLine]) {}

object Order {

  val bufferedSource = io.Source.fromFile("src\\com\\WOTS\\data\\customerOrderForm.csv")

  //populate a two dimensional array with information from the CSV of the customers orders
  def readInOrders(): Array[Order] = {

    var orders: Array[Order] = Array.empty
    //var orderLine: Array[OrderLine] = Array.empty

    for (line <- bufferedSource.getLines()) {
      var orderLine: Array[OrderLine] = Array.empty
      val orderInformation = line.split(',').map(_.trim)
      val products = orderInformation(6).split('|').map(_.trim)

      for (productIDAndQuantity <- products){
        val orderDetails = productIDAndQuantity.split(':').map(_.trim)
        orderLine = orderLine :+ new OrderLine(orderDetails(0), orderDetails(1).toInt)
      }

      orders = orders :+ new Order(orderInformation(0), orderInformation(1), orderInformation(2), orderInformation(3), orderInformation(4), orderLine)

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
      println("Order Status  |  Order ID  |  Customer ID  |  Customer Name  |  Order Date  |  Products Ordered")
      println(order.orderStatus + "  |  " + order.orderID + "  |  " + order.customerID + "  |  " + order.customerName + "  |  " + order.orderDate)
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
        println("Order Status  |  Order ID  |  Customer ID  |  Customer Name  |  Order Date  |  Products Ordered")
        println(arrayOfOrders.head.orderStatus + "  |  " + arrayOfOrders.head.orderID + "  |  " + arrayOfOrders.head.customerID + "  |  " +
          arrayOfOrders.head.customerName + "  |  " + arrayOfOrders.head.orderDate)
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
    var newOrders:Array[Order] = Array.empty
    def updateStatus(orderArray: Array[Order], orderID: String, newStatus: String): Array[Order] ={
      if(orderArray.isEmpty) {
        orderArray
      }
      else if(orderArray.head.orderID == orderID){
        updateStatus(orderArray.tail, orderID, newStatus) :+ orderArray.head.copy(orderStatus = newStatus)
      }
      else {
        updateStatus(orderArray.tail, orderID, newStatus) :+ orderArray.head
      }
    }
    println("Enter the orders ID to change the status: ")
    val orderID = Menu.userInput().toUpperCase()
    val orderSearchedFor = returnSingleOrder(orderID, orders)

    println("Enter the new status of the order: (New / Processing / Cancelled / Dispatched)")
    println("You may enter (N / P / C / D) for short: ")
    Menu.userInput().toLowerCase().capitalize match{
      case "N" | "New" =>
        if(orderSearchedFor.orderStatus == "New"){
          println(s"The order with ID: $orderID, is already set with status New")
          orders
        }
        else {
          newOrders = updateStatus(orders, orderID, "New")
          writeToCSV(newOrders)
          newOrders
        }

      case "P" | "Processing" =>
        if(orderSearchedFor.orderStatus == "Processing"){
          println(s"The order with ID: $orderID, is already set with status Processing")
          orders
        }
        else {
          newOrders = updateStatus(orders, orderID, "Processing")
          writeToCSV(newOrders)
          newOrders
        }

      case "C" | "Cancelled" =>
        if(orderSearchedFor.orderStatus == "Cancelled"){
          println(s"The order with ID: $orderID, is already set with status Cancelled")
          orders
        }
        else {
          updateStatus(orders, orderID, "Cancelled")
          newOrders = writeToCSV(newOrders)
          newOrders
        }

      case "D" | "Dispatched" =>
        if(orderSearchedFor.orderStatus == "Dispatched"){
          println(s"The order with ID: $orderID, is already set with status Dispatched")
          orders
        }
        else {
          updateStatus(orders, orderID, "Dispatched")
          newOrders = writeToCSV(newOrders)
          newOrders
        }
      case _ =>
        println("There was an input error.")
        orders
    }

  }

}