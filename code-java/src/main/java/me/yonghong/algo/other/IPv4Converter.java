package me.yonghong.algo.other;

import me.yonghong.algo.Solution;

/**
 * IPv4 与 int 转换
 *
 * @author yonghongwang#163.com
 * @since 2021/8/2
 **/
public class IPv4Converter implements Solution {

    public static void main(String[] args) {
        new IPv4Converter().test();
    }

    @Override
    public void test() {
        System.out.printf("%32s\n", Integer.toBinaryString(-1));
        System.out.printf("%32s\n", Integer.toBinaryString(0));
        System.out.printf("%32s\n", Integer.toBinaryString(255));
        System.out.printf("%32s\n", Integer.toBinaryString(255 << 8));
        System.out.printf("%32s\n", Integer.toBinaryString(255 << 16));
        System.out.printf("%32s\n", Integer.toBinaryString(255 << 24));


        // IPv4 转 int
        System.out.println(toInt("127.0.0.1"));
        System.out.println(toInt("192.168.0.0"));
        System.out.println(toInt("192.168.0.1"));
        System.out.println(toInt("255.255.255.255"));
        // int 转 IPv4
        System.out.println(toIPv4(16777343));
        System.out.println(toIPv4(43200));
        System.out.println(toIPv4(16820416));
        System.out.println(toIPv4(-1));
    }

    /**
     * IPv4 转 int
     *
     * @param ip
     * @return
     */
    public int toInt(String ip) {
        int res = 0;
        String[] ipSlices = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            int pos = i * 8;
            int temp = Integer.parseInt(ipSlices[i]) << pos;
            res = res | temp;
        }
        return res;
    }

    /**
     * int 转 IPv4
     *
     * @param ip
     * @return
     */
    public String toIPv4(int ip) {
        String[] ipSlices = new String[4];
        for (int i = 0; i < 4; i++) {
            int pos = i * 8;
            int temp = ip & (255 << pos);
            temp = temp >>> pos;
            ipSlices[i] = String.valueOf(temp);
        }
        return String.join(".", ipSlices);
    }
}
