package com.deprac.view

import com.deprac.viewModel.FirstTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.*

/**
 *
 * @property model FirstTabViewModel
 * @property root VBox
 */
class FirstTab : View() {
    val model: FirstTabViewModel by inject()

    override val root = vbox {
        useMaxWidth = true
        linechart("approx", NumberAxis(), NumberAxis()) {
            createSymbols = false
            data = model._approx
        }
        linechart("LTE", NumberAxis(), NumberAxis()) {
            createSymbols = false
            data = model._lte
        }
        linechart("GTE", NumberAxis(), NumberAxis()) {
            createSymbols = false
            data = model._gte
        }
    }
}






