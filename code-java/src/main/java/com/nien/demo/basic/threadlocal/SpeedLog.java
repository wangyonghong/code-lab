package com.nien.demo.basic.threadlocal;

import com.nien.demo.util.Print;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 如果不用于线程池，单独使用，在方法的入口前执行beginSpeedLog()方法，
 * 在需要计算耗时的检查点，执行logPoint(String point) 方法，记录与上一点之间的耗时
 * 在方法调用后，执行endSpeedLog()方法方法，清除线程局部变量
 * <p>
 * AOP（面向切面编程）中，可以使用注解方式通过切入点执行logPoint(String point)方法，
 * 依旧可以获得方法的执行耗时。
 */
public class SpeedLog {
    /**
     * 记录调用耗时的本地Map变量
     */
    private static final ThreadLocal<Map<String, Long>> TIME_RECORD_LOCAL =
            ThreadLocal.withInitial(SpeedLog::initialStartTime);

    /**
     * 记录调用耗时的本地Map变量的初始化方法
     */
    public static Map<String, Long> initialStartTime() {
        Map<String, Long> map = new HashMap<>();
        map.put("start", System.currentTimeMillis());
        map.put("last", System.currentTimeMillis());
        return map;
    }


    /**
     * 开始耗时记录
     */
    public static final void beginSpeedLog() {
        Print.fo("开始耗时记录");
        TIME_RECORD_LOCAL.get();
    }

    /**
     * 结束耗时记录
     */
    public static final void endSpeedLog() {
        TIME_RECORD_LOCAL.remove();
        Print.fo("结束耗时记录");
    }

    /**
     * 记录方法的耗时
     */
    public static final void logPoint(String point) {
        //获取上一次的时间
        Long last = TIME_RECORD_LOCAL.get().get("last");
        //计算耗时，并且保存
        Long cost = System.currentTimeMillis() - last;
        TIME_RECORD_LOCAL.get().put(point + " cost:", cost);
        //保存最近时间，供下一次使用
        TIME_RECORD_LOCAL.get().put("last", System.currentTimeMillis());
    }

    /**
     * print方法的耗时
     */
    public static final void printCost() {

        Iterator<Map.Entry<String, Long>> it =
                TIME_RECORD_LOCAL.get().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> entry = it.next();
            Print.fo(entry.getKey() + " =>" + entry.getValue());
        }
    }


}