package com.jianyushanshe.wildeye.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jianyushanshe.wildeye.Const
import com.jianyushanshe.wildeye.R
import com.jianyushanshe.wildeye.extension.*
import com.jianyushanshe.wildeye.ui.common.ui.WebViewActivity
import com.umeng.analytics.MobclickAgent
import java.util.concurrent.TimeUnit

/**
 * Author:wangjianming
 * Time:2020/11/18 15:58
 * Description:DialogAppraiseTipsWorker
 */
class DialogAppraiseTipsWorker(val context: Context, parms: WorkerParameters) :
    Worker(context, parms) {
    override fun doWork(): Result {
        return if (isNeedShowDialog){
            logD(TAG, "${System.currentTimeMillis()},execute doWork function,result : retry")
            Result.retry()
        }else{
            logD(TAG, "${System.currentTimeMillis()},execute doWork function,result : success")
            Result.success()
        }
    }

    companion object {
        const val TAG = "DialogAppraiseTipsWorker"

        val showDialogWorkRequest = OneTimeWorkRequest.Builder(DialogAppraiseTipsWorker::class.java)
            .addTag(TAG)
            .setInitialDelay(1, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.SECONDS)
            .build()

        var isNeedShowDialog: Boolean
            get() = sharedPreferences.getBoolean("is_need_show_dialog", true)
            set(value) = sharedPreferences.edit { putBoolean("is_need_show_dialog", value) }

        private var dialog: AlertDialog? = null

        fun showDialog(context: Context){
            if (!isNeedShowDialog) return
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_appraise_tips,null).apply {
                findViewById<TextView>(R.id.tvEncourageMessage).text = String.format(GlobalUtil.getString(R.string.encourage_message), GlobalUtil.appName)
                findViewById<TextView>(R.id.tvPositiveButton).setOnClickListener{
                    MobclickAgent.onEvent(context, Const.Mobclick.EVENT8)
                    dialog?.dismiss()
                }
                findViewById<TextView>(R.id.tvNegativeButton).setOnClickListener{
                    MobclickAgent.onEvent(context,Const.Mobclick.EVENT9)
                    dialog?.dismiss()
                    WebViewActivity.start(context,WebViewActivity.DEFAULT_TITLE,WebViewActivity.DEFAULT_URL,true,false,WebViewActivity.MODE_SONIC_WITH_OFFLINE_CACHE)
                }
            }
            dialog = AlertDialog.Builder(context,R.style.EyepetizerAlertDialogStyle).setCancelable(false).setView(dialogView).create()
            dialog?.show()
            dialog?.window?.attributes = dialog?.window?.attributes?.apply {
                width = screenWidth-(dp2px((20f)*2))
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            isNeedShowDialog = false
            MobclickAgent.onEvent(context, Const.Mobclick.EVENT7)
        }
    }
}