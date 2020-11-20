package com.jianyushanshe.wildeye.ui.common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.jianyushanshe.wildeye.R
import com.jianyushanshe.wildeye.util.TypeFaceUtil

/**
 * Author:wangjianming
 * Time:2020/11/16 17:30
 * Description:TypefaceTextView
 */
class TypefaceTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView,0,0)
            val typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface,0)
            typeface= getTypeface(typefaceType)
            typedArray.recycle()
        }
    }

    companion object{
        fun getTypeface(typefaceType:Int?) = when(typefaceType){
            TypeFaceUtil.FZLL_TYPEFACE -> TypeFaceUtil.getFzlLTypeface()
            TypeFaceUtil.FZDB1_TYPEFACE -> TypeFaceUtil.getFzdb1Typeface()
            TypeFaceUtil.FUTURA_TYPEFACE -> TypeFaceUtil.getFuturaTypeface()
            TypeFaceUtil.DIN_TYPEFACE -> TypeFaceUtil.getDinTypeface()
            TypeFaceUtil.LOBSTER_TYPEFACE -> TypeFaceUtil.getLobsterTypeface()
            else -> Typeface.DEFAULT
        }
    }

}