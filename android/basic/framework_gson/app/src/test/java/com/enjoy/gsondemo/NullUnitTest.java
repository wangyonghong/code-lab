package com.enjoy.gsondemo;

import com.enjoy.gsondemo.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NullUnitTest {
    @Test
    public void testNull() {
        User u1 = new User("Lance", "123", 18, false);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(u1);
        System.out.println(json);

        User u2 = gson.fromJson(json, User.class);
        System.out.println(u2);
    }

    @Test
    public void testListObject() {
        List<User> list1 = new ArrayList<>();
        list1.add(new User("Lance", "123", 18, false));
        list1.add(null);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(list1);
        System.out.println("序列化："+json);

        System.out.println("反序列化：");
        //反序列化
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> list2 = gson.fromJson(json, type);
        System.out.println(list2.get(0));
        System.out.println(list2.get(1));
    }
}
