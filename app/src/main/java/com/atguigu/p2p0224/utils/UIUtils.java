package com.atguigu.p2p0224.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.atguigu.p2p0224.common.MyApplication;

/**
 * Created by Administrator on 2017/6/20.
 */

public class UIUtils {

    /*
  * 加载布局
  * */
    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }

    /**
     * 返回一个人上下文
     */
    private static Context getContext(){

        return MyApplication.getContext();
    }

    /*
   * 格式化字符串 - 占位字符
   * */
    public static String stringFormat(int id, String value){
        String format = String.format(getString(id), value);

        return format;
    }

    /*
   * 从string文件获取字符串
   * */
    private static String getString(int id) {
        return getContext().getResources().getString(id);
    }


    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static void runOnUIThread(Runnable runnable) {

        //pid processid 进程id
        //tid threadid 线程id
        //注意：如果在主线程中运行 那么tid == pid
        Log.d("process", "runOnUIThread: "
                +"processid=="+MyApplication.getPid()
                +"  threadid== "+android.os.Process.myTid()
        );
        if(MyApplication.getPid() == android.os.Process.myTid()){
            runnable.run();
        }else{
            MyApplication.getHandler().post(runnable);
        }
    }}
