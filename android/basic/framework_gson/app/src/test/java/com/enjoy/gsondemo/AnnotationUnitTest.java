package com.enjoy.gsondemo;

import com.enjoy.gsondemo.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUnitTest {
    @Test
    public void testSerializedName() {
        //java对象
        User u1 = new User("Lance", "123", 18, false);
        u1.setCls(2);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(u1);
        System.out.println(json);

        User u2 = gson.fromJson(json, User.class);
        System.out.println(u2);

    }

    @Test
    public void testExpose() {
        //java对象
        User u1 = new User("Lance", "123", 18, false);
        u1.setTest1(1);
        u1.setTest2(2);
        //Gson提供的Gson对象
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //序列化
        String json = gson.toJson(u1);
        System.out.println(json);

        User u2 = gson.fromJson(json, User.class);
        System.out.println(u2);
    }
}
