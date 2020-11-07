package com.deprac.model

import kotlin.math.abs
import kotlin.math.pow

// Wrapper class for Points
data class Grid(
        var x: List<Double> = emptyList(),
        var y: List<Double> = emptyList(),
)

// Extension fun of Grid to get list<Point>
fun Grid.toPlot() = List(x.size) { i -> Point(x[i], y[i]) }

// Parent for all methods
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

// Add several funs for NumMethod
interface AddonNumeric {
    fun step(x: Double, y: Double): Double

    fun localErrors(exact_vals: List<Double>): List<Double>

    fun globalErrors(exact_vals: List<Double>): List<Double>
}

/**
 * y = x^4 + x^2 * (y_0 - x_0^4) / x_0 ^2
 *
 * @param C - (y_0 - x_0^4) / x_0 ^2
 * @param func - x^4
 * @param constMult - x^2
 * @param deriv - 2x^3 + 2y/x
 */
val C: (Double, Double) -> Double = { x, y -> (y - x.pow(4)) / (x.pow(2)) }
val func: (Double) -> Double = { x -> x.pow(4) }
val constMult: (Double) -> Double = { x -> x.pow(2) }
val deriv: (Double, Double) -> Double = { x, y -> 2 * x.pow(3) + 2 * y / x }


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

    /**
     * @return Grid - exact_values
     */
    override fun solve(): Grid {
        val ys = mutableListOf<Double>()
        val C = C(x0, y0)
        for (i in 0..N) {
            val x = x0 + i * h
            ys.add(func(x) + C * constMult(x))
        }
        return Grid(y = ys).apply { x = getXs(x0, x_max, N) }
    }
}

// returns List<Double> - Points of x-axis
fun getXs(x0: Double, xMax: Double, N: Int): List<Double> = List(N + 1) { i ->
    x0 + i * ((xMax - x0) / N)
}

// Parent for Euler, EulerImproved and Runge-Kutta methods
open class NumMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int) : Method, AddonNumeric {
    override val x0 = _x0
    override val y0 = _y0
    override val x_max = _x_max
    override val N get() = _n
    var _n = _N
    override val name: String
        get() = TODO("used to override name attribute")

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
    } //it's empty

    override fun localErrors(exact_vals: List<Double>) = mutableListOf(0.0).also {
        run {
            for (i in 0 until N) {
                val x = x0 + h * i
                it.add(abs(exact_vals[i + 1] - step(x, exact_vals[i])))
            }
        }
    }

    override fun globalErrors(exact_vals: List<Double>) = mutableListOf<Double>().also {
        run {
            val approx_vals = solve()
            for (i in 0..N) {
                it.add(abs(exact_vals[i] - approx_vals.y[i]))
            }
        }
    }
}

class EulerMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int)
    : NumMethod(_x0, _y0, _x_max, _N) {

    override val name: String
        get() = "Euler"

    override fun step(x: Double, y: Double): Double {
        return y + h * deriv(x, y)
    }
}

class ImprovedEulerMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int)
    : NumMethod(_x0, _y0, _x_max, _N) {

    override val name: String
        get() = "EulerImproved"

    override fun step(x: Double, y: Double): Double {
        return y + h * (deriv(x, y) + deriv(x + h, ownStep(x, y))) / 2
    }

    private fun ownStep(x: Double, y: Double) =
            y + h * deriv(x, y)
}

class RungeKuttaMethod(_x0: Double, _y0: Double, _x_max: Double, _N: Int)
    : NumMethod(_x0, _y0, _x_max, _N) {

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


data class ChartPlot(
        val name: String?,
        val points: List<Point> = emptyList(),
)

