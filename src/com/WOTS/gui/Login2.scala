package com.WOTS.gui

import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{HBox, VBox}

/**
  * Created by Yuan on 07/07/2016.
  */
object Login2 extends Scene {

  val ilabel = new Label("Employee ID:")
  val etextField = new TextField() {promptText = "Employee ID"}
  etextField.maxWidth=200

  val plabel = new Label("Password:")
  val passwordField = new PasswordField() {promptText = "Enter Password"}
  passwordField.maxWidth=200

  val Login = new Button("Login")
  val cancel = new Button("Cancel")

  var go = false

  Login.onAction = (event: ActionEvent) => {
    if(etextField.text.apply().isEmpty || passwordField.text.apply().isEmpty) {
      new Alert(AlertType.Error) {
        title = "Error Message"
        headerText = "Incorrect username/password!"
        contentText = "Please type the correct username/password"
        etextField.text.value = ""
        passwordField.text.value = ""
      }.showAndWait()
    }
    else {
      val staffId = etextField.text.apply().toString
      val password = passwordField.text.apply().toString

      if (staffId=="rootS" && password=="rootS"){
        go = true
        MainFace.changeScene(PurchaseFace())
      }else{
        new Alert(AlertType.Error) {
          title = "Error Message"
          headerText = "Incorrect username/password!"
          contentText = "Please type the correct username/password"
          etextField.text.value = ""
          passwordField.text.value = ""
        }.showAndWait()
      }
    }
  }

  cancel.onAction = (event: ActionEvent) => {
    MainFace.changeScene(OrderFace())
  }

  // Request focus on the username field by default.
  Platform.runLater(etextField.requestFocus())

  val hBox = new HBox(14,cancel, Login)
  hBox.alignment = Pos.Center

  val loginUI = new VBox(7, ilabel, etextField, plabel, passwordField, hBox)
  loginUI.alignment = Pos.Center
  root = loginUI
}
