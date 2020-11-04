package com.deprac.model

import kotlin.math.abs
import kotlin.math.exp


data class Grid(
    var x: List<Double> = emptyList(),
    var y: List<Double> = emptyList(),
)

fun Grid.toPlot() = List(x.size){i -> Point(x[i], y[i])}


interface Method {
    val name: String
    val x0: Double
    val y0: Double
    val x_max: Double
    val N: Int

    val h: Double
        get() = (x_max - x0) / N

    fun solve(): Grid
}

interface AddonNumeric {
    fun step(x: Double, y: Double): Double

    fun localErrors(exact_vals: List<Double>): List<Double>

    fun globalErrors(exact_vals: List<Double>): List<Double>
}

val const: (Double, Double) -> Double = { x, y -> (y + 2*x - 1) / exp(x) }
val func: (Double) -> Double = { x -> -2*x + 1 }
val constMult: (Double) -> Double = { x -> exp(x) }
val deriv: (Double, Double) -> Double = { x, y -> 2*x + y - 3 }

class ExactMethod(
    _x0: Double,
    _y0: Double,
    _x_max: Double,
    _N: Int,
) : Method {
    override val x0 = _x0
    override val y0 = _y0
    override val x_max = _x_max
    override val N get() = _n

    var _n = _N

    override val name = "Exact"

    override fun solve(): Grid {
        val ys = mutableListOf<Double>()
        val C = const(x0, y0)
        for (i in 0..N) {
            val x = x0 + i * h
            ys.add(func(x) + C * constMult(x))
        }
        return Grid(y = ys).apply { x = getXs(x0, x_max, N) }
    }
}

fun getXs(x0: Double, xMax: Double, N: Int): List<Double> = List(N+1){
        i -> x0 + i * ( (xMax - x0) / N )
}

open class NumMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int) : Method, AddonNumeric {
    override val x0 = _x0
    override val y0 = _y0
    override val x_max = _x_max
    override val N get() = _n

    var _n = _N

    override val name: String
        get() = TODO("Not yet implemented")

    override fun solve(): Grid {
        val ys = mutableListOf(y0)
        for (i in 0 until N) {
            val x_cur = x0 + h * i
            ys.add(step(x_cur, ys.last()))
        }
        return Grid(y = ys).apply { x = getXs(x0, x_max, N) }
    }

    override fun step(x: Double, y: Double): Double {
        TODO("Not yet implemented")
    }

    override fun localErrors(exact_vals: List<Double>) =
        mutableListOf(0.0).also {
            run {
                for (i in 0 until N) {
                    val x = x0 + h * i
                    it.add(abs(exact_vals[i + 1] - step(x, exact_vals[i])))
                }
            }
        }

    override fun globalErrors(exact_vals: List<Double>) =
        mutableListOf<Double>().also {
            run {
                val approx_vals = solve()
                for (i in 0..N) {
                    it.add(abs(exact_vals[i] - approx_vals.y[i]))
                }
            }
        }
}

class EulerMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int) : NumMethod(_x0, _y0, _x_max, _N) {

    override val name: String
        get() = "Euler"

    override fun step(x: Double, y: Double): Double {
        return y + h * deriv(x, y)
    }
}

class ImprovedEulerMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int) : NumMethod(_x0, _y0, _x_max, _N) {

    override val name: String
        get() = "EulerImproved"

    override fun step(x: Double, y: Double): Double {
        return y + h * (deriv(x, y) + deriv(x + h, ownStep(x, y))) / 2
    }

    private fun ownStep(x: Double, y: Double) =
        y + h * deriv(x, y)
}

class RungeKuttaMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int) : NumMethod(_x0, _y0, _x_max, _N) {
    private fun k1(x: Double, y: Double) = deriv(x, y)
    private fun k2(x: Double, y: Double) = deriv(x + h / 2, y + h * k1(x, y) / 2)
    private fun k3(x: Double, y: Double) = deriv(x + h / 2, y + h * k2(x, y) / 2)
    private fun k4(x: Double, y: Double) = deriv(x + h, y + h * k3(x, y))

    override val name: String
        get() = "Runge"

    override fun step(x: Double, y: Double) =
        y + h * (k1(x, y) + 2 * k2(x, y) + 2 * k3(x, y) + k4(x, y)) / 6
}

data class Point(
    val x: Double,
    val y: Double,
)


enum class SelectedMethod {
    EXACT,
    EULER,
    EULER_IMP,
    RUNGE,
}

data class Line(
    val name: String,
    val points: List<Point>,
)

data class ChartPlot(
    val name: String?,
    val points: List<Point> = emptyList(),
)

