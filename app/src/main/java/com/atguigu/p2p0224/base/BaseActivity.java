package com.atguigu.p2p0224.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.atguigu.p2p0224.bean.LoginBean;
import com.atguigu.p2p0224.common.AppManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);

        //初始化控件
        initView();
        //初始化数据
        initData();
        //事件监听
        initListener();
        //初始化标题
        initTitle();
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initView();

    public abstract int getLayoutId();

    public void initTitle(){

    }

    /*
       *
       * 初始化对象
       * */
    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    /*
    *
    * 弹出吐司
    * */
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
  * 存储用户信息
  * */
    private String spName = "loginbean";
    public void saveUser(LoginBean bean){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name",bean.getName());
        edit.putString("imageurl",bean.getImageurl());
        edit.putString("iscredit",bean.getIscredit());
        edit.putString("phone",bean.getPhone());
        edit.commit();
    }
    /*
    * 获取用户信息
    * */
    public LoginBean getUser(){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        LoginBean bean = new LoginBean();
        bean.setName(sp.getString("name","admin"));
        bean.setImageurl(sp.getString("imageurl",""));
        bean.setIscredit(sp.getString("iscredit",""));
        bean.setPhone(sp.getString("phone",""));
        return bean;
    }

    //清除SP内容
    public void clearSp(){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        sp.edit().clear().commit();

        SharedPreferences sp2 = getSharedPreferences("isChecked", MODE_PRIVATE);
        sp2.edit().clear().commit();
    }

    /*
   * 保存图片地址
   * */
    public void saveImage(String image){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl",image);
        edit.putBoolean("isFile",true);
        edit.commit();
    }
    /*
    * 获取图片
    * */
    public String getImage(){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        boolean isFile = sp.getBoolean("isFile", false);
        if (isFile){
            return sp.getString("imageurl","");
        }else{
            return "";
        }
    }

    /*
   * 保存滑动按钮的状态
   * */
    public void save(String key,boolean value){
        SharedPreferences sp = getSharedPreferences("isChecked", MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    /*
    * 获取滑动按钮的状态
    * */
    public boolean get(String key){
        SharedPreferences sp = getSharedPreferences("isChecked", MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }
}
