package com.enjoy.gsondemo;

import com.enjoy.gsondemo.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUnitTest {
    @Test
    public void testMap() {
        Map<String, User> map1 = new HashMap<>();
        //java对象
        map1.put("1", new User("Lance", "123", 18, false));
        map1.put("2", new User("Alex", "123", 88, true));
        map1.put("3", null);
        map1.put(null, null);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(map1);
        System.out.println(json);

        Type type = new TypeToken<Map<String, User>>() {
        }.getType();
        Map<String, User> map2 = gson.fromJson(json, type);
        System.out.println(map2.get(null));
        System.out.println(map2.get("1"));
    }

    @Test
    public void testSet() {
        Set<User> set1 = new HashSet<>();
        set1.add(new User("Lance", "123", 18, false));
        set1.add(new User("Alex", "123", 88, true));
        set1.add(null);
        //Gson提供的Gson对象
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(set1);
        System.out.println(json);

        //反序列化
        Type type = new TypeToken<Set<User>>() {
        }.getType();
        Set<User> set2 = gson.fromJson(json, type);
        Iterator<User> iterator = set2.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            System.out.println("反序列化：" + next);
        }

    }
}
