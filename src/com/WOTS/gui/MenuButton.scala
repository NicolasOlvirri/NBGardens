package com.WOTS.gui

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, SplitPane}
import scalafx.scene.layout.VBox
import scalafx.scene.text.{Font, FontWeight}

/**
  * Created by Yuan on 07/07/2016.
  */
class MenuButton {
  val PrintOrder = new Button("Print order details") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  PrintOrder.maxWidth = 220
  PrintOrder.prefHeight = 50

  val UpdateOrder = new Button("Update an order status") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  UpdateOrder.maxWidth = 220
  UpdateOrder.prefHeight = 50

  val ProductLevelManagement = new Button("Product Level Manag.") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  ProductLevelManagement.maxWidth = 220
  ProductLevelManagement.prefHeight = 50

  val InputStock = new Button("Input new stock") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  InputStock.maxWidth = 220
  InputStock.prefHeight = 50

  val DispatchedOrders = new Button("Dispatched orders") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  DispatchedOrders.maxWidth = 220
  DispatchedOrders.prefHeight = 50

  val SingleProduct = new Button("Single product") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  SingleProduct.maxWidth = 220
  SingleProduct.prefHeight = 50

  val PurchaseStatus = new Button("Update a Purchase Status") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  PurchaseStatus.maxWidth = 220
  PurchaseStatus.prefHeight = 50

  val SinglePurchase = new Button("Single purchase order") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  SinglePurchase.maxWidth = 220
  SinglePurchase.prefHeight = 50

  val PurchaseOrders = new Button("Purchase orders") {
    font = Font.font(null, FontWeight.Bold, 16)
  }//a button
  PurchaseOrders.maxWidth = 220
  PurchaseOrders.prefHeight = 50

  val Exit = new Button("Exit") //a button
  Exit.maxWidth = 140
  Exit.prefHeight = 50
  Exit.onAction = (e: ActionEvent) => {
    sys.exit
  }

  val sp1 = new SplitPane()
  val sp2 = new SplitPane()
  val sp3 = new SplitPane()

  val menu = new VBox(19, PrintOrder, UpdateOrder, DispatchedOrders, sp1, ProductLevelManagement, SingleProduct, InputStock, sp2, PurchaseStatus, PurchaseOrders, SinglePurchase, sp3, Exit)
  menu.alignment = Pos.Center
}
