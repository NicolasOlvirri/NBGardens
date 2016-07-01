package com.WOTS.system

import scala.io.StdIn._

object Menu{

  //menu for the user to be able to choose what they would like to do
  def mainMenu(): String ={
    println("Please enter one of the following:")
    println("1: Print order details")
    println("2: Update an order's status")
    println("3: Increment or decrement stock levels")
    println("4: Input new stock")
    println("5: Print all dispatched orders")
    println("6: Print details for a single product")
    println("7: Update purchase order status to received, and update stock")
    println("8: Print details for a single purchase order")
    println("9: Print details for received purchase orders")
    println("If you would like to exit, enter 0.")
    userInput()
  }

  def printOrderMenu(): String={
    println("1: Print all available orders")
    println("2: Print a single customer order using the orders ID")
    userInput()
  }

  def updateOrderMenu(): String ={
    println("1: Increment Stock")
    println("2: Decrement Stock")
    userInput()
  }

  //ask for user input
  def userInput(): String ={
    val userInput = readLine()
    userInput
  }

}