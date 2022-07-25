package me.yonghong.algo;

import com.google.common.collect.Lists;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionUtils {

    private static final List<Solution> solutions = new ArrayList<>();

    public static void runTest(Class<?> clazz) {
        register(clazz);
        Collections.sort(solutions);
        solutions.forEach(solution -> {
            System.out.println(solution.getClass().getCanonicalName());
            solution.test();
            System.out.println();
        });
    }

    private static void register(Class<?> clazz) {
        ArrayList<Class<?>> interfaceImpls = getInterfaceImpls(clazz);
        for (Class<?> interfaceImpl : interfaceImpls) {
            try {
                Object instance = interfaceImpl.getDeclaredConstructor().newInstance();
                solutions.add((Solution) instance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个接口的所有实现类
     */
    private static ArrayList<Class<?>> getInterfaceImpls(Class<?> target) {
        ArrayList<Class<?>> subclasses = Lists.newArrayList();
        try {
            // 判断class对象是否是一个接口
            if (target.isInterface()) {
                ArrayList<String> basePackageList = Lists.newArrayList();
                String basePackageJava = SolutionUtils.class.getResource("").getPath();
                basePackageList.add(basePackageJava);
                String basePackageKotlin = basePackageJava.replace("classes/java/main/", "classes/kotlin/main/");
                basePackageList.add(basePackageKotlin);
                // 存放class路径的list
                ArrayList<String> classPaths = Lists.newArrayList();
                for (String basePackage : basePackageList) {
                    listPackages(basePackage, SolutionUtils.class.getPackageName(), classPaths);
                }

                // 获取所有类,然后判断是否是 target 接口的实现类
                for (String classpath : classPaths) {
                    Class<?> classObject = Class.forName(classpath);
                    if (classObject.getSuperclass() == null) continue; // 判断该对象的父类是否为null
                    Set<Class<?>> interfaces = new HashSet<>(Arrays.asList(classObject.getInterfaces()));
                    if (interfaces.contains(target)) {
                        subclasses.add(Class.forName(classObject.getName()));
                    }
                }
            } else {
                throw new IllegalArgumentException("Class对象不是一个interface");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return subclasses;
    }

    /**
     * 获取项目编译后的所有的.class的字节码文件
     * 这么做的目的是为了让 Class.forName() 可以加载类
     *
     * @param basePackage 默认包名
     * @param classes     存放字节码文件路径的集合
     */
    private static void listPackages(String basePath, String basePackage, List<String> classes) {
        File file = new File(basePath);
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                listPackages(child.getPath(), basePackage + "." + child.getName(), classes);
            }
        } else {
            // 如果不是一个目录,判断是不是以.class结尾的文件,如果不是则不作处理
            String classpath = file.getName();
            if (".class".equals(classpath.substring(classpath.length() - ".class".length()))) {
                classes.add(basePackage.replaceAll(".class", ""));
            }
        }
    }
}
