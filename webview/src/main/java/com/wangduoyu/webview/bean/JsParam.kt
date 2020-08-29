package com.wangduoyu.webview.bean

import com.google.gson.JsonObject

data class JsParam(
    val name: String,
    val param: JsonObject
)