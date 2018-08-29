package com.example.project.smartbutler.entity;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {

    private int age;
    private boolean sex;
    private String decs;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }
}
