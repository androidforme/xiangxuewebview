package com.wangduoyu.usercenter

import android.content.Intent
import com.google.auto.service.AutoService
import com.wangduoyu.base.BaseApplication
import com.wangduoyu.common.autoservice.IUserCenterService

@AutoService(IUserCenterService::class)

class UserCenterServiceImp : IUserCenterService {
    override fun isLogin(): Boolean {
        return false
    }

    override fun login() {
        val intent = Intent(BaseApplication.sApplication, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        BaseApplication.sApplication.startActivity(intent)
    }

}