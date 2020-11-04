package com.deprac.model


import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class SettingsModel(
    x_0: Double? = null, y_0: Double? = null,
    x_max: Double? = null, N: Int? = null,
    n_min: Int? = null, n_max: Int? = null,
) {
    val x_0_prop = SimpleDoubleProperty(this, "x_0", x_0 ?: 0.0)
    var x_0 by x_0_prop

    val y_0_prop = SimpleDoubleProperty(this, "y_0", y_0 ?: 0.0)
    var y_0 by y_0_prop

    val x_max_prop = SimpleDoubleProperty(this, "x_max", x_max ?: 0.0)
    var x_max by x_max_prop

    val N_prop = SimpleIntegerProperty(this, "N", N ?: 0)
    var N by N_prop

    val n_min_prop = SimpleIntegerProperty(this, "n_min", n_min ?: 0)
    var n_min by n_min_prop

    val n_max_prop = SimpleIntegerProperty(this, "n_max", n_max ?: 0)
    var n_max by n_max_prop

}