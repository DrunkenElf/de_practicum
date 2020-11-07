package com.deprac.model


import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty

/**
 * Model for SettingsViewModel
 *
 * @property x_0_prop SimpleDoubleProperty
 * @property y_0_prop SimpleDoubleProperty
 * @property x_max_prop SimpleDoubleProperty
 * @property N_prop SimpleIntegerProperty
 * @property n_min_prop SimpleIntegerProperty
 * @property n_max_prop SimpleIntegerProperty
 * @constructor
 */
class SettingsModel(
        x_0: Double? = null, y_0: Double? = null,
        x_max: Double? = null, N: Int? = null,
        n_min: Int? = null, n_max: Int? = null,
) {
    val x_0_prop = SimpleDoubleProperty(this, "x_0", x_0 ?: 0.0)

    val y_0_prop = SimpleDoubleProperty(this, "y_0", y_0 ?: 0.0)

    val x_max_prop = SimpleDoubleProperty(this, "x_max", x_max ?: 0.0)

    val N_prop = SimpleIntegerProperty(this, "N", N ?: 0)

    val n_min_prop = SimpleIntegerProperty(this, "n_min", n_min ?: 0)

    val n_max_prop = SimpleIntegerProperty(this, "n_max", n_max ?: 0)
}