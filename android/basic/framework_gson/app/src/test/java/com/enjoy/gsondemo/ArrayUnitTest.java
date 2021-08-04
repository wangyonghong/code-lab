package com.enjoy.gsondemo;

import com.enjoy.gsondemo.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArrayUnitTest {
    @Test
    public void testArray() {
        User[] users1 = new User[3];
        //java对象
        users1[0] = new User("Lance", "123", 18, false);
        users1[1] = new User("Alex", "123", 88, true);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(users1);
        System.out.println("序列化:"+json);

        User[] users2 = gson.fromJson(json, User[].class);
        System.out.println("反序列化0:"+users2[0]);
        System.out.println("反序列化1:"+users2[1]);
        System.out.println("反序列化2:"+users2[2]);

    }

    @Test
    public void testListObject() {
        List<User> list1 = new ArrayList<>();
        list1.add(new User("Lance", "123", 18, false));
        list1.add(new User("Alex", "123", 88, true));
        list1.add(null);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(list1);
        System.out.println("序列化:"+json);

        //反序列化
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> list2 = gson.fromJson(json, type);
        System.out.println("反序列化0:"+list2.get(0).getUserName());
        System.out.println("反序列化1:"+list2.get(1));
        System.out.println("反序列化2:"+list2.get(2));
    }
}
