package com.jianyushanshe.wildeye.ui.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.jianyushanshe.wildeye.R

/**
 * Author:wangjianming
 * Time:2020/11/19 14:30
 * Description:TypefaceEditText
 */
class TypefaceEditText:AppCompatEditText {
    constructor(context:Context):this(context,null)
    constructor(context: Context,attrs:AttributeSet?):this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView,0,0)
            val typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface,0)
            typeface = TypefaceTextView.getTypeface(typefaceType)
            typedArray.recycle()
        }
    }

}