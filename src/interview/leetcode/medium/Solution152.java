package interview.leetcode.medium;

/**
 * Maximum Product Subarray
 * 乘积最大子序列
 */
public class Solution152 {

    public static void main(String[] args) {
        Solution152 lc = new Solution152();
        int[] nums = new int[]{2, 3, -2, 4};
//        int[] nums = new int[]{1, 9, 0, 1, 8};
        int res = lc.maxProduct(nums);
        System.out.println(res);
    }

    private int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 第一维表示数组的长度(不保存整个数组), 第二维用0表示是正的最大值, 用1表示为负的最小值
        int[][] dp = new int[2][2];
        int res = nums[0];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            // 滚动数组, x和y轮流取值0or1(不保存整个数组)
            int x = i % 2;
            int y = (i - 1) % 2;
            /*
            if (nums[i] >= 0) {
                // 如果用上nums[i]后, 还没有nums[i]大, 那就舍掉前面的, 直接取nums[i]
                dp[x][0] = Math.max(dp[y][0] * nums[i], nums[i]);
                // 如果用上nums[i]后, 还没有nums[i]小, 那就舍掉前面的, 直接取nums[i]
                dp[x][1] = Math.min(dp[y][1] * nums[i], nums[i]);
            } else {
                // 如果用上nums[i]后, 还没有nums[i]大, 那就舍掉前面的, 直接取nums[i]
                dp[x][0] = Math.max(dp[y][1] * nums[i], nums[i]);
                // 如果用上nums[i]后, 还没有nums[i]小, 那就舍掉前面的, 直接取nums[i]
                dp[x][1] = Math.min(dp[y][0] * nums[i], nums[i]);
            }
            */
            // 上面的if语句可以简写为
            dp[x][0] = Math.max(Math.max(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]);
            dp[x][1] = Math.min(Math.min(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]);

            res = Math.max(res, dp[x][0]);
        }
        return res;
    }

}
