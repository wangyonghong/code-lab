package me.yonghong.demo.container.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableMapTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(map);
        System.out.println(unmodifiableMap);
        map.put("2", "2");
        try {
            unmodifiableMap.put("1", "4");
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
        }
        System.out.println(unmodifiableMap);
    }
}
