package com.wangduoyu.webview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.wangduoyu.base.loadsir.LoadingCallback
import com.wangduoyu.webview.databinding.FragmentWebviewBinding
import com.wangduoyu.webview.utils.Constants
import com.wangduoyu.webview.webviewclient.XiangxueWebViewClient

class WebViewFragment : Fragment() ,WebViewCallBack{

    var mUrl: String? = null
    lateinit var binding: FragmentWebviewBinding
    lateinit var mLoadService: LoadService<Any>

    companion object {

        fun newInstance(url: String): WebViewFragment {
            return WebViewFragment().apply {
                val bundle = Bundle()
                bundle.putString(Constants.URL, url)
                arguments = bundle
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle?.run {
            mUrl = bundle.getString(Constants.URL)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(mUrl)
        binding.webview.webViewClient = XiangxueWebViewClient(this)
        mLoadService = LoadSir.getDefault().register(binding.webview) {
            mLoadService.showCallback(LoadingCallback::class.java)
            binding.webview.reload()
        }
        return mLoadService.loadLayout
    }

    override fun pageStarted(url: String) {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String) {
        mLoadService.showSuccess()
    }


}