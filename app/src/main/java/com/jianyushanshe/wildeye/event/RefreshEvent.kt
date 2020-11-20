package com.jianyushanshe.wildeye.event

/**
 * Author:wangjianming
 * Time:2020/11/17 17:59
 * Description:RefreshEvent 刷新页面
 */
open class RefreshEvent(var activityClass: Class<*>? = null) : MessageEvent()