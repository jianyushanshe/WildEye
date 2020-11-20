package com.jianyushanshe.wildeye.ui.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.jianyushanshe.wildeye.R

/**
 * Author:wangjianming
 * Time:2020/11/19 11:56
 * Description:TypefaceRadioButton
 */
class TypefaceRadioButton :AppCompatRadioButton{
    constructor(context: Context) : this(context,null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)
    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView,0,0)
            val typeFaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface,0)
            typeface = TypefaceTextView.getTypeface(typeFaceType)
            typedArray.recycle()
        }
    }
}