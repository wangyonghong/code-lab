package me.yonghong.ej3.chapter2.item1;


import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

/**
 * @author yonghongwang#163.com
 * @since 2021/11/17
 **/
public class FactoryDemo {

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        // from 类型转换方法，它接受单个参数并返回此类型的相应实例
        Date date = Date.from(Instant.now());

        // of 聚合方法，接受多个参数并返回该类型的实例，并把他们合并在一起
        Set<Rank> faceCards = EnumSet.of(Rank.JACK, Rank.QUEEN, Rank.KING);

        // valueOf from和to更为详细的替代方式
        BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);

        // instance 或 getInstance，返回一个由其参数(如果有的话)描述的实例，但不能说它具有和参数相同的值
        StackWalker luke = StackWalker.getInstance();

        // create 或 newInstance，与instance 或 getInstance 类似，除了该方法保证每个调用返回一个新的实例
        Object newArray = Array.newInstance(String.class, 10);

        // getType 与getInstance类似，但是如果在工厂方法中不同的类中使用，Type是工厂方法返回的对象类型
        FileStore fs = Files.getFileStore(Path.of(""));

        // newType 与newInstance类似，但是如果在工厂方法中不同的类中使用，Type是工厂方法返回的对象类型
        BufferedReader br = Files.newBufferedReader(Path.of(""));

        Vector<String> v = new Vector<>();
        v.add("A");
        v.add("B");
        v.add("C");
        v.add("D");
        v.add("E");
        Enumeration<String> enumeration = v.elements();
        // type getType 和 newType 简洁的替代方式
        List<String> alphabet = Collections.list(enumeration);
    }

    public enum Rank {
        ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN,
        EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
}
