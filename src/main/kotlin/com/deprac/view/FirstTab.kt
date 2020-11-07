package com.deprac.view

import com.deprac.viewModel.FirstTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.*

/**
 * First tab of our tabspanel which draws 3 linecharts
 *
 * @property model FirstTabViewModel
 * @property root VBox
 */
class FirstTab : View() {
    val model: FirstTabViewModel by inject()

    override val root = vbox {
        useMaxWidth = true
        linechart("Methods", NumberAxis().apply { label = "x" },
                NumberAxis().apply { label = "y" }) {
            createSymbols = false
            data = model._approx
        }
        linechart("LTE", NumberAxis().apply { label = "x" },
                NumberAxis().apply { label = "lte" }) {
            createSymbols = false
            data = model._lte
        }
        linechart("GTE", NumberAxis().apply { label = "x" },
                NumberAxis().apply { label = "gte" }) {
            createSymbols = false
            data = model._gte
        }
    }
}






