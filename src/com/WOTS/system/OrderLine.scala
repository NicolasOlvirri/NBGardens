package com.WOTS.system

/**
  * Created by Administrator on 28/06/2016.
  */
//used to store product ID with quantity purchased in customer order form
case class OrderLine(productID: String, quantity: Int) {}

object OrderLine{}
