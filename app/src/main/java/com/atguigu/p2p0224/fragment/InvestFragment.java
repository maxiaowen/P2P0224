package com.atguigu.p2p0224.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @Bind(R.id.vp_invest)
    ViewPager vpInvest;

    @Override
    public String getChildUrl() {
        return "";
    }

    /*
     * json 需要注意 不连网的情况下 json是没有数据的
     *
     * */
    @Override
    public void setContent(String json) {


    }

    @Override
    public void initTitle() {

    }

    private List<BaseFragment> listFragment;

    @Override
    public void initData() {
        initViewPager();
        //设置默认选中的TV
        setSelectTv(tvInvestAll);
    }


      /*
    * 设置选中的TextView
    * */
    private void setSelectTv(View view) {
        setDefaultAll(tvInvestHot);
        setDefaultAll(tvInvestRecommend);
        setDefaultAll(tvInvestAll);
        TextView tv = (TextView) view;
        tv.setTextColor(Color.RED);
        tv.setBackgroundColor(Color.GREEN);
    }

    /*
    *恢复默认textview的属性
    * */
    private void setDefaultAll(View view) {
        TextView tv = (TextView) view;
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void initListener() {
        super.initListener();

        //vp监听
        vpInvest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTv(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //textView监听
        tvInvestAll.setOnClickListener(this);
        tvInvestRecommend.setOnClickListener(this);
        tvInvestHot.setOnClickListener(this);
    }

    /*
    * 根据position切换不同的标题
    * */
    private void selectTv(int position) {
        switch (position) {
            case 0:
                setSelectTv(tvInvestAll);
                break;
            case 1:
                setSelectTv(tvInvestRecommend);
                break;
            case 2:
                setSelectTv(tvInvestHot);
                break;
        }
    }

    /*
       * 初始化fragmentAdapter
       * */
    private void initViewPager() {
        listFragment = new ArrayList<>();
        listFragment.add(new InvestAllFragment());
        listFragment.add(new InvestReFragment());
        listFragment.add(new InvesthotFragment());
        vpInvest.setAdapter(new MyAdapter(getFragmentManager()));
    }

//   @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }


    /*
    * Kotlin
    * */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_invest_all:
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_invest_recommend:
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot:
                vpInvest.setCurrentItem(2);
                break;
        }

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }
    }

}
