package me.yonghong.interview.col;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/14
 **/
public class Test {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.set(1, 3);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
