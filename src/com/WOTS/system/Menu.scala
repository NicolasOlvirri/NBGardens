package com.WOTS.system

import scala.io.StdIn._

object Menu{

  //menu for the user to be able to choose what they would like to do
  def mainMenu(): String ={
    println("Please enter one of the following:")
    println("1: Print order details")
    println("2: Update an order's status")
    println("4: Add a new supplier")
    println("5: Print current stock levels")
    println("6: Remove an item with certain quantity from stock")
    println("If you would like to exit, enter 0.")
    userInput()
  }

  def printOrderMenu(): String={
    println("1: Print all available orders")
    println("2: Print a single customer order using the orders ID")
    userInput()
  }

  //ask for user input
  def userInput(): String ={
    val userInput = readLine()
    userInput
  }

}