package interview.leetcode.medium;

import java.util.Arrays;

/**
 * Coin Change
 * 零钱兑换
 */
public class Solution322 {

    public static void main(String[] args) {
        Solution322 lc = new Solution322();
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        int res = lc.coinChange(coins, amount);
        System.out.println(res);
    }

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
