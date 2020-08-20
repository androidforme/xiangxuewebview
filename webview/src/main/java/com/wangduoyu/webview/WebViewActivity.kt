package com.wangduoyu.webview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wangduoyu.webview.databinding.ActivityWebviewBinding
import com.wangduoyu.webview.utils.Constants

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        binding.backBtn.setOnClickListener { finish() }
        binding.title.text = intent.getStringExtra(Constants.TITLE)
        binding.actionBar.visibility = if (intent.getBooleanExtra(
                Constants.IS_SHOW_ACTIONBAR, false)) View.VISIBLE else View.GONE

        val fragment = WebViewFragment.newInstance(intent.getStringExtra(Constants.URL))
        supportFragmentManager.beginTransaction().replace(R.id.web_view_fragment,fragment).commit()

    }
}