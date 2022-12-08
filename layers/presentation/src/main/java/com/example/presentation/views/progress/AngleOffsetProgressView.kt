package com.example.presentation.views.progress

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.graphics.ColorUtils
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class AngleOffsetProgressView : BaseProgressView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var progress: Float by Delegates.observable(0.7f) { _, _, _ ->
        invalidate()
    }

    var progressWidth: Float by Delegates.observable(10f) { _, _, _ ->
        invalidate()
    }

    var progressColor: Int by Delegates.observable(Color.WHITE) { _, _, _ ->
        invalidate()
    }

    var progressBgDotsColor: Int by Delegates.observable(
        Color.TRANSPARENT
    ) { _, _, _ ->
        invalidate()
    }

    var angleOffset: Int by Delegates.observable(45) { _, _, _ ->
        invalidate()
    }

    private val radiusPx: Float
        get() {
            return if (width < height)
                width.toFloat() / 2f
            else
                height.toFloat() / 2f
        }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        //paints
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

        val shadowBlurRadius = pxFromDp(10f)
        val pBlurredLine = object : Paint() {
            init {
                style = Style.STROKE
                isAntiAlias = true
                strokeWidth = pxFromDp(progressWidth / 2f)
                color = ColorUtils.setAlphaComponent(
                    progressColor,
                    (255 * 0.25).toInt()
                )
                strokeJoin = Join.ROUND
                strokeCap = Cap.ROUND
                maskFilter = BlurMaskFilter(shadowBlurRadius, BlurMaskFilter.Blur.NORMAL)
            }
        }

        val padding =
            if ((pLine.strokeWidth / 2f) > (pBlurredLine.strokeWidth / 2f + shadowBlurRadius))
                (pLine.strokeWidth / 2f)
            else
                (pBlurredLine.strokeWidth  + shadowBlurRadius)
        val radiusWithPadding = radiusPx - padding
        val circumference = 2 * Math.PI * radiusWithPadding
        val dostCount = 10f * 4f
        val dotsPeriod = (circumference / dostCount).toFloat()

        val pBgDots = object : Paint() {
            init {
                style = Style.STROKE
                isAntiAlias = true
                strokeWidth = pxFromDp(progressWidth) / 2f
                color = progressBgDotsColor
                strokeJoin = Join.ROUND
                strokeCap = Cap.ROUND
                pathEffect = DashPathEffect(floatArrayOf(1f, dotsPeriod), 0f)
            }
        }


        //path
        val p = Path()
        val start = Point(
            radiusPx.roundToInt(),
            (getHeightNormalized(0.5f) + radiusWithPadding).roundToInt()
        )

        p.reset()
        p.moveTo(start.x.toFloat(), start.y.toFloat())
        p.arcTo(
            RectF(
                start.x - radiusWithPadding,
                start.y - radiusWithPadding * 2,
                start.x + radiusWithPadding,
                start.y.toFloat()
            ),
            90f,
            359f
        )

        val angleOffsetNormalized = angleOffset / 360f
        val fullProgressPart = 1 - angleOffsetNormalized * 2f

        //draw
        canvas.drawPath(
            getSubPath(
                p,
                angleOffsetNormalized,
                1f - angleOffsetNormalized - fullProgressPart * (1f - progress)
            ), pBlurredLine
        )
        canvas.drawPath(getSubPath(p, angleOffsetNormalized, 1f - angleOffsetNormalized), pBgDots)
        canvas.drawPath(
            getSubPath(
                p,
                angleOffsetNormalized + fullProgressPart * progress,
                1f - angleOffsetNormalized
            ), pLine
        )
    }
}