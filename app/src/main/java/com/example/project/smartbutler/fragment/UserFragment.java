package com.example.project.smartbutler.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.smartbutler.R;
import com.example.project.smartbutler.entity.MyUser;

import cn.bmob.v3.BmobUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private Button btn_exit_user;
    private TextView tv_user;
    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_disc;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        findView(view);

        return view;
    }

    private void findView(View view) {

        btn_exit_user = view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);

        tv_user = view.findViewById(R.id.edit_user);
        tv_user.setOnClickListener(this);

        et_username = view.findViewById(R.id.et_username);
        et_age = view.findViewById(R.id.et_age);
        et_disc = view.findViewById(R.id.et_desc);
        et_sex = view.findViewById(R.id.et_sex);

        //默认不可输入

        et_username.setEnabled(false);
        et_sex.setEnabled(false);
        et_disc.setEnabled(false);
        et_age.setEnabled(false);

        //设置值

        MyUser userInfo = MyUser.getCurrentUser(MyUser.class);

        et_username.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge());
        et_sex.setText(userInfo.getSex() ? "男" : "女");
        et_disc.setText(userInfo.getDecs());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit_user:
                //退出登录
                //清除缓存用户对象
                MyUser.logOut();
                //现在的currentUser是null
                BmobUser currentUser = MyUser.getCurrentUser();
                break;

            //编辑资料
            case R.id.edit_user:

        }
    }
}
