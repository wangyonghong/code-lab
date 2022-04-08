package me.yonghong.demo.cl;

import java.lang.reflect.Method;

/**
 * @author yonghongwang#163.com
 * @since 2022/03/31
 **/
public class ClassLoaderUtils {

    /**
     * 纯Java会返回 false、true
     * Android会返回 true、true
     */
    public static void main(String[] args) {
        boolean classLoaded;
        classLoaded = isClassLoaded("me.yonghong.demo.cl.Test");
        System.out.println(classLoaded);
        new Test();
        classLoaded = isClassLoaded("me.yonghong.demo.cl.Test");
        System.out.println(classLoaded);
    }

    public static boolean isClassLoaded(String className) {
        try {
            Method m = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[]{String.class});
            m.setAccessible(true);
            ClassLoader classLoader = ClassLoaderUtils.class.getClassLoader();
            Object cls = m.invoke(classLoader, className);
            return cls != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
