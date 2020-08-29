package com.wangduoyu.webview.webviewprocess.webchromeclient

import android.util.Log
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

    //html的打印可以在这里接收；
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        Log.d(TAG,consoleMessage.message())
        return super.onConsoleMessage(consoleMessage)
    }

}