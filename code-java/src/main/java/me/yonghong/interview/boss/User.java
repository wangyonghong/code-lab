package me.yonghong.interview.boss;

import java.util.Iterator;
import java.util.List;

public class User {

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public static void remove(List<User> userList) {
        userList.removeIf(user -> user.getAge() > 20);
    }

    public static void remove2(List<User> userList) {
        Iterator<User> it = userList.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getAge() > 20) {
                it.remove();
            }
        }
    }
}
