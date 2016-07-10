package com.WOTS.gui

import com.WOTS.system.PurchaseOrder

import scalafx.collections.ObservableBuffer

/**
  * Created by Yuan on 07/07/2016.
  */
class getPurchaseData {
  var purchases: Array[PurchaseOrder] = PurchaseOrder.readInPurchases()
  var data = new ObservableBuffer[PurchaseOrder]
  def getPurchaseData(purchase: Array[PurchaseOrder]): Unit ={
    purchases = purchase
    data.clear()
    data ++= purchases.toList
  }
}
