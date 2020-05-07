package interview.leetcode.medium;

import java.util.Arrays;

/**
 * Maximum Product Subarray
 * 乘积最大子序列
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 */
public class Solution152 {

    public static void main(String[] args) {
        Solution152 lc = new Solution152();
        int[] nums = new int[]{1, 9, 0, 1, 8};
        int res = lc.maxProduct4(nums);
        System.out.println(res);
    }

    /**
     * 暴力
     */
    private int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, product;
        int[] subNums;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // 复制的区间是左闭右开，即不包括j + 1
                subNums = Arrays.copyOfRange(nums, i, j + 1);
                product = 1;
                for (int subNum : subNums) {
                    product *= subNum;
                }
                max = Math.max(max, product);
            }
        }
        return max;
    }

    /**
     * 状态：
     * dp[i, j]
     * i：index
     * j：0表示最大值；1表示最小值
     *
     * 状态转移方程：
     * if (nums[i] >= 0) {
     *     // 如果用上nums[i]后, 还没有nums[i]大, 那就舍掉前面的, 直接取nums[i]
     *     dp[x][0] = Math.max(dp[y][0] * nums[i], nums[i]);
     *     // 如果用上nums[i]后, 还没有nums[i]小, 那就舍掉前面的, 直接取nums[i]
     *     dp[x][1] = Math.min(dp[y][1] * nums[i], nums[i]);
     * } else {
     *     // 如果用上nums[i]后, 还没有nums[i]大, 那就舍掉前面的, 直接取nums[i]
     *     dp[x][0] = Math.max(dp[y][1] * nums[i], nums[i]);
     *     // 如果用上nums[i]后, 还没有nums[i]小, 那就舍掉前面的, 直接取nums[i]
     *     dp[x][1] = Math.min(dp[y][0] * nums[i], nums[i]);
     * }
     * 可简化为：
     * dp[i, 0] = nums[i] >= 0 ? max(dp[i - 1, 0] * nums[i], nums[i]) : max(dp[i - 1, 1] * nums[i], nums[i])
     * dp[i, 1] = nums[i] >= 0 ? min(dp[i - 1, 1] * nums[i], nums[i]) : min(dp[i - 1, 0] * nums[i], nums[i])
     * 可简化为：
     * dp[i, 0] = max( max(dp[i - 1, 0] * nums[i], dp[i - 1, 1] * nums[i]), nums[i] )
     * dp[i, 1] = min( min(dp[i - 1, 0] * nums[i], dp[i - 1, 1] * nums[i]), nums[i] )
     */
    private int maxProduct2(int[] nums) {
        int[][] dp = new int[nums.length][2];
        int max = dp[0][0] = dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]), nums[i]);
            dp[i][1] = Math.min(Math.min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]), nums[i]);
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    /**
     * 滚动数组，优化maxProduct2的空间
     */
    private int maxProduct3(int[] nums) {
        int[][] dp = new int[2][2];
        int max = dp[0][0] = dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 滚动数组, x和y轮流取值0 or 1(不保存整个数组)
            int x = i % 2, y = (i - 1) % 2;
            dp[x][0] = Math.max(Math.max(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]);
            dp[x][1] = Math.min(Math.min(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]);
            max = Math.max(max, dp[x][0]);
        }
        return max;
    }

    /**
     * 不用数组，优化maxProduct3的空间
     */
    private int maxProduct4(int[] nums) {
        int max = nums[0], maxTemp = nums[0], minTemp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] < 0){
                int temp = maxTemp;
                maxTemp = minTemp;
                minTemp = temp;
            }
            maxTemp = Math.max(maxTemp * nums[i], nums[i]);
            minTemp = Math.min(minTemp * nums[i], nums[i]);
            max = Math.max(max, maxTemp);
        }
        return max;
    }
}
