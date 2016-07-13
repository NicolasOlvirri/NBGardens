package com.WOTS.gui

import com.WOTS.gui
import com.WOTS.gui.{MenuButton => _}
import com.WOTS.system.{Main, Order, Product, Staff}

import scalafx.Includes._
import scalafx.event._
import scalafx.geometry.{Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.HBox

/**
  * Created by Yuan on 01/07/2016.
  */

case class OrderFace() extends Scene {
  val getOrderData = new getOrderData
  var tableO = new getOrderTables(getOrderData)

  val menuButton = new gui.MenuButton()
  val menu = menuButton.menu

  val check = new Button("Check") //a button
  check.prefWidth = 140
  check.prefHeight = 50
  check.onAction = (e: ActionEvent) => {
    val orders: Array[Order]= Array(Order.returnSingleOrder(text.text.apply().toString, getOrderData.orders))
    getOrderData.getOrderData(orders)
  }

  val update = new Button("Update") //a button
  update.prefWidth = 140
  update.prefHeight = 50
  update.onAction = (e: ActionEvent) => {
    try{
      if(toggle.selectedToggle.apply().getUserData.toString=="Active"){
        val dialog = new TextInputDialog() {
          title = "Input StaffID"
          headerText = "Please enter your ID:"
        }
        val result = dialog.showAndWait()
        result match {
          case Some(id) =>
            val orderSearchedFor = Order.returnSingleOrder(text.text.apply().toString, getOrderData.orders)
            for(item <- orderSearchedFor.orderLine) {
              Product.writeToCSV(Product.updateStockQuantity(Main.products, item.productID, item.quantity, false))
            }
            //update the status
            var newOrders = Order.updateStatus(getOrderData.orders, text.text.apply().toString, toggle.selectedToggle.apply().getUserData.toString)
            //update the staff member to the given members ID
            newOrders = Order.assignStaffToOrder(newOrders, text.text.apply().toString, id)
            //rewrite the changes to the CSV
            Order.writeToCSV(newOrders)
            val orders: Array[Order]= newOrders
            getOrderData.getOrderData(orders)
          case None     =>
        }
      }else{
        val newOrders = Order.updateStatus(getOrderData.orders, text.text.apply().toString, toggle.selectedToggle.apply().getUserData.toString)
        //rewrite the changes to the CSV
        Order.writeToCSV(newOrders)
        val orders: Array[Order]= newOrders
        getOrderData.getOrderData(orders)
      }
    }
    catch {
      case e: Exception =>
    }
  }

  val choose1 = new RadioButton("Open")
  choose1.setUserData("Open")
  val choose2 = new RadioButton("Active")
  choose2.setUserData("Active")
  val choose3 = new RadioButton("Packaged")
  choose3.setUserData("Packaged")
  val choose4 = new RadioButton("Dispatched")
  choose4.setUserData("Dispatched")

  val toggle = new ToggleGroup()
  toggle.toggles = List (choose1,choose2,choose3,choose4)

  val text = new TextArea() {promptText = "ID"}//a button
  text.maxWidth = 140
  text.maxHeight = 20

  val textBox = new HBox(10, text, check)
  textBox.alignment = Pos.Center

  tableO.setOrderTable()

  var table = tableO.table

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
    try{
      textBox.children.clear()
      textBox.children.addAll(text,check)
      getOrderData.getOrderData(Order.readInOrders())
    }
    catch {
      case e: Exception =>
    }
  }

  menuButton.UpdateOrder.onAction = (e: ActionEvent) => {
    try{
      textBox.children.clear()
      textBox.children.addAll(text,choose1,choose2,choose3,choose4,update)
      getOrderData.getOrderData(Order.readInOrders())
    }
    catch {
      case e: Exception =>
    }
  }

  menuButton.DispatchedOrders.onAction = (e: ActionEvent) => {
    try{
      val dialog = new TextInputDialog() {
        title = "Input StaffID"
        headerText = "Please enter your ID:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(id) =>
          if(Staff.isAccountsMember(id)){
            val orders: Array[Order] = Order.readInOrders()
            val ordersDP: Array[Order]= Order.printDispatchedOrders(orders)
            getOrderData.getOrderData(ordersDP)
          }
        case None     =>
      }
    }
    catch {
      case e: Exception =>
        new Alert(AlertType.Error) {
          title = "Error Message"
          headerText = "Incorrect user"
          contentText = "Please check your role"
        }.showAndWait()
    }
  }

  menuButton.ProductLevelManagement.onAction = (e: ActionEvent) => {
    if(Login.go || Login2.go){
      MainFace.changeScene(StockFace())
    }else{
      MainFace.changeScene(Login)
    }
  }

  menuButton.InputStock.onAction = (e: ActionEvent) => {
    if(Login.go || Login2.go){
      MainFace.changeScene(AddNewStockFace())
    }else{
      MainFace.changeScene(Login)
    }
  }

  menuButton.SingleProduct.onAction = (e: ActionEvent) => {
    if(Login.go || Login2.go){
      MainFace.changeScene(StockFace())
    }else{
      MainFace.changeScene(Login)
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