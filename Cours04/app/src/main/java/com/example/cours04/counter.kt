package com.example.cours04

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import java.util.*

class Counter : View{
    lateinit var backgroundPaint: Paint
    lateinit var backgroundRect: RectF
    lateinit var linePaint: Paint
    lateinit var textPaint: TextPaint
    private var cornerRadius = 0f;
    private var displayedCount = ""
    private var count = 0

    fun reset(){
        count = 0
        setCount(count)
    }

    fun increment(){
        count++
        setCount(count)
    }

    private fun setCount(count: Int) {
        this.displayedCount = String.format(Locale.getDefault(), "%06d", count)
        invalidate()
    }

    constructor(ctx: Context) : super(ctx){
        initialize(ctx)
    }
    constructor(ctx: Context,attrs: AttributeSet) : super(ctx, attrs){
        initialize(ctx)
    }
    private fun initialize(ctx: Context) {
        backgroundRect = RectF()
        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint.color = ContextCompat.getColor(ctx, R.color.cardview_dark_background)

        linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.color = ContextCompat.getColor(ctx, R.color.purple_200)
        linePaint.strokeWidth = 5f

        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        textPaint.color =ContextCompat.getColor(ctx, R.color.purple_200)
        textPaint.textSize = Math.round(64f * resources.displayMetrics.scaledDensity).toFloat()


        cornerRadius = Math.round(2f * resources.displayMetrics.density).toFloat()
        this.setCount(count)
    }

    val MAX_CHARS = "9999999"
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val maxTextWidth = textPaint.measureText(MAX_CHARS)
        val fontMetrics = textPaint.fontMetrics

        val maxTextHeight = -fontMetrics.top + fontMetrics.bottom

        val desiredW = Math.round(maxTextWidth + paddingLeft + paddingRight)
        val desiredH = Math.round(maxTextHeight * 2f + paddingLeft + paddingRight)

        val mesuredW = resolveSize(desiredW, widthMeasureSpec)
        val mesuredH = resolveSize(desiredH, heightMeasureSpec)
        setMeasuredDimension(mesuredW,mesuredH)
    }

    override fun onDraw(canvas: Canvas?) {
        val canvasW = canvas?.width?.times(1f) ?:0f
        val canvasH = canvas?.height?.times(1f) ?:0f

        val center = canvasW * 0.5f

        backgroundRect.set(0f,0f,canvasW,canvasH)
        canvas?.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint)

        val baselineY = Math.round(canvasH * 0.6f).toFloat()
        canvas?.drawLine(0f,baselineY, canvasW, baselineY,linePaint)

        val textWidth = textPaint.measureText(displayedCount)
        val textX = Math.round(center - textWidth * 0.5f).toFloat()
        canvas?.drawText(displayedCount, textX, baselineY, textPaint)
    }

}