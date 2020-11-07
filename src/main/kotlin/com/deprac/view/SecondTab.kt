package com.deprac.view

import com.deprac.viewModel.SecondTabViewModel
import javafx.scene.chart.NumberAxis
import tornadofx.*

/**
 * The second tab in tabspanel which draws 1 linechars
 *
 * @property model SecondTabViewModel
 * @property root VBox
 */
class SecondTab : View() {
    val model: SecondTabViewModel by inject()

    override val root = vbox {
        useMaxHeight = true
        useMaxWidth = true
        linechart(
                "GTE",
                NumberAxis().apply { label = "n" },
                NumberAxis().apply { label = "gte" },
        ) {
            createSymbols = false
            prefHeight = 900.0
            data = model.gte
        }
    }
}