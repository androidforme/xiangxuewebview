// IWebviewProcessToMainProcessInterface.aidl
package com.wangduoyu.webview;

// Declare any non-default types here with import statements

interface ICallBack {
    void onResult(String callbackname, String response);
}
