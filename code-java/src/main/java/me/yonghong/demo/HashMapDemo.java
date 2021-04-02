package me.yonghong.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author yonghongwang#163.com
 * @since 2021/3/24
 */
public class HashMapDemo {

    public static void main(String[] args) {
        testHashMap1();
    }

    private static void testHashMap1() {
        Map<RandomPoint1, Integer> map = new HashMap<>();
        RandomPoint1 randomPoint1 = new RandomPoint1(RandomUtils.nextInt(), RandomUtils.nextInt());
        String addr = randomPoint1.toString();
        Integer random = RandomUtils.nextInt();
        map.put(randomPoint1, random);
        while (true) {
            map.put(new RandomPoint1(RandomUtils.nextInt(), RandomUtils.nextInt()), RandomUtils.nextInt());
            System.out.println(randomPoint1.toString());
            System.out.println(map.get(randomPoint1));
            if (!addr.equals(randomPoint1.toString())) {
                throw new RuntimeException("地址变了");
            }
            System.out.println(map.size());
            if (map.size() == 50000) {
                map = new HashMap<>();
                map.put(randomPoint1, random);
            }
        }
    }

}

class RandomPoint1 {

    private Integer x;
    private Integer y;

    public RandomPoint1(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}

class RandomPoint2 {

    private Integer x;
    private Integer y;

    public RandomPoint2(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        RandomPoint2 that = (RandomPoint2)o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
