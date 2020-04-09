package interview.leetcode.medium;

import java.util.Arrays;

/**
 * Longest Increasing Subsequence
 * 最长上升子序列
 * 动态规划
 */
public class Solution300 {

    public static void main(String[] args) {
        Solution300 lc = new Solution300();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int res = lc.lengthOfLIS(nums);
        System.out.println(res);
    }

    /*
        1.状态：
        DP[i]：从数组索引0 - i元素，最长子序列的长度
        这个值是：max(DP[0], DP[1], ..., DP[i - 1])
        2.状态转移方程式：
        max(DP[0], DP[1], ..., DP[i - 1], DP[i - 1] + 1)
        3.初始值：本题即所有的DP[i]初始值都为1
        伪代码：
        res;
        for i: 0 - (n - 1)
          for j: 0 - (i - 1)
            if (a[j] < a[i])
              DP[i] = max(DP[j]) + 1
          max(res, DP[i])
     */
    private int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        // 全局结果，记录最长上升子序列的数量
        int res = 1;
        // 设置每个值的状态初始值为1
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            // 遍历i之前的所有值，看能否组成上升子序列
            for (int j = 0; j < i; j++) {
                // 如果可以组成上升子序列
                if (nums[j] < nums[i]) {
                    // 更新i的状态值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 获取最长上升子序列用O(NlogN)解法结果不一定正确, 比如:[7,8,9,1,2], 且java里没有类似C++里的lower_bound, 该解法只能求出最后的最长上升子序列的值是保证正确的

//    private int lengthOfLIS2(int[] nums) {
//
//    }
}
