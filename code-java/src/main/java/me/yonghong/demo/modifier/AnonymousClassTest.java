package me.yonghong.demo.modifier;

/**
 * @author yonghongwang#163.com
 */
public class AnonymousClassTest {

    private static final String TEST = "TEST";

    public static void main(String[] args) {
        DoSomething doSomething = new DoSomething();
        doSomething.run(new OutClassCallback());
        doSomething.run(new InnerClassCallback());
        // 匿名内部类
        doSomething.run(new Callback() {
            @Override
            public void doCallback() {
                fun1();
                System.out.print("匿名内部类的Callback");
                printClass();
            }
        });
        // lambda表达式
        doSomething.run(() -> {
            fun1();
            System.out.print("匿名内部类的Callback");
        });
    }

    private static void fun1() {
        System.out.println("调用外层类的方法");
    }

    static class InnerClassCallback implements Callback {
        @Override
        public void doCallback() {
            System.out.print("内部类的Callback");
            System.out.println(TEST);
            printClass();
        }
    }
}

interface Callback {

    /**
     * 执行回调的内容
     */
    void doCallback();

    /**
     * 打印类名
     */
    default void printClass() {
        System.out.println(" ### 类名是：" + getClass());
        System.out.println();
    }
}

class OutClassCallback implements Callback {
    @Override
    public void doCallback() {
        System.out.print("外部类的Callback");
        printClass();
    }
}

class DoSomething {
    void run(Callback callback) {
        System.out.println("我要做点啥事");
        // 做完之后要调用回调
        callback.doCallback();
    }
}