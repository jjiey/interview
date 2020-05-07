package interview.leetcode.stoke.easy;

import java.util.Objects;

/**
 * Best Time to Buy and Sell Stock
 * 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 */
public class Solution121 {

    public static void main(String[] args) {
        Solution121 lc = new Solution121();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int res = lc.maxProfit(prices);
        System.out.println(res);
    }

    private int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, res = 0, profit;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if ((profit = (price - minPrice)) > res) {
                res = profit;
            }
        }
        return res;
    }

    /**
     * dp，思路参考Solution188
     */
    private int maxProfit2(int[] prices) {
        if (Objects.isNull(prices) || prices.length == 0) {
            return 0;
        }
        int[][][] dp = new int[prices.length][2][2];
        dp[0][0][0] = dp[0][1][0] = 0;
        dp[0][0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);
        }
        return Math.max(dp[prices.length - 1][0][0], dp[prices.length - 1][1][0]);
    }
}
