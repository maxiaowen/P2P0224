package com.atguigu.p2p0224.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/21.
 */

public class AppManager {

    private  AppManager(){}

    private static AppManager manager = new AppManager();

    public static  AppManager getInstance(){
        return manager;
    }

    private static Stack<Activity> stack = new Stack<>();

    public void addActivity(Activity activity){
        if(activity != null) {
            stack.add(activity);
        }
    }

    public void removeActivity (Activity activity){
        if(activity != null) {
            for(int i = stack.size()-1; i >= 0 ; i--) {

                Activity currentActivity = stack.get(i);
                if(currentActivity == activity) {
                    currentActivity.finish();
                    stack.remove(currentActivity);
                }
            }
        }
    }

    public void removeAll(){
        for(int i = stack.size()-1; i >= 0 ; i--) {
            Activity currentActivity = stack.get(i);
            if(currentActivity != null) {
                currentActivity.finish();
                stack.remove(currentActivity);
            }
        }
    }

}
