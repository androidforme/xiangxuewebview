package com.wangduoyu.webview.command

import com.wangduoyu.webview.ICallBack

interface Command {
    fun name(): String
    fun execute(parametes: Map<String, String>, callBack: ICallBack?)
}