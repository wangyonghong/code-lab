package me.yonghong.patterns.structural.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/10
 */
public class ProxyHandler implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object mTarget;

    public ProxyHandler(Object target) {
        this.mTarget = target;
    }

    /**
     * 方法拦截，可以进行一些额外操作
     *
     * @param proxy
     * @param method 拦截的方法
     * @param args   方法对应的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("movieShow".equals(methodName) || "tvShow".equals(methodName)) {
            if (args[0] instanceof Integer && ((int) args[0]) < 300000000) {
                System.out.println(((int) args[0]) + "块钱？！你雇 HuangZiTao 演去吧！");
                return null;
            }
        }
        if ("sing".equals(methodName)) {
            if (args[0] instanceof Integer && ((int) args[0]) < 300000000) {
                System.out.println(((int) args[0]) + "块钱？！你雇 HuangZiTao 唱去吧！");
                return null;
            }
        }

        return method.invoke(mTarget, args);
    }

    /**
     * 获取代理
     *
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(mTarget.getClass().getClassLoader(), mTarget.getClass().getInterfaces(), this);
    }
}
