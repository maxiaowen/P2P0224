package com.atguigu.p2p0224.fragment;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseFragment;
import com.atguigu.p2p0224.common.AppNetConfig;

/**
 * Created by Administrator on 2017/6/20.
 */

public class ProperyFragment extends BaseFragment {


    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void setContent(String json) {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
