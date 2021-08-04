package com.enjoy.gsondemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    private String userName;
    @Expose
    private String password;
    @Expose
    private int age;
    @Expose
    private boolean isStudent;


    @Expose
    private Job job;


    //serialize：是否参与序列化，deserialize是否参与反序列化
    @Expose(serialize = false, deserialize = false)
    private int test1;

    private transient int test2;

    @Expose
    //无法以class作为字段名
    @SerializedName("class")
    private int cls;


    public User(String userName, String password, int age, boolean isStudent) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.isStudent = isStudent;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public void setTest2(int test2) {
        this.test2 = test2;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", job=" + job +
                ", test1=" + test1 +
                ", test2=" + test2 +
                ", cls=" + cls +
                '}';
    }
}
