package com.deprac

import com.deprac.model.ChartPlot
import tornadofx.FXEvent

class FirstTabEvent(
        var approx: MutableList<ChartPlot> = mutableListOf(),
        var lte: List<ChartPlot> = emptyList(),
        var gte: List<ChartPlot> = emptyList(),
) : FXEvent()

class SecondTabEvent(
    var gte: List<ChartPlot> = emptyList(),
    var lte: List<ChartPlot> = emptyList(),
) : FXEvent()