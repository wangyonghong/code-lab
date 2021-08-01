package me.yonghong;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/14
 **/
public class TestSpring5 {

    @Test
    public void testAdd() {
        // 1. 加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2. 获取配置创建的对象
        User user = context.getBean("user", User.class);

        System.out.println(user);
        user.add();
    }
}
