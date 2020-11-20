package com.jianyushanshe.wildeye.util

import com.google.gson.JsonSyntaxException
import com.jianyushanshe.wildeye.R
import com.jianyushanshe.wildeye.extension.logW
import com.jianyushanshe.wildeye.ui.common.exception.ResponseCodeException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Author:wangjianming
 * Time:2020/11/20 16:42
 * Description:ResponseHandler
 * 获取网络请求返回的异常信息。
 */
object ResponseHandler {
    const val TAG = "ResponseHandler"
    fun getFailureTips(e: Throwable?): String {
        logW(TAG, "getFailureTips exception is ${e?.message}")
        return when (e) {
            is ConnectException -> GlobalUtil.getString(R.string.network_connect_error)
            is SocketTimeoutException -> GlobalUtil.getString(R.string.network_connect_timeout)
            is ResponseCodeException -> GlobalUtil.getString(R.string.network_response_code_error) + e.responseCode
            is NoRouteToHostException -> GlobalUtil.getString(R.string.no_route_to_host)
            is UnknownHostException -> GlobalUtil.getString(R.string.network_error)
            is JsonSyntaxException -> GlobalUtil.getString(R.string.json_data_error)
            else -> {
                GlobalUtil.getString(R.string.unknown_error)
            }
        }
    }
}