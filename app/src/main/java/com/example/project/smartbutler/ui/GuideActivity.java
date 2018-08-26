package com.example.project.smartbutler.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.project.smartbutler.MainActivity;
import com.example.project.smartbutler.R;
import com.example.project.smartbutler.utils.L;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    //容器
    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3;
    private ImageView iv_back;

    //小园点
    private ImageView point1, point2, point3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    //初始化View
    private void initView() {
        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        iv_back = findViewById(R.id.iv_back);
        //设置默认图片
        setPointImg(true, false, false);

        mViewPager = findViewById(R.id.mViewPager);

        view1 = View.inflate(this, R.layout.pager_item_one, null);
        view2 = View.inflate(this, R.layout.pager_item_two, null);
        view3 = View.inflate(this, R.layout.pager_item_three, null);

        view3.findViewById(R.id.btn_start).setOnClickListener(this);
        iv_back.setOnClickListener(this);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());

        //监听viewpager滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                L.i("position:" + position, this.getClass().getName());
                switch (position) {
                    case 0:
                        iv_back.setVisibility(View.VISIBLE);
                        setPointImg(true, false, false);
                        break;
                    case 1:
                        iv_back.setVisibility(View.VISIBLE);
                        setPointImg(false, true, false);
                        break;
                    case 2:
                        iv_back.setVisibility(View.GONE);
                        setPointImg(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //设置小圆点选中图片
    private void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3) {
        if (isCheck1) {
            point1.setImageResource(R.drawable.point_on);
        } else {
            point1.setImageResource(R.drawable.point_off);
        }
        if (isCheck2) {
            point2.setImageResource(R.drawable.point_on);
        } else {
            point2.setImageResource(R.drawable.point_off);
        }
        if (isCheck3) {
            point3.setImageResource(R.drawable.point_on);
        } else {
            point3.setImageResource(R.drawable.point_off);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.btn_start:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }
    }
}
