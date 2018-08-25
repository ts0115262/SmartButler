package com.example.project.smartbutler.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project.smartbutler.MainActivity;
import com.example.project.smartbutler.R;
import com.example.project.smartbutler.utils.ShareUtils;
import com.example.project.smartbutler.utils.StaticClass;
import com.example.project.smartbutler.utils.UtilTools;

public class SplashActivity extends AppCompatActivity {
    /**
     * 1.延时2000毫秒
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity自定义主题
     */

    private TextView tv_splash;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    finish();
                    break;
            }
        }
    };

    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHRAE_ISFRIST, true);
        if (isFirst) ShareUtils.putBoolean(SplashActivity.this, StaticClass.SHRAE_ISFRIST, false);
        return isFirst;
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

    }

    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
        tv_splash = findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(this,tv_splash);
    }
}
