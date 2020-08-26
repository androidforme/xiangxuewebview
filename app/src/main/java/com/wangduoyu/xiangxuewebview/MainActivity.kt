package com.wangduoyu.xiangxuewebview


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wangduoyu.base.autoservice.XiangxueServiceLoader
import com.wangduoyu.common.autoservice.IWebViewService
import com.wangduoyu.xiangxuewebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.openWebview.setOnClickListener {
            val webViewService = XiangxueServiceLoader.load(IWebViewService::class.java)
            webViewService?.run {
                startWebViewActivity(this@MainActivity, "https://www.baidu.com", "1百度", true)
            }
        }
    }
}
