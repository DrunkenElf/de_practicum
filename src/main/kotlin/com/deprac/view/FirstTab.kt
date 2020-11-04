package com.deprac.view

import com.deprac.viewModel.FirstTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.*

class FirstTab : View() {
    val model: FirstTabViewModel by inject()

    override val root = vbox {
        useMaxWidth = true
        linechart("approx", NumberAxis(), NumberAxis()) {
            data = model._approx
        }
        linechart("LTE", NumberAxis(), NumberAxis()) {
            data = model._lte
        }
        linechart("GTE", NumberAxis(), NumberAxis()) {
            data = model._gte
        }
    }
}






