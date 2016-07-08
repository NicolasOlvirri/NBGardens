package com.WOTS.gui

import com.WOTS.system.Product

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.{Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.HBox

/**
  * Created by Yuan on 07/07/2016.
  */
case class AddNewStockFace() extends Scene{

  val getStockData = new getStockData
  var tableS = new getStockTables(getStockData)

  val menuButton = new MenuButton()
  val menu = menuButton.menu

  val Add = new Button("Add") //a button
  Add.minWidth = 90
  Add.minHeight = 100
  Add.onAction = (e: ActionEvent) => {
    if(textID.text.apply().isEmpty || textN.text.apply().isEmpty || textL.text.apply().isEmpty || textQ.text.apply().isEmpty){
      new Alert(AlertType.Error) {
        title = "Error Message"
        headerText = "Incorrect"
        contentText = "Please enter Data"
      }.showAndWait()
    }else{
      var products: Array[Product] = Product.readInProducts()
      products = products :+ new Product(textL.text.apply().toString, textID.text.apply().toString, textN.text.apply().toString, textQ.text.apply().toInt)
      Product.writeToCSV(products)
      getStockData.getStockData(products)
    }
  }

  val textID = new TextArea() {promptText = "Product ID"}//a button
  textID.maxWidth = 140
  textID.maxHeight = 20

  val textN = new TextArea() {promptText = "Product Name"}//a button
  textN.maxWidth = 140
  textN.maxHeight = 20

  val textL = new TextArea() {promptText = "Product Location"}//a button
  textL.maxWidth = 140
  textL.maxHeight = 20

  val textQ = new TextArea() {promptText = "Product QTY"}//a button
  textQ.maxWidth = 140
  textQ.maxHeight = 20

  val textBox = new HBox(10, textID, textN, textL, textQ, Add)
  textBox.alignment = Pos.Center

  tableS.setProductTable()

  var table = tableS.table

  val sph = new SplitPane(){
    autosize()
    items.addAll(textBox,table)
    dividerPositions=(0.10)
  }
  sph.setOrientation(Orientation.Vertical)

  val spv = new SplitPane(){
    autosize()
    items.addAll(menu,sph)
    dividerPositions=(0.20)
  }

  menuButton.PrintOrder.onAction = (e: ActionEvent) => {
    MainFace.changeScene(OrderFace())
  }

  menuButton.UpdateOrder.onAction = (e: ActionEvent) => {
    MainFace.changeScene(OrderFace())
  }

  menuButton.DispatchedOrders.onAction = (e: ActionEvent) => {
    MainFace.changeScene(OrderFace())
  }

  menuButton.ProductLevelManagement.onAction = (e: ActionEvent) => {
    MainFace.changeScene(StockFace())
  }

  menuButton.SingleProduct.onAction = (e: ActionEvent) => {
    try {
      val dialog = new TextInputDialog() {
        title = "Input ProductID"
        headerText = "Please enter a product ID:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(id) =>
          val products: Array[Product]= Array(Product.findSingleProduct(id.toString, getStockData.products))
          getStockData.getStockData(products)
        case None =>
      }
    }
    catch {
      case e: Exception =>
        new Alert(AlertType.Error) {
          title = "Error Message"
          headerText = "Incorrect data"
          contentText = "Please check your product ID"
        }.showAndWait()
    }
  }

  menuButton.PurchaseStatus.onAction = (e: ActionEvent) => {
    if(Login2.go){
      MainFace.changeScene(PurchaseFace())
    }else{
      MainFace.changeScene(Login2)
    }
  }

  menuButton.SinglePurchase.onAction = (e: ActionEvent) => {
    if(Login2.go){
      MainFace.changeScene(PurchaseFace())
    }else{
      MainFace.changeScene(Login2)
    }
  }

  menuButton.PurchaseOrders.onAction = (e: ActionEvent) => {
    if(Login2.go){
      MainFace.changeScene(PurchaseFace())
    }else{
      MainFace.changeScene(Login2)
    }
  }


  root = spv
}
