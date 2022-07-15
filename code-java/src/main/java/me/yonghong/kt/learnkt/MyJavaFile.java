package me.yonghong.kt.learnkt;

/**
 * Interoperability Example
 */
public class MyJavaFile {

    public static void main(String[] args) {
        int sum = MyKotlinFile.addNumbers(3, 4);
        System.out.println("Printing sum from Java file :" + sum);
    }

    public static int getArea(int l, int b) {
        return l * b;
    }
}
