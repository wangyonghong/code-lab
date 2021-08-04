package com.derry.simplestudy.simple03;

import java.io.Serializable;

// 必须实现Serializable接口，此对象才有传递的资格
public class Student implements Serializable {

    public int id;
    public String name;
    public int age;

}
