package com.example.presentation.view.progress

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.roundToInt


abstract class BaseProgressView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    protected fun getPointNormalized(x: Float, y: Float): Point {
        return Point(getWidthNormalized(x).roundToInt(), getHeightNormalized(y).roundToInt())
    }

    protected fun getWidthNormalized(w: Float): Float {
        return w * width
    }

    protected fun getHeightNormalized(h: Float): Float {
        return h * height
    }

    protected fun getSubPath(path: Path, start: Float, end: Float): Path {
        val subPath = Path()
        val pathMeasure = PathMeasure(path, false)
        pathMeasure.getSegment(
            clamp(start) * pathMeasure.length,
            clamp(end) * pathMeasure.length,
            subPath,
            true
        )
        return subPath
    }

    protected fun getLengthOfPath(path: Path): Float {
        return PathMeasure(path, false).length
    }

    protected fun dpFromPx(px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    protected fun pxFromDp(dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    protected fun clamp(value: Float): Float {
        return when {
            value < 0f -> 0f
            value > 1f -> 1f
            else -> value
        }
    }
}