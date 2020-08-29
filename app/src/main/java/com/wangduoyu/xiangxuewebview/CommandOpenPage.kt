package com.wangduoyu.xiangxuewebview

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import com.google.auto.service.AutoService
import com.wangduoyu.base.BaseApplication
import com.wangduoyu.webview.ICallBack
import com.wangduoyu.webview.command.Command
import com.wangduoyu.webview.mainprocess.MainProcessCommandManager

@AutoService(Command::class)
class CommandOpenPage : Command {

    override fun name(): String = "openPage"

    override fun execute(parametes: Map<String, String>, callBack: ICallBack?) {
        val target = parametes?.get("target_class")
        target?.run {
            val intent = Intent().apply {
                component = ComponentName(BaseApplication.sApplication, target)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            Log.d(MainProcessCommandManager.TAG, "executeCommand target:$target")
            BaseApplication.sApplication.startActivity(intent)
        }
    }

}