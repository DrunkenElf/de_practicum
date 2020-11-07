package com.deprac

import com.deprac.model.ChartPlot
import tornadofx.*

/**
 * Connection between SettingsTab and FirstTab
 *
 * @property approx MutableList<ChartPlot>
 * @property lte List<ChartPlot>
 */
class FirstTabEvent(
        var approx: MutableList<ChartPlot> = mutableListOf(),
        var lte: List<ChartPlot> = emptyList(),
) : FXEvent()

/**
 * Connection between SettingsTab and SecondTab
 *
 * @property gte List<ChartPlot>
 */
class SecondTabEvent(
        var gte: List<ChartPlot> = emptyList(),
) : FXEvent()