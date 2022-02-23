package me.yonghong.demo.basic;

/**
 * @author yonghongwang#163.com
 * @since 2022/02/14
 **/
public class CloneableTest implements Cloneable {

    // 声明变量
    String name;
    int likes;

    public static void main(String[] args) {

        // 创建对象
        CloneableTest obj1 = new CloneableTest();

        // 初始化变量
        obj1.name = "Runoob";
        obj1.likes = 111;

        // 打印输出
        System.out.println(obj1.name); // Runoob
        System.out.println(obj1.likes); // 111

        try {

            // 创建 obj1 的拷贝
            CloneableTest obj2 = (CloneableTest) obj1.clone();

            // 使用 obj2 输出变量
            System.out.println(obj2.name); // Runoob
            System.out.println(obj2.likes); // 111

            System.out.println(obj1.name == obj2.name ? "浅拷贝" : "深拷贝");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneableTest clone = (CloneableTest) super.clone();
        // String没有继承Cloneable接口, 也没有重写clone方法, 它无法深克隆, 那怎么对它进行深克隆呢?
        // 我觉得答案是不需要对String进行刻意的深克隆, 因为String类在堆内存中有相应的字符串池String Pool,
        // 设想如果你给cloneTest2的nameString做出了修改, 那它就会指向相应的字符串引用,
        // 该引用可能在字符串池也可能在堆内存(取决于你如何创建String对象). 所以String类是没必要实现Cloneable接口的.
        clone.name = new String(name);
        return clone;
    }
}
