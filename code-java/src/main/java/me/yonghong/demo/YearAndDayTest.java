package me.yonghong.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yonghongwang#163.com
 * @since 2021/3/1
 *
 * yyyy：year-of-year；正儿八经的年
 * YYYY：week-based-year；只要本周跨年，那么这周就算入下一年，一周的第一天从周日开始算
 *
 * DD：代表处于这一年中那一天，不是处于这个月的那一天
 */
public class YearAndDayTest {

    private static void tryIt(int Y, int M, int D, String pat) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pat);
        LocalDate dat = LocalDate.of(Y, M, D);
        String str = fmt.format(dat);
        System.out.printf("Y=%04d M=%02d D=%02d formatted with \"%s\" -> %s\n", Y, M, D, pat, str);
    }

    public static void main(String[] args) {
        tryIt(2020, 1, 20, "MM/DD/YYYY");
        tryIt(2020, 1, 21, "DD/MM/YYYY");
        tryIt(2020, 1, 22, "YYYY-MM-DD");
        tryIt(2020, 3, 17, "MM/DD/YYYY");
        tryIt(2020, 3, 18, "DD/MM/YYYY");
        tryIt(2020, 3, 19, "YYYY-MM-DD");
    }
}
