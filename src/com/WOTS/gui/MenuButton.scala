package com.WOTS.gui

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, SplitPane}
import scalafx.scene.layout.VBox

/**
  * Created by Yuan on 07/07/2016.
  */
class MenuButton {
  val PrintOrder = new Button("Print order details") //a button
  PrintOrder.maxWidth = 140
  PrintOrder.prefHeight = 50

  val UpdateOrder = new Button("Update an order status") //a button
  UpdateOrder.maxWidth = 140
  UpdateOrder.prefHeight = 50

  val ProductLevelManagement = new Button("Product Level Manag.") //a button
  ProductLevelManagement.maxWidth = 140
  ProductLevelManagement.prefHeight = 50

  val InputStock = new Button("Input new stock") //a button
  InputStock.maxWidth = 140
  InputStock.prefHeight = 50

  val DispatchedOrders = new Button("Dispatched orders") //a button
  DispatchedOrders.maxWidth = 140
  DispatchedOrders.prefHeight = 50

  val SingleProduct = new Button("Single product") //a button
  SingleProduct.maxWidth = 140
  SingleProduct.prefHeight = 50

  val PurchaseStatus = new Button("Purchase Status") //a button
  PurchaseStatus.maxWidth = 140
  PurchaseStatus.prefHeight = 50

  val SinglePurchase = new Button("Single purchase order") //a button
  SinglePurchase.maxWidth = 140
  SinglePurchase.prefHeight = 50

  val PurchaseOrders = new Button("Purchase orders") //a button
  PurchaseOrders.maxWidth = 140
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
