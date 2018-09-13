package com.example.project.smartbutler.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.smartbutler.R;
import com.example.project.smartbutler.entity.MyUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

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

    private Button btn_update_ok;


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
        btn_update_ok = view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

        //默认不可输入

        setEnable(false);

        //设置值

        MyUser userInfo = MyUser.getCurrentUser(MyUser.class);

        et_username.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge() + "");
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
                if (currentUser == null) {
                    Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else
                    Toast.makeText(getActivity(), "退出失败", Toast.LENGTH_SHORT).show();

                break;

            //编辑资料
            case R.id.edit_user:
                setEnable(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update_ok:

                //拿到输入框的值
                String username = et_username.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String sex = et_sex.getText().toString().trim();
                String disc = et_disc.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(age) & !TextUtils.isEmpty(sex) & (sex.equals("男") || sex.equals("女"))) {
                    //更新属性
                    MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    if (sex.equals("男"))
                        user.setSex(true);
                    else if (sex.equals("女"))
                        user.setSex(false);
                    if (TextUtils.isEmpty(disc)) {
                        disc = "这个人很懒什么都没有留下";
                        user.setDecs(disc);
                        et_disc.setText(disc);
                    }else
                        user.setDecs(disc);
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "更新信息成功", Toast.LENGTH_SHORT).show();
                                setEnable(false);
                            } else {
                                Toast.makeText(getActivity(), "更新信息失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    void setEnable(boolean is) {
        et_username.setEnabled(is);
        et_sex.setEnabled(is);
        et_disc.setEnabled(is);
        et_age.setEnabled(is);
    }
}
