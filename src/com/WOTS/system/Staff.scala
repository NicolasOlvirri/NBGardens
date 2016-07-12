package com.WOTS.system

/**
  * Created by Administrator on 30/06/2016.
  */
case class Staff(staffID: String, staffName: String, staffRole: String, username: String, password: String) {}

object Staff{

  var staff = Array(
    new Staff("0001", "Nick", "Warehouse Worker", "Admin", "Admin"),
    new Staff("0002", "Luke", "Manager", "Admin", "Admin"),
    new Staff("0003", "Noob", "Accounts", "Admin", "Admin")
  )

  //given the ID of a staff member, return true if the member is part of accounts
  def isAccountsMember(staffID: String): Boolean ={
    var isAccountsMem = false

    def findStaff(staffArray: Array[Staff]): Boolean ={
      if(staffArray.isEmpty){}

      if (staffID == staffArray.head.staffID){
        if(staffArray.head.staffRole == "Accounts"){
          isAccountsMem = true
          isAccountsMem
        }
        else {
          isAccountsMem
        }
      }
      else{
        findStaff(staffArray.tail)
      }
    }
    findStaff(staff)
  }

  def findStaffMember(staffID: String): Boolean ={
    var isMember: Boolean = false
    def findStaff(staffMembers: Array[Staff]): Boolean ={
      if(staffMembers.isEmpty){
        isMember
      }
      else if(staffID == staffMembers.head.staffID){
        isMember = true
        isMember
      }
      else{
        findStaff(staffMembers.tail)
      }
    }
    findStaff(staff)
  }

}
