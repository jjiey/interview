package interview.leetcode.medium;

import java.util.Arrays;

/**
 * Coin Change
 * 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 */
public class Solution322 {

    public static void main(String[] args) {
        Solution322 lc = new Solution322();
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        int res = lc.coinChange(coins, amount);
        System.out.println(res);
    }

    /**
     * dp
     *
     * 状态：dp[i]
     * i：上到第i级台阶（凑成的总金额）的最小步数（最少需要的硬币个数）
     *
     * 状态转移方程：
     *
     */
    private int coinChange(int[] coins, int amount) {
        // index: 0 - amount
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
