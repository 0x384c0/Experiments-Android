package com.example.animations_demo.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet

class SquareMultiProgressView : MultiProgressView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override val strokeCap = Paint.Cap.SQUARE
    private val rectPath = Path()
    private val rect = RectF(0f, 0f, 0f, 0f)

    override fun onDraw(canvas: Canvas?) {
        val measuredHeight = height.toFloat()
        val measuredWidth = width.toFloat()
        val cornerRadius = measuredHeight / 2f

        rect.set(0f, 0f, measuredWidth, measuredHeight)
        rectPath.reset()
        rectPath.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW)

        canvas?.clipPath(rectPath)
        super.onDraw(canvas)
    }
}