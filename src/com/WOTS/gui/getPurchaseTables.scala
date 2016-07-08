package com.WOTS.gui

import com.WOTS.system.PurchaseOrder

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.scene.control.{TableColumn, TableView}

/**
  * Created by Administrator on 07/07/2016.
  */
class getPurchaseTables (getPurchaseData: getPurchaseData){

  var table = new TableView(getPurchaseData.data)

  def setProductTable() ={
    val tableT = new TableView(getPurchaseData.data)
    getPurchaseData.getPurchaseData(PurchaseOrder.readInPurchases())
    val col1ForTable = new TableColumn[PurchaseOrder, String]("PurchasesID")
    col1ForTable.cellValueFactory = cdf => ObjectProperty(cdf.value.orderID)
    col1ForTable.prefWidth=150
    val col2ForTable = new TableColumn[PurchaseOrder, String]("Status")
    col2ForTable.prefWidth=150
    col2ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderStatus)
    val col3ForTable = new TableColumn[PurchaseOrder, String]("PurchasesDate")
    col3ForTable.prefWidth=150
    col3ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderDate)
    val col4ForTable = new TableColumn[PurchaseOrder, String]("Supplier")
    col4ForTable.prefWidth=150
    col4ForTable.cellValueFactory = cdf => StringProperty(cdf.value.supplierName)
    val col5ForTable = new TableColumn[PurchaseOrder, String]("Goods")
    col5ForTable.prefWidth=150
    col5ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderLine.toString)

    tableT.columns ++= List(col1ForTable, col2ForTable, col3ForTable, col4ForTable, col5ForTable)
    table = tableT
  }
}