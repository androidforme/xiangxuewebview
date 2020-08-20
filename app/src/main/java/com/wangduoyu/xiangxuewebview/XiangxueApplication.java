package com.wangduoyu.xiangxuewebview;

import com.kingja.loadsir.core.LoadSir;
import com.wangduoyu.base.BaseApplication;
import com.wangduoyu.base.loadsir.CustomCallback;
import com.wangduoyu.base.loadsir.EmptyCallback;
import com.wangduoyu.base.loadsir.ErrorCallback;
import com.wangduoyu.base.loadsir.LoadingCallback;
import com.wangduoyu.base.loadsir.TimeoutCallback;

public class XiangxueApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
