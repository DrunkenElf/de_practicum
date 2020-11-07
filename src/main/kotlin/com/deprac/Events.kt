package com.deprac

import com.deprac.model.ChartPlot
import tornadofx.*

/**
 * Connection between SettingsTab and FirstTab
 *
 * @property approx MutableList<ChartPlot>
 * @property lte List<ChartPlot>
 * @property gte List<ChartPlot>
 * @constructor
 */
class FirstTabEvent(
        var approx: MutableList<ChartPlot> = mutableListOf(),
        var lte: List<ChartPlot> = emptyList(),
        var gte: List<ChartPlot> = emptyList(),
) : FXEvent()

/**
 * Connection between SettingsTab and SecondTab
 *
 * @property gte List<ChartPlot>
 * @property lte List<ChartPlot>
 * @constructor
 */
class SecondTabEvent(
        var gte: List<ChartPlot> = emptyList(),
        var lte: List<ChartPlot> = emptyList(),
) : FXEvent()