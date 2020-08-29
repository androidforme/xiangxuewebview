package com.wangduoyu.webview.mainprocess

import android.util.Log
import com.google.gson.Gson
import com.wangduoyu.webview.ICallBack
import com.wangduoyu.webview.IWebviewProcessToMainProcessInterface
import com.wangduoyu.webview.command.Command
import java.util.*
import kotlin.collections.HashMap

class MainProcessCommandManager : IWebviewProcessToMainProcessInterface.Stub() {


    var mCommands: HashMap<String, Command> = HashMap()

    init {
        val serviceLoader = ServiceLoader.load(Command::class.java)
        serviceLoader.forEach {
            if (!mCommands.containsKey(it.name())) {
                mCommands[it.name()] = it
            }
        }
    }

    companion object {
        const val TAG = "MainProcessCommand"

        private var sInstance: MainProcessCommandManager? = null

        fun getInstance(): MainProcessCommandManager {
            if (sInstance == null) {
                synchronized(MainProcessCommandManager::class.java) {
                    sInstance = MainProcessCommandManager()
                }
            }
            return sInstance!!
        }

    }

    override fun handleWebCommand(commandName: String, jsonParams: String?, callback: ICallBack?) {
        Log.i("test","handleWebCommand :${mCommands[commandName]}")
        MainProcessCommandManager.getInstance().executeCommand(
            commandName,
            Gson().fromJson(jsonParams, Map::class.java) as Map<String, String>,callback
        )
    }

    private fun executeCommand(
        commandName: String,
        params: Map<String, String>?,
        callback: ICallBack?
    ) {
        if (params != null) {
            mCommands[commandName]?.execute(params,callback)
        }

    }
}