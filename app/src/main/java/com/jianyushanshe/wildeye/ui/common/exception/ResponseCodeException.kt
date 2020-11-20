package com.jianyushanshe.wildeye.ui.common.exception

import java.lang.RuntimeException

/**
 * Author:wangjianming
 * Time:2020/11/19 11:14
 * Description:ResponseCodeException
 */
class ResponseCodeException(val responseCode :Int) :RuntimeException("Http request failed with response code $responseCode")