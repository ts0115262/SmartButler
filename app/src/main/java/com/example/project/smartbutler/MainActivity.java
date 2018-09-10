package com.example.project.smartbutler;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.project.smartbutler.fragment.ButlerFragment;
import com.example.project.smartbutler.fragment.GirlFragment;
import com.example.project.smartbutler.fragment.UserFragment;
import com.example.project.smartbutler.fragment.WeChatFragment;
import com.example.project.smartbutler.ui.SettingActivity;
import com.example.project.smartbutler.utils.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    //TableLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //TiTle
    private List<String> mTitle;

    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗
    private FloatingActionButton fab_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉阴影
        getSupportActionBar().setElevation(0);

        initDate();
        initView();

    }

    private void initDate() {
        mTitle = new ArrayList<>();
        mTitle.add("服务管家");
        mTitle.add("微信精选");
        mTitle.add("美女社区");
        mTitle.add("个人中心");

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WeChatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());

    }

    private void initView() {
        mTabLayout = findViewById(R.id.mTabLayout);
        mViewPager = findViewById(R.id.mViewPager);
        fab_setting = findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        fab_setting.setVisibility(View.GONE);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());


        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                L.d("onPageSelected: " + position, TAG);
                if (position == 0) {
                    fab_setting.setVisibility(View.GONE);
                } else {
                    fab_setting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的Item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }


            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}
