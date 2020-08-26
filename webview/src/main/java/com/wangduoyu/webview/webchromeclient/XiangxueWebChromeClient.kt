package com.wangduoyu.webview.webchromeclient

import android.webkit.*
import com.wangduoyu.webview.WebViewCallBack

class XiangxueWebChromeClient(val callBack: WebViewCallBack?) : WebChromeClient() {

    companion object {
        const val TAG = "XiangxueWebChromeClient"
    }

    override fun onReceivedTitle(view: WebView, title: String) {
        super.onReceivedTitle(view, title)
        callBack?.updateTitle(title)
    }

}