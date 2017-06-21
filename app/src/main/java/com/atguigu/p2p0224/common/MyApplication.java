package com.atguigu.p2p0224.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }

    private static Context context;

    /*
    初始化上下文
     */

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
