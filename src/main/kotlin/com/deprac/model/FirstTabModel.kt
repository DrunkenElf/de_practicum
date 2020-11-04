package com.deprac.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import tornadofx.*


class FirstTabModel(
    approx: List<Line> = emptyList(),
    lte: List<Line> = emptyList(),
    isPopulated: Boolean = false,
) {
    val approx_prop = SimpleListProperty<Line>(FXCollections.observableList(approx))
    var approx by approx_prop

    val lte_prop = SimpleListProperty<Line>(this, "lte", lte.asObservable())
    var lte by lte_prop

    val isPop_prop = SimpleBooleanProperty(this, "isPopulated", isPopulated)
    var isPopulated by isPop_prop

}



