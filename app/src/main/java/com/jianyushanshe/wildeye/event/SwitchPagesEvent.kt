package com.jianyushanshe.wildeye.event

/**
 * Author:wangjianming
 * Time:2020/11/17 18:01
 * Description:SwitchPagesEvent
 */
open class SwitchPagesEvent(var activityClass: Class<*>?= null) :MessageEvent()