package com.WOTS.gui

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

/**
  * Created by Administrator on 07/07/2016.
  */

object MainFace extends JFXApp {
  stage = new PrimaryStage {
    title = "NBGardenWOTS"
    centerOnScreen()
    height = 800
    width = 980
    scene = OrderFace()
  }

  def changeScene(scene: Scene): Unit = {
    stage.scene = scene
  }
}
