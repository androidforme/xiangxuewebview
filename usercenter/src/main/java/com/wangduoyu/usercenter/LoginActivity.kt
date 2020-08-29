package com.wangduoyu.usercenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wangduoyu.common.eventbus.LoginEvent
import com.wangduoyu.usercenter.databinding.ActivityLoginBinding
import org.greenrobot.eventbus.EventBus

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        binding.btnLogin.setOnClickListener{
            EventBus.getDefault().post(LoginEvent("inputEmail"))
            finish()
        }

    }
}