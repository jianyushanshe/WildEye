package com.jianyushanshe.wildeye.ui.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jianyushanshe.wildeye.R
import com.jianyushanshe.wildeye.extension.dp2px

/**
 * Author:wangjianming
 * Time:2020/11/19 14:35
 * Description:SimpleDividerDecoration
 */
class SimpleDividerDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var dividerHeight: Int = dp2px(0.5f)
    private var leftSpace = 0
    private var showDividerUnderLastItem = false
    private var dividerPaint: Paint = Paint()

    init {
        dividerPaint.color = ContextCompat.getColor(context, R.color.grayDark)
    }

    constructor(context: Context, showDividerUnderLastItem: Boolean) : this(context) {
        this.showDividerUnderLastItem = showDividerUnderLastItem
    }

    constructor(context: Context, leftSpace: Int) : this(context) {
        this.leftSpace = leftSpace
    }

    constructor(context: Context, dividerHeight: Int, @ColorInt dividerColor: Int) : this(context) {
        this.dividerHeight = dividerHeight
        dividerPaint.color = dividerColor
    }

    constructor(
        context: Context,
        leftSpace: Int,
        showDividerUnderLastItem: Boolean,
        dividerHeight: Int,
        @ColorInt dividerColor: Int
    ) : this(context) {
        this.showDividerUnderLastItem = showDividerUnderLastItem
        this.leftSpace = leftSpace
        this.dividerHeight = dividerHeight
        dividerPaint.color = dividerColor
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val left = parent.paddingLeft + leftSpace
        val right = parent.width - parent.paddingRight

        val loopLenght = if (showDividerUnderLastItem){
            childCount
        }else{
            childCount-1
        }

        for (i in 0 until loopLenght){
            val view = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = (view.bottom+dividerHeight).toFloat()
            c.drawRect(left.toFloat(),top,right.toFloat(),bottom,dividerPaint)
        }
    }

}