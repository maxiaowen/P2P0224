package com.atguigu.p2p0224.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyApplication extends Application {

    private static Context context;

    private static Handler handler;
    private static int pid;

    public static Context getContext() {
        return context;
    }
    /*
    初始化上下文
     */

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //初始化crashHandler
//        CrashHandler.getInstance().init(context);

        initGallery();

        handler = new Handler();
        pid = android.os.Process.myPid();
    }

    /*
   * 配置gallery
   * */
    private void initGallery() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new PicassoImageLoader();
        CoreConfig coreConfig =
                new CoreConfig.Builder(this, imageloader, theme)
                        .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getPid() {
        return pid;
    }
}
