package com.WOTS.gui

import com.WOTS.gui

import scalafx.Includes._
import scalafx.event._
import scalafx.geometry.{Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.HBox
/**
  * Created by Yuan on 06/07/2016.
  */
case class PurchaseFace() extends Scene {

  val getPurchaseData = new getPurchaseData
  var tableP = new getPurchaseTables(getPurchaseData)

  val menuButton = new gui.MenuButton()
  val menu = menuButton.menu

//  val Set = new Button("Set") //a button
//  Set.minWidth = 90
//  Set.minHeight = 90
//  Set.onAction = (e: ActionEvent) => {
//    val id = text.text.apply().toString
//    val qty = textQ.text.apply().toInt
//    var products: Array[Product] = Product.readInProducts()
//    if(id == "" || qty == ""){
//      new Alert(AlertType.Error) {
//        title = "Error Message"
//        headerText = "Incorrect input!"
//        contentText = "Please type the correct productId/Qty"
//      }.showAndWait()
//    }
//    else{
//      if(toggleForP.selectedToggle.apply().getUserData.toString=="Increment"){
//        val bool: Boolean = true
//        products = Product.updateStockQuantity(products, id, qty, bool)
//        com.WOTS.gui.getStockData.com.WOTS.gui.getStockData(products)
//      }
//      if(toggleForP.selectedToggle.apply().getUserData.toString=="Decrement"){
//        val bool: Boolean = false
//        products = Product.updateStockQuantity(products, id, qty, bool)
//        com.WOTS.gui.getStockData.com.WOTS.gui.getStockData(products)
//      }
//    }
//  }

  val choose5 = new RadioButton("Increment Stock By")
  choose5.setUserData("Increment")
  val choose6 = new RadioButton("Decrement Stock")
  choose6.setUserData("Decrement")

  val toggleForP = new ToggleGroup()
  toggleForP.toggles = List (choose5,choose6)

  val text = new TextArea() {promptText = "ID"}//a button
  text.maxWidth = 140
  text.maxHeight = 20

  val textQ = new TextArea() {promptText = "Number"}//a button
  textQ.maxWidth = 140
  textQ.maxHeight = 20

  val textBox = new HBox(10, text, choose5, choose6, textQ)
  textBox.alignment = Pos.Center

  tableP.setProductTable()

  var table = tableP.table

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
    MainFace.changeScene(StockFace())
  }

  menuButton.InputStock.onAction = (e: ActionEvent) => {
    MainFace.changeScene(AddNewStockFace())
  }

  menuButton.PurchaseStatus.onAction = (e: ActionEvent) => {
    MainFace.changeScene(Login2)
  }

  menuButton.SinglePurchase.onAction = (e: ActionEvent) => {
    MainFace.changeScene(Login2)
  }

  menuButton.PurchaseOrders.onAction = (e: ActionEvent) => {
    MainFace.changeScene(PurchaseFace())
  }

  root = spv
}
