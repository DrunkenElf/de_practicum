package com.deprac.view

import com.deprac.viewModel.FirstTabViewModel
import com.deprac.viewModel.SettingsViewModel
import javafx.scene.control.ToggleGroup
import tornadofx.*

class MainView : View("De_Practicum") {
    private val settingsPanel: SettingsPanel by inject()
    private val tabsPanel: TabsPanel by inject()


    override val root = borderpane {
        left = tabsPanel.root
        right = settingsPanel.root
    }
}

class TabsPanel: View(){
    private val firstTab: FirstTab by inject()
    private val secondTab: SecondTab by inject()

    override val root = tabpane {
        useMaxWidth = true

        tab("Solutions"){
            add(firstTab)
        }
        tab("GTE"){
            add(secondTab)
        }
    }

}

class SettingsPanel: View(){
    val model: SettingsViewModel by inject()
    val firstTabViewModel: FirstTabViewModel by inject()

    private val toggleGroup = ToggleGroup()

    override val root = form{
        fieldset("Properties"){
            field("x_0"){
                textfield(model.x_0)
            }
            field("y_0"){
                textfield(model.y_0)
            }
            field("x_max"){
                textfield(model.x_max)
            }
            field("N"){
                textfield(model.N)
            }
        }
        fieldset {
            field("n_min"){
                textfield(model.n_min)
            }
            field("n_max"){
                textfield(model.n_max)
            }
        }
        fieldset {
            button("Calculate"){
                action {
                    model.onClick()
                }
            }
        }
    }
    init {
        model.x_0.value = 1.0
        model.y_0.value = 1.0
        model.x_max.value = 7.0
        model.N.value = 10
        model.n_min.value = 10
        model.n_max.value = 100
        model.commit()
    }
}