package com.deprac.view

import com.deprac.viewModel.SecondTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.*

/**
 * The second tab in tabspanel which draws 2 linechars
 *
 * @property model SecondTabViewModel
 * @property root VBox
 */
class SecondTab : View() {
    val model: SecondTabViewModel by inject()

    override val root = vbox {
        linechart(
                "GTE",
                NumberAxis().apply { label = "n" },
                NumberAxis().apply { label = "gte" },
        ) {
            createSymbols = false
            data = model.gte
        }
        linechart(
                "LTE",
                NumberAxis().apply { label = "n" },
                NumberAxis().apply { label = "lte" },
        ) {
            createSymbols = false
            data = model.lte
        }
    }
}