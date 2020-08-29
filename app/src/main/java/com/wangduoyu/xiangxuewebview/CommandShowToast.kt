package com.wangduoyu.xiangxuewebview


import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.auto.service.AutoService
import com.wangduoyu.base.BaseApplication
import com.wangduoyu.webview.ICallBack
import com.wangduoyu.webview.command.Command


@AutoService(Command::class)
class CommandShowToast : Command {

    override fun name(): String = "showToast"

    override fun execute(parametes: Map<String, String>, callBack: ICallBack?) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApplication.sApplication, parametes["message"].toString(), Toast.LENGTH_SHORT).show()
        }
    }

}