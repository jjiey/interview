package interview.leetcode.medium;

/**
 * Pow(x, n)
 * Pow(x, n)
 * TODO
 */
public class Solution50 {

    public static void main(String[] args) {
        Solution50 lc = new Solution50();
//        double res = lc.myPow(2, 10);
//        double res = lc.myPow2(2, 10);
        double res = lc.myPow3(2, 10);
        System.out.println(res);
    }

    /**
     * 直接调库函数
     */
    private double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    /**
     * 暴力法, 会超出时间限制
     */
    private double myPow2(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double res = x;
        for (int i = 0; i < Math.abs(n) - 1; i++) {
            res = res * x;
        }
        if (n < 0) {
            res = 1 / res;
        }
        return res;
    }

    /**
     * 分治
     */
    private double myPow3(double x, int n) {
        return 0;
    }

}
