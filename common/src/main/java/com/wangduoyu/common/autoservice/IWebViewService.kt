package com.wangduoyu.common.autoservice

import android.content.Context
import androidx.fragment.app.Fragment

interface IWebViewService {
    fun startWebViewActivity(context: Context, url: String, title: String, isShowActionBar: Boolean)

    fun getWebViewFragment(url: String): Fragment
}