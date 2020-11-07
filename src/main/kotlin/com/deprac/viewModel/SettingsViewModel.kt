package com.deprac.viewModel

import com.deprac.FirstTabEvent
import com.deprac.SecondTabEvent
import com.deprac.model.*
import tornadofx.*

/**
 * ViewModel for SettingsPanel
 *
 * Responsible for computing and sending data to
 * @see FirstTabViewModel
 * @see SecondTabViewModel
 *
 * @property x_0 SimpleDoubleProperty
 * @property y_0 SimpleDoubleProperty
 * @property x_max SimpleDoubleProperty
 * @property N SimpleIntegerProperty
 * @property n_min SimpleIntegerProperty
 * @property n_max SimpleIntegerProperty
 */
class SettingsViewModel : ItemViewModel<SettingsModel>() {
    val x_0 = bind(SettingsModel::x_0_prop)
    val y_0 = bind(SettingsModel::y_0_prop)
    val x_max = bind(SettingsModel::x_max_prop)
    val N = bind(SettingsModel::N_prop)
    val n_min = bind(SettingsModel::n_min_prop)
    val n_max = bind(SettingsModel::n_max_prop)

    fun onClick() {
        val exact = ExactMethod(x_0.value, y_0.value, x_max.value, N.value)
        val euler = EulerMethod(x_0.value, y_0.value, x_max.value, N.value)
        val impEuler = ImprovedEulerMethod(x_0.value, y_0.value, x_max.value, N.value)
        val runge = RungeKuttaMethod(x_0.value, y_0.value, x_max.value, N.value)

        val methods = listOf(euler, impEuler, runge)

        // fires Event to update data in FirstTab
        fire(FirstTabEvent(
                approx = methods.map {
                    ChartPlot(
                            name = it.name,
                            points = it.solve().toPlot()
                    )
                }.toMutableList().also { it.add(ChartPlot(exact.name, exact.solve().toPlot())) },
                lte = methods.map {
                    ChartPlot(
                            name = it.name,
                            points = Grid(getXs(it.x0, it.x_max, it.N), it.localErrors(exact.solve().y))
                                    .toPlot(),
                    )
                },
                gte = methods.map {
                    ChartPlot(
                            name = it.name,
                            points = Grid(getXs(it.x0, it.x_max, it.N), it.globalErrors(exact.solve().y))
                                    .toPlot(),
                    )
                }
        ))
        val ns = mutableListOf<Int>().also {
            for (i in n_min.value..n_max.value) {
                it.add(i)
            }
        }
        // Fires Event to change data in SecondTab
        fire(SecondTabEvent(
                gte = methods.map {
                    ChartPlot(
                            name = it.name,
                            points = Grid(
                                    ns.map { it.toDouble() },
                                    ns.map { index ->
                                        it.apply { _n = index }
                                                .globalErrors(exact.apply { _n = index }.solve().y)
                                                .maxOrNull()!!
                                    }).toPlot()
                    )
                },
                lte = methods.map {
                    ChartPlot(
                            name = it.name,
                            points = Grid(
                                    ns.map { it.toDouble() },
                                    ns.map { index ->
                                        it.apply { _n = index }
                                                .localErrors(exact.apply { _n = index }.solve().y)
                                                .maxOrNull()!!
                                    }).toPlot()
                    )
                },
        ))
    }
}