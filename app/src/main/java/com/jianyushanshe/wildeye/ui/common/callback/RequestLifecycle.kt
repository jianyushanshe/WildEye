package com.jianyushanshe.wildeye.ui.common.callback

/**
 * Author:wangjianming
 * Time:2020/11/18 16:56
 * Description:RequestLifecycle 网络加载提示
 */
interface RequestLifecycle {
    fun startLoading()
    fun loadFinished()
    fun loadFailed(msg: String?)

}