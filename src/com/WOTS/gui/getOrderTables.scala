package com.WOTS.gui

import com.WOTS.system.Order

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.text.{Font, FontWeight}

/**
  * Created by Yuan on 06/07/2016.
  */
class getOrderTables (getOrderData: getOrderData){

  var table = new TableView(getOrderData.data)

  def setOrderTable() ={
    val tableT = new TableView(getOrderData.data)
    getOrderData.getOrderData(Order.readInOrders())
    val col1ForTable = new TableColumn[Order, String]("Order Status"){
      Font(null, FontWeight.Bold, 16)
    }
    col1ForTable.cellValueFactory = cdf => ObjectProperty(cdf.value.orderStatus)
    col1ForTable.prefWidth=100
    val col2ForTable = new TableColumn[Order, String]("Order ID"){
      Font(null, FontWeight.Bold, 16)
    }
    col2ForTable.prefWidth=100
    col2ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderID)
    val col3ForTable = new TableColumn[Order, String]("Staff ID"){
      Font(null, FontWeight.Bold, 16)
    }
    col3ForTable.prefWidth=100
    col3ForTable.cellValueFactory = cdf => StringProperty(cdf.value.staffID)
    val col4ForTable = new TableColumn[Order, String]("Customer Name"){
      Font(null, FontWeight.Bold, 16)
    }
    col4ForTable.prefWidth=110
    col4ForTable.cellValueFactory = cdf => StringProperty(cdf.value.customerName)
    val col5ForTable = new TableColumn[Order, String]("Order Date"){
      Font(null, FontWeight.Bold, 16)
    }
    col5ForTable.prefWidth=100
    col5ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderDate)
    val col6ForTable = new TableColumn[Order, String]("Address"){
      Font(null, FontWeight.Bold, 16)
    }
    col6ForTable.prefWidth=100
    col6ForTable.cellValueFactory = cdf => StringProperty(cdf.value.address)
    val col7ForTable = new TableColumn[Order, String]("Payment Method"){
      Font(null, FontWeight.Bold, 16)
    }
    col7ForTable.prefWidth=110
    col7ForTable.cellValueFactory = cdf => StringProperty(cdf.value.paymentMethod)
    val col8ForTable = new TableColumn[Order, String]("Product ID : Quantity"){
      Font(null, FontWeight.Bold, 16)
    }
    col8ForTable.prefWidth=150
    col8ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderLine.mkString)
    tableT.columns ++= List(col1ForTable, col2ForTable, col3ForTable, col4ForTable, col5ForTable, col6ForTable, col7ForTable, col8ForTable)
    table = tableT
  }
}
