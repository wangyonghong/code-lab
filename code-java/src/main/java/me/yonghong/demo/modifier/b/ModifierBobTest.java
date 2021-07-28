package me.yonghong.demo.modifier.b;

import me.yonghong.demo.modifier.a.ModifierAliceTest;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/18
 */
public class ModifierBobTest extends ModifierAliceTest {

    protected void testBob() {
        testAlice();
    }

    public static void main(String[] args) {
        new ModifierBobTest().testBob();
        //new ModifierAliceTest().testAlice();
    }
}
