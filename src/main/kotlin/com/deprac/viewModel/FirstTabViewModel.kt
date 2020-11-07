package com.deprac.viewModel

import com.deprac.FirstTabEvent
import javafx.scene.chart.XYChart
import tornadofx.*

/**
 * ViewModel for FirsTab
 * It receives computed data from FirsTabEvent and update charts
 *
 * @property _approx ObservableList<Series<Number, Number>>
 * @property _lte ObservableList<Series<Number, Number>>
 */
class FirstTabViewModel: ViewModel(){
    val _approx = mutableListOf<XYChart.Series<Number, Number>>().asObservable()
    val _lte = mutableListOf<XYChart.Series<Number, Number>>().asObservable()

    init {
        // Subscribes to FirstTabEvent and update charts on receive
        subscribe<FirstTabEvent> {
            with(_approx){
                clear()
                setAll(it.approx.map {
                    XYChart.Series(it.name, it.points.map {
                        XYChart.Data(it.x as Number, it.y as Number)
                    }.asObservable())
                })
            }
            with(_lte){
                clear()
                setAll(it.lte.map {
                    XYChart.Series(it.name, it.points.map {
                        XYChart.Data(it.x as Number, it.y as Number)
                    }.asObservable())
                })
            }
        }
    }
}

