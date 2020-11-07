package com.deprac.view

import com.deprac.viewModel.SettingsViewModel
import tornadofx.*

/**
 * Performs user input and responsible for
 * triggering charts to rerender
 *
 * @property model SettingsViewModel
 * @property root Form
 */
class SettingsPanel : View() {
    val model: SettingsViewModel by inject()

    override val root = form {
        fieldset("Properties") {
            field("x_0") { textfield(model.x_0) }
            field("y_0") { textfield(model.y_0) }
            field("x_max") { textfield(model.x_max) }
            field("N") { textfield(model.N) }
        }
        fieldset {
            field("n_min") { textfield(model.n_min) }
            field("n_max") { textfield(model.n_max) }
        }
        fieldset {
            button("Calculate") {
                action { model.onClick() }
            }
        }
    }
    init {
        model.x_0.value = 1.0
        model.y_0.value = 2.0
        model.x_max.value = 10.0
        model.N.value = 10
        model.n_min.value = 10
        model.n_max.value = 100
        model.commit()
    }
}