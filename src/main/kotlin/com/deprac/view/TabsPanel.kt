package com.deprac.view

import tornadofx.*

/**
 * This is TabsPanel which holds all our tabs
 *
 * @property firstTab FirstTab
 * @property secondTab SecondTab
 * @property root TabPane
 */
class TabsPanel : View() {
    private val firstTab: FirstTab by inject()
    private val secondTab: SecondTab by inject()

    override val root = tabpane {
        useMaxWidth = true

        tab("Solutions") {
            add(firstTab)
        }
        tab("Convergence") {
            add(secondTab)
        }
    }
}