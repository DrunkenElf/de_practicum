package com.deprac.viewModel

import com.deprac.SecondTabEvent
import javafx.scene.chart.XYChart
import tornadofx.*

/**
 * ViewModel for SecondTab
 * Responsible for updating charts
 *
 * @property gte ObservableList<Series<Number, Number>>
 */
class SecondTabViewModel : ViewModel() {
    val gte = mutableListOf<XYChart.Series<Number, Number>>().asObservable()

    init {
        // Subscribe to SecondTabEvent and update data on receive
        subscribe<SecondTabEvent> {
            with(gte){
                clear()
                setAll(it.gte.map {
                    XYChart.Series(it.name, it.points.map {
                        XYChart.Data(it.x as Number, it.y as Number)
                    }.asObservable())
                })
            }
        }
    }
}