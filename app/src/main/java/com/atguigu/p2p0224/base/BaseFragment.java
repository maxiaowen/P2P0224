package com.atguigu.p2p0224.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.view.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/22.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getLayoutId() == 0) {
            TextView textView = new TextView(getContext());
            textView.setText("你的View哪儿去了！！");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.RED);
            textView.setTextSize(30);
            return textView;
        }
//        View view = View.inflate(getContext(),getLayoutId(),null);
//        ButterKnife.bind(this,view);
//
//        initView();
//        initTitle();
//        initData();
//        initListener();

         loadingPager = new LoadingPager(getActivity()) {
            @Override
            public int getLayoutid() {
                return getLayoutId();
            }

            @Override
            public void setResult(View successView, String json) {
                ButterKnife.bind(BaseFragment.this,successView);
                setContent(json);

            }

            @Override
            public String getUrl() {
                return  getChildUrl();
            }
        };

        return loadingPager;
    }

    public abstract String getChildUrl();

    public abstract void setContent(String json);


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getLayoutId() != 0) {
            //连接网络
            loadingPager.loadNet();
        }

    }

    public abstract void initTitle();

    /*
    * 重写
    * */
    private void initListener() {

    }

    public abstract void initData();

    /*
    * 可以重写
    *
    * */
    private void initView() {

    }


    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
