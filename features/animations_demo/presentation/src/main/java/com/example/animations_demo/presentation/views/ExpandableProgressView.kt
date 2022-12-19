package com.example.animations_demo.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class ExpandableProgressView : BaseProgressView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var progressWidth: Float by Delegates.observable(10f) { _, _, _ ->
        invalidate()
    }

    var progressBackgroundWidth: Float by Delegates.observable(5f) { _, _, _ ->
        invalidate()
    }

    var endOffset: Float by Delegates.observable(0f) { _, _, _ ->
        invalidate()
    }
    var progress: Float by Delegates.observable(0.5f) { _, _, _ ->
        invalidate()
    }
    var radius: Float by Delegates.observable(20f) { _, _, _ ->
        invalidate()
    }

    var progressColor: Int by Delegates.observable(Color.RED) { _, _, _ ->
        invalidate()
    }
    var progressBackgroundColor: Int by Delegates.observable(Color.BLUE) { _, _, _ ->
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        val pLine = object : Paint() {
            init {
                style = Style.STROKE
                isAntiAlias = true
                strokeWidth = pxFromDp(progressWidth)
                color = progressColor
                strokeJoin = Join.ROUND
                strokeCap = Cap.ROUND
            }
        }

        val pBg = object : Paint() {
            init {
                style = Style.STROKE
                isAntiAlias = true
                strokeWidth = pxFromDp(progressBackgroundWidth)
                color = progressBackgroundColor
                strokeJoin = Join.ROUND
                strokeCap = Cap.ROUND
            }
        }

        val radius = pxFromDp(radius)
        val radiusWithStrokeWidth = radius + pLine.strokeWidth / 2f

        val p = Path()
        val start = Point(
            radiusWithStrokeWidth.roundToInt(),
            (getHeightNormalized(0.5f) + radiusWithStrokeWidth).roundToInt()
        )
        val end = Point(
            getWidthNormalized(1f).roundToInt() - pLine.strokeWidth.roundToInt() / 2,
            (getHeightNormalized(0.5f) + radiusWithStrokeWidth).roundToInt()
        )

        p.reset()
        p.moveTo(start.x.toFloat(), start.y.toFloat())
        p.arcTo(
            RectF(start.x - radius, start.y - radius * 2, start.x + radius, start.y.toFloat()),
            90f,
            -359f
        )
        val cicleLength = getLengthOfPath(p)
        p.lineTo(end.x.toFloat(), end.y.toFloat())
        val fullLength = getLengthOfPath(p)


        val circlePart = cicleLength / fullLength
        val linePart = (fullLength - cicleLength) / fullLength

        val fullProgressPart = circlePart * endOffset + linePart * (1 - endOffset)
        val endSubPath = 1 - endOffset * linePart
        val startSubPath = 1 - (endOffset * linePart) - fullProgressPart * progress
        val startSubPathBg = 1 - (endOffset * linePart) - fullProgressPart

        canvas.drawPath(getSubPath(p, startSubPathBg, endSubPath), pBg)
        canvas.drawPath(getSubPath(p, startSubPath, endSubPath), pLine)
    }

}