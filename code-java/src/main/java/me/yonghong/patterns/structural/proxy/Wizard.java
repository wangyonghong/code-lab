package me.yonghong.patterns.structural.proxy;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class Wizard {

    private final String name;

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
