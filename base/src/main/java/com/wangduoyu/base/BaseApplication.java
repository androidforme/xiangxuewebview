package com.wangduoyu.base;

import android.app.Application;

// 放在最底层的好处是 所以的module都能引用到它 sApplication
// 不需要UI的context database preference 不会有内存泄漏；
public class BaseApplication extends Application {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
