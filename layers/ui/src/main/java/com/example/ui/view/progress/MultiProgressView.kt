package com.example.ui.view.progress

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import kotlin.properties.Delegates

open class MultiProgressView : BaseProgressView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    protected open val strokeCap = Paint.Cap.ROUND

    var progresses: List<Progress> by Delegates.observable(listOf()) { _, _, _ ->
        invalidate()
    }
    var ovelaid: Boolean by Delegates.observable(true) { _, _, _ ->
        invalidate()
    }
    var reverse: Boolean by Delegates.observable(false) { _, _, _ ->
        invalidate()
    }

    fun setHeight(heightDp: Float) {
        layoutParams.height = pxFromDp(heightDp).toInt()
        layoutParams = layoutParams
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        val mStrokeCap = strokeCap
        var lastEndX = 0
        for (progress in progresses) {
            val pLine = object : Paint() {
                init {
                    style = Style.STROKE
                    isAntiAlias = true
                    strokeWidth = height.toFloat()
                    color = progress.color
                    strokeJoin = Join.ROUND
                    strokeCap = mStrokeCap
//                    xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
                }
            }
            val p = Path()
            val radius = (pLine.strokeWidth / 2f).toInt()
            val start = Point(
                if (ovelaid) radius else lastEndX,
                getHeightNormalized(0.5f).toInt()
            )
            val end = Point(
                getWidthNormalized(
                    progress.progress / 100f
                ).toInt() - radius,
                getHeightNormalized(0.5f).toInt()
            )
            lastEndX = end.x + height

            p.reset()
            if (reverse) {
                p.moveTo(width - end.x.toFloat(), height - end.y.toFloat())
                p.lineTo(width - start.x.toFloat(), height - start.y.toFloat())
            } else {
                p.moveTo(start.x.toFloat(), start.y.toFloat())
                p.lineTo(end.x.toFloat(), end.y.toFloat())
            }
            canvas.drawPath(p, pLine)
        }
    }


    data class Progress(val color: Int, val progress: Int)
}