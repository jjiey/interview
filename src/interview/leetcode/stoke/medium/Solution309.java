package interview.leetcode.stoke.medium;

/**
 * Best Time to Buy and Sell Stock with Cooldown
 * 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 */
public class Solution309 {

    public static void main(String[] args) {
        Solution309 lc = new Solution309();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int res = lc.maxProfit(prices);
        System.out.println(res);
    }

    /**
     * 状态：
     * dp[i][k][j]
     * i：第i天
     * k：0代表当天可以交易（前一天没有卖出）；1代表当天不可以交易（前一天卖出）
     * j：0代表当天不持有股票（只能买）；1代表当天持有股票（只能卖）
     *
     * todo 待验证
     * （1）第i天可以交易没有股票，则前一天没有卖出，有四种情况：前一天不可以交易没有买入，前一天不可以交易买入但今天卖出，前一天可以交易没有买入，前一天可以交易买入但今天卖出，即
     *     dp[i, 0, 0] = max(dp[i - 1, 1, 0], dp[i - 1, 1, 1] + prices[i], dp[i - 1, 0, 0], dp[i - 1, 0, 1] + prices[i])
     * （2）第i天不可以交易有股票，有两种情况：前一天交易过有股票，前一天交易过没有股票今天买入，即
     *     dp[i, 1，1] = max(dp[i - 1, 1, 1], dp[i - 1, 1, 0] - prices[i])
     *
     * （3）第i天可以交易有股票，则前一天没有卖出，有两种情况：前一天不可以交易没有买入，前一天不可以交易买入但今天卖出，前一天可以交易没有买入，前一天可以交易买入但今天卖出，即
     *     dp[i, 0, 1] = max(dp[i - 1, 1, 1], dp[i - 1, 1, 0] - prices[i])
     * （4）第i天不可以交易没有股票，有两种情况：前一天交易过没有股票，前一天交易过有股票今天卖出，即
     *     dp[i, 1，0] = max(dp[i - 1, 1, 0], dp[i - 1, 1, 1] + prices[i])
     * 最终结果：max( dp[i, 0, 0], dp[i, 1，1], dp[i, 0, 1], dp[i, 1，0] )
     */
    private int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
//        int[][][] dp = new int[prices.length][2][2];
//        dp[0][0][0] = 0;
//        dp[0][1][1] = -prices[0];
//        dp[0][0][1] =;
//        dp[0][1][0] =;

        return 0;
    }
}
