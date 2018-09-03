package com.example.project.smartbutler.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.smartbutler.R;
import com.example.project.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_forget_password;
    private EditText et_email;
    private Button btn_update_password;
    private EditText et_now;
    private EditText et_new;
    private EditText et_new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initView();
    }

    private void initView() {
        btn_forget_password = findViewById(R.id.btn_forget_password);
        et_email = findViewById(R.id.et_email);
        btn_update_password = findViewById(R.id.btn_update_password);
        et_now = findViewById(R.id.et_now);
        et_new = findViewById(R.id.et_new);
        et_new_password = findViewById(R.id.et_new_password);

        btn_forget_password.setOnClickListener(this);
        btn_update_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_forget_password:
                //获取输入框邮箱
                final String email = et_email.getText().toString();
                //判断是否为空
                if (!TextUtils.isEmpty(email)) {
                    //
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ForgetPasswordActivity.this, "邮件已经发送至" + email, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ForgetPasswordActivity.this, "邮件发送失败" + email, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_update_password:
                String now = et_now.getText().toString();
                String newPassoword = et_new.getText().toString();
                String new_password = et_new_password.getText().toString();
                if (!TextUtils.isEmpty(now)&&!TextUtils.isEmpty(new_password)&&!TextUtils.isEmpty(newPassoword)){
                    if (new_password.equals(newPassoword)){
                        MyUser.updateCurrentUserPassword(now, new_password, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null){
                                    Toast.makeText(ForgetPasswordActivity.this, "修改完成", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(ForgetPasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this, "两次输入密码不想等", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
