package com.atguigu.p2p0224.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/21.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 设置单例模式
     */
    private CrashHandler(){}
    private static CrashHandler crashHandler = new CrashHandler();
    public static CrashHandler getInstance(){
        return crashHandler;
    }

    private Context context;

    public void init(Context context){
        this.context = context;
        //告诉系统 崩溃的操作 由我来执行
        Thread.setDefaultUncaughtExceptionHandler(this);
    }



    @Override
    public void uncaughtException(Thread t, Throwable e) {
          /*
        * 第一 提醒用户
        * 第二 收集异常
        * 第三 退出应用
        *
        *
        * */

        new Thread(){
            public void run(){
                Looper.prepare();
                //执行在主线程中的代码
                Toast.makeText(context, "系统崩溃了", Toast.LENGTH_SHORT).show();
                Looper.loop();            }
        }.start();


        collection(e);

        //杀死当前的所有进程
        AppManager.getInstance().removeAll();

        //参数 除了0以外都表示非正常退出
        System.exit(0);//退出虚拟机
    }

    /**
     * 收集异常信息
     * @param e
     */
    private void collection(Throwable e) {

        //各种硬件设备的信息
        String board = Build.BOARD;

        //发送信息给服务器
    }
}
