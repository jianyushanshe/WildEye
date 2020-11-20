package com.jianyushanshe.wildeye.ui.common.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.jianyushanshe.wildeye.R
import com.jianyushanshe.wildeye.event.MessageEvent
import com.jianyushanshe.wildeye.extension.dialogShare
import com.jianyushanshe.wildeye.extension.logD
import com.jianyushanshe.wildeye.util.ActivityCollector
import com.jianyushanshe.wildeye.util.ShareUtil
import com.umeng.analytics.MobclickAgent
import kotlinx.coroutines.NonCancellable.isActive
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.w3c.dom.Text
import java.lang.ref.WeakReference

/**
 * Author:wangjianming
 * Time:2020/11/17 15:27
 * Description:BaseActivity
 */
open class BaseActivity : AppCompatActivity() {
    /**
     * 判断当前Activity是否在前台
     */
    protected var isActive: Boolean = false

    /**
     * 当前activity实例
     */
    protected var activity: Activity? = null

    /**
     * 当前activity的弱引用，防止内存泄露
     */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG, "BaseActivity-->onCreate()")
        activity = this
        activityWR = WeakReference(activity!!)
        ActivityCollector.pushTask(activityWR)

        EventBus.getDefault().register(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logD(TAG, "BaseActivity-->onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logD(TAG, "BaseActivity-->onRestoreInstanceState()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logD(TAG, "BaseActivity-->onNewIntent()")
    }

    override fun onRestart() {
        super.onRestart()
        logD(TAG, "BaseActivity-->onRestart()")
    }

    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseActivity-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseActivity-->onResume()")
        isActive = true
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseActivity-->onPause()")
        isActive = false
        MobclickAgent.onPause(this)
    }

    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseActivity-->onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseActivity-->onDestroy()")
        activity = null
        ActivityCollector.removeTask(activityWR)
        EventBus.getDefault().unregister(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setStatusBarBackgroud(R.color.colorPrimaryDark)
        setupViews()
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        setStatusBarBackgroud(R.color.colorPrimaryDark)
        setupViews()
    }

    protected open fun setupViews() {
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        navigateBefore?.setOnClickListener { finish() }
        tvTitle?.isSelected = true //获取焦点，实现跑马灯效果
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }


    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackgroud(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f)
            .statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }

    /**
     * 内容分享
     */
    protected fun share(shareContent: String, shareType: Int) {
        ShareUtil.share(this, shareContent, shareType)
    }

    /**
     * 显示分享对话框
     */
    protected fun showDialogShare(shareContent: String) {
        dialogShare(this, shareContent)
    }


}