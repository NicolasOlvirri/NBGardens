package com.WOTS.gui

import com.WOTS.gui
import com.WOTS.system.{Menu, _}

import scalafx.Includes._
import scalafx.event._
import scalafx.geometry.{Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
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

  tableP.setProductTable()

  var table = tableP.table

  val spv = new SplitPane(){
    autosize()
    items.addAll(menu,table)
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
    try{
      val dialog = new TextInputDialog() {
        title = "Input"
        headerText = "Please enter the ID of the purchase order:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(id) =>
          getPurchaseData.purchases = PurchaseOrder.orderReceived(getPurchaseData.purchases, id)
          getPurchaseData.getPurchaseData(getPurchaseData.purchases)
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

  menuButton.SinglePurchase.onAction = (e: ActionEvent) => {
    try {
      val dialog = new TextInputDialog() {
        title = "Input"
        headerText = "Please enter the ID of the purchase order:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(id) =>
          val purchases: Array[PurchaseOrder] = Array(PurchaseOrder.searchSingleOrder(getPurchaseData.purchases, id))
          getPurchaseData.getPurchaseData(purchases)
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

  menuButton.PurchaseOrders.onAction = (e: ActionEvent) => {
    MainFace.changeScene(PurchaseFace())
  }

  root = spv
}
