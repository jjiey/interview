package interview.leetcode.medium;

/**
 * Pow(x, n)
 * Pow(x, n)
 */
public class Solution50 {

    public static void main(String[] args) {
        Solution50 lc = new Solution50();
//        double res = lc.myPow(2, 10);
//        double res = lc.myPow2(2, 10);
        double res = lc.myPow4(2, 10);
//        double res = lc.myPow5(2, 10);
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
     * 暴力法, 会超出时间限制
     * 提前处理: 如果n是负数, x ^ n = 1 / (x ^ -n) = (1 / x) ^ -n
     */
    private double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++) {
            ans = ans * x;
        }
        return ans;
    }

    /**
     * 分治
     * 如果n是偶数, 一分为二后, n / 2取整:
     *     y = x ^ (n / 2), result = y * y
     * 如果n是奇数, 拿掉最中间的x(就是随便去掉一个x), 一分为二后, n / 2取整:
     *     y = x ^ (n / 2) * x, result = y * y * x
     *
     * 提前处理: 如果n是负数, x ^ n = 1 / (x ^ -n) = (1 / x) ^ -n
     */
    private double myPow4(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        // n / 2 == n >> 1
        double y = fastPow(x, n >> 1);
        // 偶数
        if ((n & 1) == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }

    /**
     * 非递归
     * 提前处理: 如果n是负数, x ^ n = 1 / (x ^ -n) = (1 / x) ^ -n
     */
    public double myPow5(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i >>= 1) {
            // 奇数
            if ((i & 1) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

}
