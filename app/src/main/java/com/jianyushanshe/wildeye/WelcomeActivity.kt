package com.jianyushanshe.wildeye

import android.Manifest
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.jianyushanshe.wildeye.extension.edit
import com.jianyushanshe.wildeye.extension.sharedPreferences
import com.jianyushanshe.wildeye.ui.common.ui.BaseActivity
import com.jianyushanshe.wildeye.util.GlobalUtil
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * wangjianming
 * 启动页
 */
class WelcomeActivity : BaseActivity() {
    private val job by lazy { Job() }
    private val splashDuration = 3 * 1000L
    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f,
            1.05f,
            1f,
            1.05f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWriteExternalStoragePermission()
    }

    override fun setupViews() {
        super.setupViews()
        ivSlogan.startAnimation(alphaAnimation)
        ivSplashPicture.startAnimation(scaleAnimation)
        CoroutineScope(job).launch {
            delay(splashDuration)
            MainActivity.start(this@WelcomeActivity)
            finish()
        }
        isFirstEntryApp = false
    }

    override fun setStatusBarBackgroud(statusBarColor: Int) {
        //沉浸式
        ImmersionBar.with(this).init()
    }

    private fun requestWriteExternalStoragePermission() {
        PermissionX.init(this@WelcomeActivity).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showRequestReasonDialog(deniedList, message, GlobalUtil.getString(R.string.ok), GlobalUtil.getString(R.string.cancel))
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showForwardToSettingsDialog(deniedList, message, GlobalUtil.getString(R.string.settings), GlobalUtil.getString(R.string.cancel))
            }
            .request { allGranted, grantedList, deniedList ->
                requestReadPhoneStatePermission()
            }
    }

    private fun requestReadPhoneStatePermission() {
        PermissionX.init(this@WelcomeActivity).permissions(Manifest.permission.READ_PHONE_STATE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showRequestReasonDialog(deniedList, message, GlobalUtil.getString(R.string.ok), GlobalUtil.getString(R.string.cancel))
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showForwardToSettingsDialog(deniedList, message, GlobalUtil.getString(R.string.settings), GlobalUtil.getString(R.string.cancel))
            }
            .request { allGranted, grantedList, deniedList ->
                setContentView(R.layout.activity_welcome)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    companion object{
        //是否首次进入app
        var isFirstEntryApp : Boolean
        get() = sharedPreferences.getBoolean("is_first_entry_app",true)
        set(value) = sharedPreferences.edit { putBoolean("is_first_entry_app",value) }
    }

}