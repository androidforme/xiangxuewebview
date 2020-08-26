package com.wangduoyu.webview


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.wangduoyu.base.loadsir.ErrorCallback
import com.wangduoyu.base.loadsir.LoadingCallback
import com.wangduoyu.webview.databinding.FragmentWebviewBinding
import com.wangduoyu.webview.utils.Constants
import com.wangduoyu.webview.webchromeclient.XiangxueWebChromeClient
import com.wangduoyu.webview.webviewclient.XiangxueWebViewClient

class WebViewFragment : Fragment(), WebViewCallBack {


    var mUrl: String? = null
    var mCanNativeRefresh: Boolean = true
    var mIsError: Boolean = false
    lateinit var binding: FragmentWebviewBinding
    lateinit var mLoadService: LoadService<Any>

    companion object {

        const val TAG = "WebViewFragment"

        fun newInstance(url: String, canNativeRefresh: Boolean = true): WebViewFragment {
            return WebViewFragment().apply {
                val bundle = Bundle()
                bundle.putString(Constants.URL, url)
                bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh)
                arguments = bundle
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle?.run {
            mUrl = bundle.getString(Constants.URL)
            mCanNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(mUrl)
        binding.webview.webViewClient = XiangxueWebViewClient(this)
        binding.webview.webChromeClient = XiangxueWebChromeClient(this)
        binding.smartRefreshLayout.apply {
            setEnableLoadMore(false)
            setEnableRefresh(mCanNativeRefresh)
            setOnRefreshListener {  binding.webview.reload() }
        }

        mLoadService = LoadSir.getDefault().register(binding.smartRefreshLayout) {
            mLoadService.showCallback(LoadingCallback::class.java)
            binding.webview.reload()
        }
        return mLoadService.loadLayout
    }

    override fun pageStarted(url: String) {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String) {
        binding.smartRefreshLayout.finishRefresh()
        if (mIsError){
            binding.smartRefreshLayout.setEnableRefresh(true)
            mLoadService.showCallback(ErrorCallback::class.java)
        }else {
            binding.smartRefreshLayout.setEnableRefresh(mCanNativeRefresh)
            mLoadService.showSuccess()
        }
        Log.d(TAG, "pageFinished")
        mIsError = false
    }

    override fun onError() {
        Log.e(TAG, "onError")
        mIsError = true
    }

    override fun updateTitle(title: String) {
        if (activity is WebViewActivity){
            (activity as WebViewActivity).updateTitle(title)
        }
    }
}