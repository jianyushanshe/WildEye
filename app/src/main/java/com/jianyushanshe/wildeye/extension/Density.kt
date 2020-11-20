package com.jianyushanshe.wildeye.extension

import com.jianyushanshe.wildeye.WildEyeApp

/**
 * Author:wangjianming
 * Time:2020/11/16 17:19
 * Description:Density
 */

/**
 * 根据手机的分辨率将dp转成为px。
 */
fun dp2px(dp: Float): Int {
    val scale = WildEyeApp.context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率将px转成dp。
 */
fun px2dp(px: Float): Int {
    val scale = WildEyeApp.context.resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * 获取屏幕宽值。
 */
val screenWidth
    get() = WildEyeApp.context.resources.displayMetrics.widthPixels

/**
 * 获取屏幕高值。
 */
val screenHeight
    get() = WildEyeApp.context.resources.displayMetrics.heightPixels

/**
 * 获取屏幕像素：对获取的宽高进行拼接。例：1080X2340。
 */
fun screenPixel(): String {
    WildEyeApp.context.resources.displayMetrics.run {
        return "${widthPixels}X${heightPixels}"
    }
}