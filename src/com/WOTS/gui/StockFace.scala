package com.WOTS.gui

import com.WOTS.gui
import com.WOTS.gui.{MenuButton => _}
import com.WOTS.system.Product

import scalafx.Includes._
import scalafx.event._
import scalafx.geometry.{Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.HBox
import scalafx.scene.text.{Font, FontWeight}
/**
  * Created by Yuan on 06/07/2016.
  */
case class StockFace() extends Scene{

  val getStockData = new getStockData
  var tableS = new getStockTables(getStockData)

  val menuButton = new gui.MenuButton()
  val menu = menuButton.menu

  val Set = new Button("Set")  {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  Set.minWidth = 90
  Set.minHeight = 90
  Set.onAction = (e: ActionEvent) => {
    val id = text.text.apply().toString
    val qty = textQ.text.apply().toInt
    var products: Array[Product] = Product.readInProducts()
    if(id == "" || qty == ""){
      new Alert(AlertType.Error) {
        title = "Error Message"
        headerText = "Incorrect input!"
        contentText = "Please type the correct productId/Qty"
      }.showAndWait()
    }
    else{
      if(toggleForP.selectedToggle.apply().getUserData.toString=="Increment"){
        val bool: Boolean = true
        products = Product.updateStockQuantity(products, id, qty, bool)
        getStockData.getStockData(products)
      }
      if(toggleForP.selectedToggle.apply().getUserData.toString=="Decrement"){
        val bool: Boolean = false
        products = Product.updateStockQuantity(products, id, qty, bool)
        getStockData.getStockData(products)
      }
    }
  }

  val choose5 = new RadioButton("Increment Stock By") {
    font = Font.font(null, FontWeight.Bold, 16)
  }
  choose5.setUserData("Increment")
  val choose6 = new RadioButton("Decrement Stock") {
    font = Font.font(null, FontWeight.Bold, 16)
  }
  choose6.setUserData("Decrement")

  val toggleForP = new ToggleGroup()
  toggleForP.toggles = List (choose5,choose6)

  val text = new TextArea() {promptText = "ID"}//a button
  text.maxWidth = 140
  text.maxHeight = 20

  val textQ = new TextArea() {promptText = "Number"}//a button
  textQ.maxWidth = 140
  textQ.maxHeight = 20

  val textBox = new HBox(10, text, choose5, choose6, textQ, Set)
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

  menuButton.InputStock.onAction = (e: ActionEvent) => {
    MainFace.changeScene(AddNewStockFace())
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
    }else {
      MainFace.changeScene(Login2)
    }
  }

  root = spv
}
