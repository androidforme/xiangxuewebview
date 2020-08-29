// IWebviewProcessToMainProcessInterface.aidl
package com.wangduoyu.webview;

import com.wangduoyu.webview.ICallBack;


interface IWebviewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String commandName, String jsonParams, in ICallBack callback);
}
