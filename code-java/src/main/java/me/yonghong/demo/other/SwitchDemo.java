package me.yonghong.demo.other;

/**
 * @author yonghongwang#163.com
 */
public class SwitchDemo {
    public static void main(String[] args) {
        switchMethod("something");
        switchMethod("null");
        // 传 null 会抛出异常，而不是 default
        switchMethod(null);
    }

    public static void switchMethod(String param) {

        switch (param) {
            case "something":
                System.out.println("something");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }
}
