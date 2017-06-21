package com.atguigu.p2p0224.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.common.AppManager;
import com.atguigu.p2p0224.fragment.HomeFragment;
import com.atguigu.p2p0224.fragment.InvestFragment;
import com.atguigu.p2p0224.fragment.MoreFragment;
import com.atguigu.p2p0224.fragment.ProperyFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.rb_main)
    RadioButton rbMain;
    @Bind(R.id.rb_invest)
    RadioButton rbInvest;
    @Bind(R.id.rb_propert)
    RadioButton rbPropert;
    @Bind(R.id.rb_more)
    RadioButton rbMore;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private ProperyFragment properyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);

        //初始化控件
        initView();
        //初始化数据
        initData();
        //事件监听
        initListener();

    }

    private void initListener() {

        //radioGroup监听
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switchFrgment(checkedId);
            }
        });
    }
    /*
      * 切换fragment
      *
      * */
    private void switchFrgment(int checkedId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hidden(ft);
        switch (checkedId) {
            case R.id.rb_main :
                if(homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_fl,homeFragment);
                }else {
                    ft.show(homeFragment);
                }
                break;
            case R.id.rb_invest :
                if(investFragment == null) {
                    investFragment = new InvestFragment();
                    ft.add(R.id.main_fl,investFragment);
                }else {
                    ft.show(investFragment);
                }
                break;
            case R.id.rb_more :
                if(moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.main_fl,moreFragment);
                }else {
                    ft.show(moreFragment);
                }
                break;
            case R.id.rb_propert :
                if(properyFragment == null) {
                    properyFragment = new ProperyFragment();
                    ft.add(R.id.main_fl,properyFragment);
                }else {
                    ft.show(properyFragment);
                }
                break;
        }
        ft.commit();

    }

    /**
     * 隐藏Fragment
     * @param ft
     */
    private void hidden (FragmentTransaction ft){
        if(homeFragment != null) {
            ft.hide(homeFragment);
        }
        if(investFragment != null) {
            ft.hide(investFragment);
        }
        if(moreFragment != null) {
            ft.hide(moreFragment);
        }
        if(properyFragment != null) {
            ft.hide(properyFragment);
        }

    }

    private void initData() {

    }

    private void initView() {
        switchFrgment(R.id.rb_main);
    }

    /**
     * 双击退出
     */
    private boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {

            if(isExit) {
                finish();
            }

            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            isExit = true;

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
            return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }
}
