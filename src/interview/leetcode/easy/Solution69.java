package interview.leetcode.easy;

/**
 * Sqrt(x)
 * x 的平方根
 * TODO
 */
public class Solution69 {

    public static void main(String[] args) {
        Solution69 lc = new Solution69();
        int x = 8;
//        int res = lc.mySqrt(x);
        float res = lc.mySqrt2(x);
        System.out.println(res);
    }

    /**
     * 调用库函数
     */
    private int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /**
     * 二分法
     * TODO 有点小问题
     */
    private float mySqrt2(int x) {
        float left = 0;
        float right = x;
        while (Math.abs(right - left) > 1e-5) {
            float mid = left + (right - left) / 2;
            float y = mid * mid;
            if (y > x) {
                right = mid;
            } else if (y < x) {
                left = mid;
            } else {
                return mid;
            }
        }
        return left;
    }

}
