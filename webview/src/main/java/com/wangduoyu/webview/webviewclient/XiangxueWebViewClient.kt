package com.wangduoyu.webview.webviewclient

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wangduoyu.webview.WebViewCallBack

class XiangxueWebViewClient(val callBack: WebViewCallBack?) : WebViewClient() {

    companion object {
        const val TAG = "XiangxueWebViewClient"
    }

    override fun onPageStarted(view: WebView?, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        callBack?.pageStarted(url)

        if (callBack == null) {
            Log.e(TAG, "WebViewCallBack is null !!!")
        }
    }

    override fun onPageFinished(view: WebView?, url: String) {
        super.onPageFinished(view, url)
        callBack?.pageFinished(url)
        if (callBack == null) {
            Log.e(TAG, "WebViewCallBack is null !!!")
        }
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        callBack?.onError()
        if (callBack == null) {
            Log.e(TAG, "WebViewCallBack is null !!!")
        }
    }

}