package com.enjoy.gsondemo;

import com.enjoy.gsondemo.bean.Job;
import com.enjoy.gsondemo.bean.User;
import com.google.gson.Gson;

import org.junit.Test;

public class ObjectUnitTest {
    @Test
    public void testObject() {
        //java对象
        User u1 = new User("Lance", "123", 18, false);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(u1);
        System.out.println("序列化：" + json);

        //反序列化
        User u2 = gson.fromJson(json, User.class);
        System.out.println("反序列化:" + u2.getUserName() + "-" + u2.getPassword());

    }

    @Test
    public void testNestedObject() {
        //java对象
        User u1 = new User("Lance", "123", 18, false);
        Job job = new Job("工人", 10000);
        u1.setJob(job);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(u1);
        System.out.println("序列化：" + json);

        User u2 = gson.fromJson(json, User.class);
        System.out.println("反序列化:" + u2.getUserName() + "-" + u2.getPassword() +"-"+u2.getJob());
    }
}
