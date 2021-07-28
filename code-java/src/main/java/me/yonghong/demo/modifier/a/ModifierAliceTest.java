package me.yonghong.demo.modifier.a;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/18
 */
public class ModifierAliceTest {

    protected void testAlice() {
        System.out.println(ModifierAliceTest.class);
    }

    public static void main(String[] args) {
        new ModifierAliceTest().testAlice();
    }
}
