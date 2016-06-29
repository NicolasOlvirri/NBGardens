//package com.WOTS.system
//
//import java.io.{File, FileWriter}
//
//import scala.collection._
//import scala.collection.mutable._
//import scala.io.StdIn._
//
//object Supplier{
//
//  def addSupplier(): Unit ={
//
//    //access suppliers CSV
//    val bufferedSource = io.Source.fromFile("src\\supplierList.csv")
//    val currentCSV: ArrayBuffer[String] = new ArrayBuffer[String]
//
//    //add information to variables
//    println("Please give the information of the new supplier: ")
//    println("Enter the suppliers ID: ")
//    val supplierID = readLine()
//    println("Enter the suppliers name: ")
//    val supplierName = readLine()
//    //Date should automatically be entered
//    println("Enter the suppliers address: ")
//    val address = readLine()
//    println("Enter the suppliers phone number: ")
//    val phoneNumber = readLine()
//
//    println("The supplier has been added.")
//
//    //create array holding all information given
//    val newSupplier = supplierID.toUpperCase() + ", " +
//      supplierName.toLowerCase().split(' ').map(_.trim).map(_.capitalize).mkString(" ") + ", " +
//      address.capitalize + ", " +
//      phoneNumber.capitalize
//
//    for(line <- bufferedSource.getLines()) {
//      currentCSV.append(line)
//    }
//
//    currentCSV.append(newSupplier)
//    val file = new FileWriter(new File("src\\supplierList.csv"))
//
//    //write new line in CSV holding new suppliers information
//    for(element <- currentCSV){
//      file.write(element + "\n")
//    }
//
//    file.close()
//  }
//
//  def addPurchaseOrder(stockLevels: mutable.HashMap[String, Int]): Unit ={
//
//
//  }
//}
//
//
////Calendar.getInstance().getTime + ", " +