package com.jianyushanshe.wildeye.ui.common.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jianyushanshe.wildeye.R
import com.jianyushanshe.wildeye.event.MessageEvent
import com.jianyushanshe.wildeye.extension.dialogShare
import com.jianyushanshe.wildeye.extension.logD
import com.jianyushanshe.wildeye.ui.common.callback.RequestLifecycle
import com.jianyushanshe.wildeye.util.ShareUtil
import com.umeng.analytics.MobclickAgent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Author:wangjianming
 * Time:2020/11/18 16:54
 * Description:BaseFragment
 */
open class BaseFragment : Fragment(), RequestLifecycle {
    /**
     * 是否已经加载过数据
     */
    private var mHasLoadedData = false

    /**
     * 数据加载失败布局
     */
    private var loadErrorView: View? = null

    /**
     * fragment布局
     */
    protected var rootView: View? = null

    /**
     * 加载提示条
     */
    protected var loading: ProgressBar? = null

    /**
     * 依附的activity
     */
    lateinit var activity: Activity

    protected val TAG: String = this.javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity()!!
        logD(TAG, "BaseFragment-->onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG, "BaseFragment-->onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logD(TAG, "BaseFragment-->onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD(TAG, "BaseFragment-->onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logD(TAG, "BaseFragment-->onActivityCreated()")
    }

    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseFragment-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseFragment-->onResume()")
        MobclickAgent.onPageStart(javaClass.name)
        if (!mHasLoadedData) {
            loadDataOnce()
            logD(TAG, "BaseFragment-->loadDataOnce()")
            mHasLoadedData = true
        }
    }

    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseFragment-->onPause()")
        MobclickAgent.onPageEnd(javaClass.name)
    }

    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseFragment-->onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logD(TAG, "BaseFragment-->onDestroyView()")
        EventBus.getDefault().unregister(this)
        if (rootView?.parent!=null)(rootView?.parent as ViewGroup).removeView(rootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseFragment-->onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        logD(TAG, "BaseFragment-->onDetach()")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent){
        logD(TAG, "BaseFragment-->onMessageEvent()")
    }


    open fun loadDataOnce() {

    }
    @CallSuper
    override fun startLoading() {
        loading?.visibility = View.VISIBLE
        hindeLoadErrorView()
    }

    private fun hindeLoadErrorView() {
        loadErrorView?.visibility = View.GONE
    }

    @CallSuper
    override fun loadFinished() {
       loading?.visibility = View.GONE
    }

    @CallSuper
    override fun loadFailed(msg: String?) {
        loading?.visibility = View.GONE
    }

    fun onCreateView(view: View):View{
        rootView = view
        loading = view.findViewById(R.id.loading)
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
        return view
    }

    protected fun showLoadErrorView(tip: String, block: View.() -> Unit) {
        if (loadErrorView != null) {
            loadErrorView?.visibility = View.VISIBLE
            return
        }
        if (rootView != null) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.loadErrorView)
            if (viewStub != null) {
                loadErrorView = viewStub.inflate()
                val loadErrorText = loadErrorView?.findViewById<TextView>(R.id.loadErrorText)
                loadErrorText?.text = tip
                val loadErrorRootView = loadErrorView?.findViewById<View>(R.id.loadErrorkRootView)
                loadErrorRootView?.setOnClickListener { it?.block() }
            }
        }
    }

    protected fun share(shareContent: String,shareType : Int){
        ShareUtil.share(this.activity,shareContent,shareType)
    }

    protected fun showDialogShare(shareContent:String){
        if (this.activity is AppCompatActivity){
            dialogShare(this.activity as AppCompatActivity,shareContent)
        }
    }
}