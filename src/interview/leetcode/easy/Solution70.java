package interview.leetcode.easy;

/**
 * Climbing Stairs
 * 爬楼梯
 */
public class Solution70 {

    public static void main(String[] args) {
        Solution70 lc = new Solution70();
        int n = 3;
        int res = lc.climbStairs(n);
        System.out.println(res);
    }

    /*
        1.状态：
        DP[n]：到第n阶楼梯的总走法数
        2.状态转移方程式：
        DP[n] = DP[n - 1] + DP[n - 2]
        3.初始值：
        DP[0] = 0;DP[1] = 1;DP[2] = 2
        伪代码：
        斐波拉契数列
     */
    private int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        int[] res = new int[n];
        res[0] = 1;
        res[1] = 2;
        for (int i = 2; i < n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n - 1];
    }

}
