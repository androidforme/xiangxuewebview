package com.wangduoyu.xiangxuewebview

import android.util.Log
import com.google.auto.service.AutoService
import com.google.gson.Gson
import com.wangduoyu.base.autoservice.XiangxueServiceLoader
import com.wangduoyu.common.autoservice.IUserCenterService
import com.wangduoyu.common.eventbus.LoginEvent
import com.wangduoyu.webview.ICallBack
import com.wangduoyu.webview.command.Command
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@AutoService(Command::class)
class CommandLogin : Command {
    init {
        EventBus.getDefault().register(this)
    }

    val service = XiangxueServiceLoader.load(IUserCenterService::class.java)
    var callBack: ICallBack? = null
    var callbackNameFromNativeJS: String? = null

    override fun name(): String = "login"

    override fun execute(parametes: Map<String, String>, callBack: ICallBack?) {
        service?.run {
            if (!isLogin()) {
                login()
                this@CommandLogin.callBack = callBack
                callbackNameFromNativeJS = parametes["callbackname"]
            }
        }
    }

    @Subscribe
    fun onMeassgerEvent(event: LoginEvent) {
        val map = HashMap<String, String>().apply {
            put("accountName", event.name)
        }
        callBack?.onResult(callbackNameFromNativeJS, Gson().toJson(map))
    }

}