package com.example.project.smartbutler.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.smartbutler.MainActivity;
import com.example.project.smartbutler.R;
import com.example.project.smartbutler.entity.MyUser;
import com.example.project.smartbutler.utils.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_registered;
    private Button btn_login;
    private EditText et_name;
    private EditText et_password;
    private CheckBox cb_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        getRemember();

    }

    private void getRemember() {
        String name = ShareUtils.getString(this, "username", "");
        String password = ShareUtils.getString(this, "password", "");
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
            et_name.setText(name);
            et_name.setSelection(name.length());
            et_password.setText(password);
            et_password.setSelection(password.length());
            cb_remember.setChecked(true);
        }
    }

    private void initView() {

        btn_registered = findViewById(R.id.btn_registered);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_registered.setOnClickListener(this);
        cb_remember = findViewById(R.id.cb_remember);

        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btn_login:
                //获取输入窗的值
                final String name = et_name.getText().toString().trim();
                final String password = et_password.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    MyUser user = new MyUser();
                    final ProgressDialog dialog = new ProgressDialog(this);
                    dialog.setMessage("请稍候...");
                    dialog.setTitle("正在登陆");
                    dialog.show();
                    dialog.setCancelable(false);
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null) {
                                //判断邮箱是否验证
                                //   if(user.getEmailVerified()){
                                //跳转
                                dialog.dismiss();
                                setRemember(name, password);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                //   }else
                                //       Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "登陆失败" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, "提示框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setRemember(String name, String password) {
        if (cb_remember.isChecked()) {
            ShareUtils.putString(this, "username", name);
            ShareUtils.putString(this, "password", password);
        } else {
            ShareUtils.deleteShare(this, "username");
            ShareUtils.deleteShare(this, "password");
        }
    }
}
