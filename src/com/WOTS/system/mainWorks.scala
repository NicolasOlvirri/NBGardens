package com.WOTS.system

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.text.{Font, FontWeight}
import scalafx.Includes._
import scalafx.event._
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.paint.Color

/**
  * Created by Yuan on 01/07/2016.
  */
case class mainWorks() extends Scene {

  val Print = new Button("Print") //a button
  Print.prefWidth = 140
  Print.prefHeight = 150
  val toDoList = new Button("Orders need to do") //a button
  toDoList.prefWidth = 140
  toDoList.prefHeight = 50
  val doingList = new Button("Orders in progress") //a button
  doingList.prefWidth = 140
  doingList.prefHeight = 50
  val doneList = new Button("Orders are Done") //a button
  doneList.prefWidth = 140
  doneList.prefHeight = 50
  val storehouse = new Button("Store") //a button
  storehouse.prefWidth = 50
  storehouse.prefHeight = 150

  val labelB = new Label("If you have done an order please chick on the \"Orders in progess\"button"){
    font = Font.font(null, FontWeight.Bold, 12)
  }
  labelB.setTextFill(Color.Brown)
  val label = new Label("Welcome")
  val label1 = new Label("_") {
    font = Font.font(null, FontWeight.Bold, 18)
  }
  val label2 = new Label("Please enter your ID before do anything. Your Id:") {
    font = Font.font(null, FontWeight.Bold, 16)
  }
  val label3 = new Label("Please enter the order ID you wish to dealing with:") {
    font = Font.font(null, FontWeight.Bold, 16)
  }
  label1.prefHeight = 50
  label2.prefHeight = 50
  label3.prefHeight = 50
  labelB.prefHeight = 50

  var textAreaForStaffID = new TextArea("") //an area for enter staffID
  textAreaForStaffID.prefHeight = 50
  textAreaForStaffID.prefWidth = 200
  var textAreaForOrderID = new TextArea("") //an area for enter OrderID
  textAreaForOrderID.prefHeight = 50
  textAreaForOrderID.prefWidth = 200

  val mainContents = new BorderPane {autosize()}

  val mainContentsTop = new BorderPane{autosize()}

  val topRightGridPanel = new GridPane{
    autosize()
    gridLinesVisible = true
    val colInfo = new ColumnConstraints(minWidth = 140, prefWidth = 140, maxWidth = 140)
    columnConstraints.add(colInfo)
    GridPane.setConstraints(toDoList, 0, 0)
    GridPane.setConstraints(doingList, 0, 1)
    GridPane.setConstraints(doneList, 0, 2)
  }
  topRightGridPanel.children.add(toDoList)
  topRightGridPanel.children.add(doingList)
  topRightGridPanel.children.add(doneList)

  val topRightBorderPane = new BorderPane{

    autosize()
  }
  topRightBorderPane.right = storehouse
  topRightBorderPane.center = topRightGridPanel
  topRightBorderPane.left = Print

  val topCenterGridPanel = new GridPane() {

    autosize()
    GridPane.setConstraints(textAreaForStaffID, 0, 0)
    GridPane.setConstraints(label1, 0, 1)
    GridPane.setConstraints(textAreaForOrderID, 0, 2)
  }
  topCenterGridPanel.children.add(textAreaForStaffID)
  topCenterGridPanel.children.add(label1)
  topCenterGridPanel.children.add(textAreaForOrderID)


  val topLeftGridPanel = new GridPane() {

    autosize()
    GridPane.setConstraints(label2, 0, 0)
    GridPane.setConstraints(labelB, 0, 1)
    GridPane.setConstraints(label3, 0, 2)
  }
  topLeftGridPanel.children.add(label2)
  topLeftGridPanel.children.add(labelB)
  topLeftGridPanel.children.add(label3)

  mainContentsTop.right = topRightBorderPane
  mainContentsTop.center = topCenterGridPanel
  mainContentsTop.left = topLeftGridPanel

  mainContents.top = mainContentsTop
  mainContents.bottom = label

  root = mainContents

  storehouse.onAction = (e: ActionEvent) => {

  }

  Print.onAction = (e: ActionEvent) => {
  }

  toDoList.onAction = (e: ActionEvent) => {

  }

  doingList.onAction = (e: ActionEvent) => {

  }

  doneList.onAction = (e: ActionEvent) => {

  }
}
