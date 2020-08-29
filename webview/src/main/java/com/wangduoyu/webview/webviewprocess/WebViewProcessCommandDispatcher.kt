package com.wangduoyu.webview.webviewprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.wangduoyu.base.BaseApplication
import com.wangduoyu.webview.ICallBack
import com.wangduoyu.webview.mainprocess.MainProcessCommandService
import com.wangduoyu.webview.IWebviewProcessToMainProcessInterface


class WebViewProcessCommandDispatcher : ServiceConnection {
    private var iWebviewProcessToMainProcessInterface: IWebviewProcessToMainProcessInterface? = null

    override fun onServiceDisconnected(p0: ComponentName?) {
        iWebviewProcessToMainProcessInterface = null
        initAIDLConnection()
    }

    override fun onServiceConnected(componentName: ComponentName?, service: IBinder) {
        iWebviewProcessToMainProcessInterface =
            IWebviewProcessToMainProcessInterface.Stub.asInterface(service)
    }

    override fun onBindingDied(name: ComponentName?) {
        iWebviewProcessToMainProcessInterface = null
        initAIDLConnection()
    }

    companion object {
        private var sInstance: WebViewProcessCommandDispatcher? = null

        fun getInstance(): WebViewProcessCommandDispatcher {
            if (sInstance == null) {
                synchronized(WebViewProcessCommandDispatcher::class.java) {
                    sInstance = WebViewProcessCommandDispatcher()
                }
            }
            return sInstance!!
        }
    }

    fun initAIDLConnection() {
        val intent = Intent(BaseApplication.sApplication, MainProcessCommandService::class.java)
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun executeCommand(
        commandName: String,
        params: String,
        baseWebView: BaseWebView
    ) {
        iWebviewProcessToMainProcessInterface?.run {
            handleWebCommand(commandName, params, object : ICallBack.Stub() {
                override fun onResult(callbackname: String?, response: String?) {
                    baseWebView.handleCallBack(callbackname, response)
                }
            })
        }
    }
}