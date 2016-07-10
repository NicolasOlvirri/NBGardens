package com.WOTS.gui

import com.WOTS.system.Product

import scalafx.collections.ObservableBuffer

/**
  * Created by Administrator on 06/07/2016.
  */
class getStockData {
  var products: Array[Product] = Product.readInProducts()
  var data = new ObservableBuffer[Product]
  def getStockData(product: Array[Product]): Unit ={
    products = product
    data.clear()
    data ++= products.toList
  }
}
