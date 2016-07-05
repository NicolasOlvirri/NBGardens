import com.WOTS.system.{Menu, Order}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.event._
import scalafx.geometry.{Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{HBox, VBox}
import scalafx.Includes._

/**
  * Created by Yuan on 01/07/2016.
  */

object mainFace extends JFXApp {
  stage = new PrimaryStage {
    title = "NBGardenWOTS"
    centerOnScreen()
    height = 800
    width = 980
    scene = new Scene(){
      val PrintOrder = new Button("Print order details") //a button
      PrintOrder.maxWidth = 140
      PrintOrder.prefHeight = 50

      val UpdateOrder = new Button("Update an order status") //a button
      UpdateOrder.maxWidth = 140
      UpdateOrder.prefHeight = 50

      val StockManagement = new Button("Stock Management") //a button
      StockManagement.maxWidth = 140
      StockManagement.prefHeight = 50

      val InputStock = new Button("Input new stock") //a button
      InputStock.maxWidth = 140
      InputStock.prefHeight = 50

      val DispatchedOrders = new Button("Dispatched orders") //a button
      DispatchedOrders.maxWidth = 140
      DispatchedOrders.prefHeight = 50

      val SingleProduct = new Button("Single product") //a button
      SingleProduct.maxWidth = 140
      SingleProduct.prefHeight = 50

      val OrdersStatus = new Button("Orders Status") //a button
      OrdersStatus.maxWidth = 140
      OrdersStatus.prefHeight = 50

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

      val check = new Button("Check") //a button
      check.prefWidth = 140
      check.prefHeight = 50
      check.onAction = (e: ActionEvent) => {
        val orders1: Array[Order]= Array(Order.returnSingleOrder(text.text.apply().toString, orders))
        data.clear()
        data ++= orders1.toList
      }

      val update = new Button("Update") //a button
      update.prefWidth = 140
      update.prefHeight = 50
      update.onAction = (e: ActionEvent) => {
        try{
          val orders1: Array[Order]= Order.updateStatus(orders,text.text.apply().toString,toggle.selectedToggle.apply().getUserData.toString)
          data.clear()
          data ++= orders1.toList
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

      val text = new TextArea() //a button
      text.maxWidth = 140
      text.maxHeight = 50

      val textBox = new HBox(10, text, check)
      textBox.alignment = Pos.Center

      val menu = new VBox(19, PrintOrder, UpdateOrder, StockManagement, InputStock, DispatchedOrders, SingleProduct, OrdersStatus, SinglePurchase, PurchaseOrders, Exit)
      menu.alignment = Pos.Center

      var orders: Array[Order] = Order.readInOrders()
      var data = new ObservableBuffer[Order]

      def getOrderData(): Unit ={
        data.clear()
        data ++= orders.toList
      }
      getOrderData()

      var table = new TableView(data)
      val col1ForTable = new TableColumn[Order, String]("Order Status")
      col1ForTable.cellValueFactory = cdf => ObjectProperty(cdf.value.orderStatus)
      col1ForTable.prefWidth=100
      val col2ForTable = new TableColumn[Order, String]("Order ID")
      col2ForTable.prefWidth=100
      col2ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderID)
      val col3ForTable = new TableColumn[Order, String]("Staff ID")
      col3ForTable.prefWidth=100
      col3ForTable.cellValueFactory = cdf => StringProperty(cdf.value.staffID)
      val col4ForTable = new TableColumn[Order, String]("Customer Name")
      col4ForTable.prefWidth=110
      col4ForTable.cellValueFactory = cdf => StringProperty(cdf.value.customerName)
      val col5ForTable = new TableColumn[Order, String]("Order Date")
      col5ForTable.prefWidth=100
      col5ForTable.cellValueFactory = cdf => StringProperty(cdf.value.orderDate)
      val col6ForTable = new TableColumn[Order, String]("Address")
      col6ForTable.prefWidth=100
      col6ForTable.cellValueFactory = cdf => StringProperty(cdf.value.address)
      val col7ForTable = new TableColumn[Order, String]("Payment Method")
      col7ForTable.prefWidth=110
      col7ForTable.cellValueFactory = cdf => StringProperty(cdf.value.paymentMethod)

      table.columns ++= List(col1ForTable, col2ForTable, col3ForTable, col4ForTable, col5ForTable, col6ForTable, col7ForTable)

      val sph = new SplitPane(){
        autosize()
        items.addAll(textBox,table)
        dividerPositions=(0.10)
      }
      sph.setOrientation(Orientation.Vertical)

      val spv = new SplitPane(){
        autosize()
        items.addAll(menu,sph)
        dividerPositions=(0.15)
      }

      PrintOrder.onAction = (e: ActionEvent) => {
        try{
          textBox.children.removeAll(choose1,choose2,choose3,choose4,update)
          textBox.children.addAll(check)
          getOrderData()
        }
        catch {
          case e: Exception =>
        }
      }

      UpdateOrder.onAction = (e: ActionEvent) => {
        try{
          textBox.children.remove(check)
          textBox.children.addAll(choose1,choose2,choose3,choose4,update)
          getOrderData()
        }
        catch {
          case e: Exception =>
        }
      }

      root = spv
    }
  }

  def changeScene(scene: Scene): Unit = {
    stage.scene = scene
  }
}