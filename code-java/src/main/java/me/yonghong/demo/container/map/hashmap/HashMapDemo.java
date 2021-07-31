package me.yonghong.demo.container.map.hashmap;

import java.util.Date;
import java.util.HashMap;

/**
 * @author yonghongwang#163.com
 * @since 2021/5/18
 */
public class HashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        HashMap<LoginInfo, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUsername("li");
            loginInfo.setLoginTime(new Date());
            map.put(loginInfo, "Student");
            Thread.sleep(50);
        }
        System.out.println(map.size());
    }



}
