package interview.leetcode.easy;

/**
 * Sqrt(x)
 * x 的平方根
 * TODO
 */
public class Solution69 {

    public static void main(String[] args) {
        Solution69 lc = new Solution69();
        int res = lc.mySqrt(8);
        System.out.println(res);
    }

    private int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

}
