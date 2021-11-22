package me.yonghong.feature.java15;

public record Point(int x, int y) {

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        System.out.println(point.toString());
    }
}

