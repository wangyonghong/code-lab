package me.yonghong.interview;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/17
 **/
public class TestSumN {

    public static void main(String[] args) {

        // System.out.println(sum(4));
        System.out.println(compare("9.8", "9.8.0"));
    }

    public static int sum(int n) {
        boolean b = n > 0 && (n += sum(n - 1)) > 0;
        return n;
    }

    public static int compare(String a, String b) {
        if (a.equals(b)) {
            System.out.println("相等");
            return 0;
        }
        String[] version1 = a.split("\\.");
        String[] version2 = b.split("\\.");
        int length1 = version1.length;
        int length2 = version2.length;

        int maxLength = Math.max(length1, length2);
        int[] version1Int = new int[maxLength];
        int[] version2Int = new int[maxLength];
        for (int i = 0; i < length1; i++) {
            version1Int[i] = Integer.parseInt(version1[i]);
        }
        for (int i = 0; i < length2; i++) {
            version2Int[i] = Integer.parseInt(version2[i]);
        }

        for (int i = 0; i < maxLength; i++) {
            if (version1Int[i] > version2Int[i]) {
                System.out.println("第一个大");
                return 1;
            } else if (version1Int[i] < version2Int[i]) {
                System.out.println("第二个大");
                return -1;
            }
        }
        return 0;
    }
}
