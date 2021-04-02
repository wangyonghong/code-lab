/**
 * @author yonghongwang#163.com
 */
public class ForLoopTest {
    private static int[] nums = {1, 6, 8};
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        for (int num : nums) {
            ma.submit(num);
        }
        double avg = ma.getAvg();
    }
}