package me.yonghong.patterns.structural.proxy;

/**
 * 代理模式
 *
 * @author yonghongwang#163.com
 * @see java.lang.reflect.Proxy
 * @since 2021/8/6
 */
public class App {

    public static void main(String[] args) {
        var proxy = new WizardTowerProxy(new IvoryTower());
        proxy.enter(new Wizard("Red wizard"));
        proxy.enter(new Wizard("White wizard"));
        proxy.enter(new Wizard("Black wizard"));
        proxy.enter(new Wizard("Green wizard"));
        proxy.enter(new Wizard("Brown wizard"));
    }
}
