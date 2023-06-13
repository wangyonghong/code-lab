package com.nien.demo.basic.threadlocal;

import com.nien.demo.util.Logger;
import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;
import lombok.Data;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    @Data
    static class Foo {
        //实例总数
        static final AtomicInteger AMOUNT = new AtomicInteger(0);
        int index = 0;  //对象的编号
        int bar = 10; //对象的内容

        //构造器
        public Foo() {
            index = AMOUNT.incrementAndGet(); //总数增加，并且给对象的编号
        }

        @Override
        public String toString() {
            return index + "@Foo{bar=" + bar + '}';
        }
    }

    //定义线程本地变量
    private static final ThreadLocal<Foo> LOCAL_FOO = new ThreadLocal<Foo>();

    @Test
    public void testThreadLocal() throws InterruptedException {
//        ThreadLocal<Foo> localFoo = ThreadLocal.withInitial(() -> new Foo());

        //获取自定义的混合线程池
        ThreadPoolExecutor threadPool = ThreadUtil.getMixedTargetThreadPool();

        //共5个线程
        for (int i = 0; i < 5; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //获取“线程本地变量”中当前线程所绑定的值
                    if (LOCAL_FOO.get() == null) {
                        //设置“线程本地变量”中当前线程所绑定的值
                        LOCAL_FOO.set(new Foo());
                    }

                    Print.tco("初始的本地值：" + LOCAL_FOO.get());
                    //每个线程执行10次
                    for (int i = 0; i < 10; i++) {
                        Foo foo = LOCAL_FOO.get();
                        foo.setBar(foo.getBar() + 1);
                        ThreadUtil.sleepMilliSeconds(10);

                    }
                    Print.tco("累加10次之后的本地值：" + LOCAL_FOO.get());

                    //删除“线程本地变量”中当前线程所绑定的值，对于线程池中的线程尤其重要
                    LOCAL_FOO.remove();
                }
            });
        }

        ThreadUtil.sleepMilliSeconds(Integer.MAX_VALUE);
    }

    static class LeakFoo extends Foo {

        private final Byte[] toLeak;

        public LeakFoo() {
            super();
            toLeak = new Byte[1024 * 1024];
        }

        @Override
        protected void finalize() {
            Print.tco(super.toString());

        }
    }

    @Test
    public void testLeak() throws InterruptedException {
        //获取自定义的混合线程池
        ThreadPoolExecutor threadPool = ThreadUtil.getMixedTargetThreadPool();
        //共1000个任务
        for (int i = 0; i < 1000; i++) {
            threadPool.submit(new Runnable() {

                @Override
                public void run() {

                    //每个任务执行1000次
                    for (int i = 0; i < 1000; i++) {
                        ThreadLocal<LeakFoo> localFoo = new ThreadLocal<LeakFoo>();
                        if (null == localFoo.get()) {
                            localFoo.set(new LeakFoo());
                        }
                        LeakFoo foo = localFoo.get();
                    }
                }
            });
        }
    }

    public ThreadLocal<String> local = ThreadLocal.withInitial(() -> "foo");

    @Test
    public void testFinalThreadLocal() {
        //设置本地变量的值
        local.set("bar");
        Logger.fo("local:" + local.get());
        funcB();
        funcC();
    }

    public void funcB() {
        //修改本地变量的引用地址
        local = new ThreadLocal<>();
        Logger.fo("local:" + local.get());
    }

    public void funcC() {
        Logger.fo("local:" + local.get());
    }

    @Test
    public void testWeakReference() {
        WeakReference<Foo> weakReference = new WeakReference<>(new Foo());

        if (weakReference.get() != null) {
            System.out.println("GC 前非空");
        }
        System.gc(); // 垃圾回收，释放弱引用的内存

        if (weakReference.get() != null) {
            System.out.println("GC 后 非空");
        } else {
            System.out.println("GC 后 已空");
        }
    }

    static class WeakReferenceFoo extends WeakReference<Foo> {
        //构造器，传入需要引用的对象
        public WeakReferenceFoo(Foo referent) {
            super(referent);
        }
    }

    @Test
    public void testWeakReference2() {
        WeakReference<Foo> weakReference = new WeakReferenceFoo(new Foo());
        System.gc(); // 垃圾回收，释放弱引用的内存
        if (weakReference.get() != null) {
            System.out.println("GC 后 非空");
        } else {
            System.out.println("GC 后 已空");
        }
    }

    @Test
    public void callFuncA() {
        funcA();
        //函数末尾
    }

    public void funcA() {
        //创建一个线程本地变量
        ThreadLocal<Foo> local = new ThreadLocal<Foo>();
        //设置值
        local.set(new Foo());
        //获取值
        local.get();
        //函数末尾
    }

    private static final Foo SF_FOO = new Foo();

    @Test
    public void testWeakReference3() {
        WeakReference<Foo> weakReference = new WeakReferenceFoo(SF_FOO);
        System.gc(); // 垃圾回收，释放弱引用的内存
        if (weakReference.get() != null) {
            System.out.println("GC 后 非空");
        } else {
            System.out.println("GC 后 已空");
        }
    }

}