package com.deprac.view

import tornadofx.*

/**
 * The Root of application views
 *
 * @property settingsPanel SettingsPanel
 * @property tabsPanel TabsPanel
 * @property root BorderPane
 */
class MainView : View("De_Practicum") {
    private val settingsPanel: SettingsPanel by inject()
    private val tabsPanel: TabsPanel by inject()

    override val root = borderpane {
        left = tabsPanel.root
        right = settingsPanel.root
    }
}
