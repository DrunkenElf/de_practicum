package com.deprac.viewModel

import com.deprac.SecondTabEvent
import javafx.scene.chart.XYChart
import tornadofx.ViewModel
import tornadofx.*

class SecondTabViewModel : ViewModel() {

    val gte = mutableListOf<XYChart.Series<Number, Number>>().asObservable()
    val lte = mutableListOf<XYChart.Series<Number, Number>>().asObservable()


    init {
        subscribe<SecondTabEvent> {
            with(lte){
                clear()
                setAll(it.lte.map {
                    XYChart.Series(it.name, it.points.map {
                        XYChart.Data(it.x as Number, it.y as Number)
                    }.asObservable())
                })
            }
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