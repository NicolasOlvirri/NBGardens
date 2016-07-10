package com.WOTS.gui

import com.WOTS.system.Order

import scalafx.collections.ObservableBuffer

/**
  * Created by Yuan on 06/07/2016.
  */
class getOrderData {
  var orders: Array[Order] = Order.readInOrders()
  var data = new ObservableBuffer[Order]
  def getOrderData(order: Array[Order]): Unit ={
    orders = order
    data.clear()
    data ++= orders.toList
  }
}
