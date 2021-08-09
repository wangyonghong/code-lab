package me.yonghong.patterns.structural.adapter;

import java.util.Collection;
import java.util.Enumeration;

/**
 * 适配器模式
 *
 * @author yonghongwang#163.com
 * @see java.util.Arrays#asList(Object[])
 * @see java.util.Collections#list(Enumeration)
 * @see java.util.Collections#enumeration(Collection)
 * @since 2021/8/6
 */
public class App {

    private App() {
    }

    public static void main(final String[] args) {
        var captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
