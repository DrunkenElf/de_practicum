package com.deprac

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        tabPane {
            padding = box(1.px, 1.px, 0.px, 1.px)
        }
        tab {
            prefWidth = 490.px
        }
        tabContentArea {
            prefHeight = 900.px
        }

    }
}