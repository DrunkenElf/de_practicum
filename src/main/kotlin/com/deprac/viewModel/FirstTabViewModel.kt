package com.deprac.viewModel

import com.deprac.FirstTabEvent
import javafx.scene.chart.XYChart
import tornadofx.*

/*
* approx can contain all method
* lte - all except EXACT
* */
class FirstTabViewModel: ViewModel(){

    val _approx = mutableListOf<XYChart.Series<Number, Number>>().asObservable()
    val _lte = mutableListOf<XYChart.Series<Number, Number>>().asObservable()
    val _gte = mutableListOf<XYChart.Series<Number, Number>>().asObservable()


    init {
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
            with(_gte){
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

