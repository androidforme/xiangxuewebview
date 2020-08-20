package com.wangduoyu.webview

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.wangduoyu.common.autoservice.IWebViewService
import com.wangduoyu.webview.utils.Constants

@AutoService(IWebViewService::class)
class WebViewServiceImp :IWebViewService {

    override fun getWebViewFragment(url: String): Fragment {
        return WebViewFragment.newInstance(url)
    }

    override fun startWebViewActivity(context: Context, url: String, title: String, isShowActionBar: Boolean) {
        val intent = Intent(context,WebViewActivity::class.java).apply {
            putExtra(Constants.TITLE,title)
            putExtra(Constants.URL,url)
            putExtra(Constants.IS_SHOW_ACTIONBAR,isShowActionBar)
        }
        context.startActivity(intent)
    }


}