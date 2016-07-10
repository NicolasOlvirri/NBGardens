package com.WOTS.gui

import com.WOTS.system.Product

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.scene.control.{TableColumn, TableView}
/**
  * Created by Yuan on 06/07/2016.
  */
class getStockTables (getStockData: getStockData){

  var table = new TableView(getStockData.data)

  def setProductTable() ={
    val tableT = new TableView(getStockData.data)
    getStockData.getStockData(Product.readInProducts())
    val col1ForTable = new TableColumn[Product, String]("ProductID")
    col1ForTable.cellValueFactory = cdf => ObjectProperty(cdf.value.productID)
    col1ForTable.prefWidth=150
    val col2ForTable = new TableColumn[Product, String]("Name")
    col2ForTable.prefWidth=150
    col2ForTable.cellValueFactory = cdf => StringProperty(cdf.value.name)
    val col3ForTable = new TableColumn[Product, String]("Location")
    col3ForTable.prefWidth=150
    col3ForTable.cellValueFactory = cdf => StringProperty(cdf.value.location)
    val col4ForTable = new TableColumn[Product, String]("Quantity")
    col4ForTable.prefWidth=150
    col4ForTable.cellValueFactory = cdf => StringProperty(cdf.value.quantity.toString)
    tableT.columns ++= List(col1ForTable, col2ForTable, col3ForTable, col4ForTable)
    table = tableT
  }
}
