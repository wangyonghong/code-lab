package com.nien.demo.petstore.store;


import com.nien.demo.util.RandomUtil;

import java.util.Comparator;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class Customer implements Comparable {

    private String name;//名称，内部唯一
    private String nickName;//昵称，用户可见
    private String passWord;//密码
    private Gender gender;//性别
    private int age;//年龄
    private int level;//级别

    @Override
    public int compareTo(Object o) {
        if (o == null) throw new NullPointerException("user object is null");
        Customer user = (Customer) o;

//        按照名字的字母顺序排序，区分大小写
        return this.name.compareTo(user.name);

    }

    public static class AgeComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            if (o1 == null || o2 == null)
                return 0;
            return o1.age - o2.age;
        }


    }

    public static class NameComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            if (o1 == null || o2 == null)
                return 0;
            String name1 = o1.name;
            String name2 = o2.name;
            //按照字母顺序的先后排序，区分大小写
            return name1.compareTo(name2);
            //按照字母顺序的先后排序，不区分大小写
//            return name1.toUpperCase().compareTo(name2.toUpperCase());
        }
    }


    public enum Gender {
        MALE, FEMALE;

        public static Gender randGender() {
            int length = values().length;
            int typeNo = RandomUtil.randInMod(length) - 1;
            return values()[typeNo];
        }
    }

    public Customer(String name, String nickName, String passWord, Gender gender, int age) {
        this.name = name;
        this.nickName = nickName;
        this.passWord = passWord;
        this.gender = gender;
        this.age = age;
    }

    public Customer(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
        this.passWord = "123456";
        this.gender = Gender.randGender();
        this.age = RandomUtil.randInMod(100);
        this.level = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer user = (Customer) o;

        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {


    }


}
