package com.wangduoyu.webview.webviewprocess

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.wangduoyu.webview.webviewprocess.settings.WebDefaultSettings
import com.wangduoyu.webview.webviewprocess.webchromeclient.XiangxueWebChromeClient
import com.wangduoyu.webview.webviewprocess.webviewclient.XiangxueWebViewClient
import android.util.Log
import android.webkit.JavascriptInterface
import com.google.gson.Gson
import com.wangduoyu.webview.WebViewCallBack
import com.wangduoyu.webview.bean.JsParam


class BaseWebView : WebView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    companion object {
        const val TAG = "BaseWebView"
    }

    init {
        WebViewProcessCommandDispatcher.getInstance().initAIDLConnection()
        WebDefaultSettings.getInstance().setSettings(this)
        // 会注入到window中
        addJavascriptInterface(this, "xiangxuewebview")
    }

    fun registerWebViewCallBack(callBack: WebViewCallBack?) {
        webViewClient = XiangxueWebViewClient(callBack)
        webChromeClient = XiangxueWebChromeClient(callBack)
    }

    @JavascriptInterface
    fun takeNativeAction(jsParam: String) {
        Log.i(TAG, jsParam)
        val jsonObject = Gson().fromJson(jsParam, JsParam::class.java)
        jsonObject?.run {
            WebViewProcessCommandDispatcher.getInstance().executeCommand(
                jsonObject.name,
                Gson().toJson(jsonObject.param),this@BaseWebView
            )
        }
    }

    fun handleCallBack(callbackname: String?, response: String?) {
        callbackname?.run {
            response?.run {
                post {
                    val jscode = "javascript:xiangxuejs.callback('$callbackname',$response)"
                    Log.e("xxxxxx", jscode)
                    evaluateJavascript(jscode, null)
                }
            }
        }
    }
}