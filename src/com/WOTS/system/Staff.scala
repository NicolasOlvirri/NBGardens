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

}
