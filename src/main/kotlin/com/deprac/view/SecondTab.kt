package com.deprac.view

import com.deprac.viewModel.SecondTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.View
import tornadofx.linechart
import tornadofx.vbox

class SecondTab : View() {

    val model: SecondTabViewModel by inject()

    override val root = vbox {
        linechart("gte", NumberAxis(), NumberAxis()){
            data = model.gte
        }
        linechart("lte", NumberAxis(), NumberAxis()){
            data = model.lte
        }
    }
}