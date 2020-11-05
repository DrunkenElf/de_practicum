package com.deprac.view

import com.deprac.viewModel.SecondTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.*

/**
 *
 * @property model SecondTabViewModel
 * @property root VBox
 */
class SecondTab : View() {
    val model: SecondTabViewModel by inject()

    override val root = vbox {
        linechart("gte", NumberAxis(), NumberAxis()){
            createSymbols = false
            data = model.gte
        }
        linechart("lte", NumberAxis(), NumberAxis()){
            createSymbols = false
            data = model.lte
        }
    }
}