package interview.leetcode.stoke.hard;

/**
 * Best Time to Buy and Sell Stock III
 * 买卖股票的最佳时机 III
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Solution123 {

    public static void main(String[] args) {
        Solution123 lc = new Solution123();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int res = lc.maxProfit(prices);
        System.out.println(res);
    }

    /**
     * 状态：
     * dp[i][k][j]
     * i：第i天
     * k：代表之前交易的次数
     * j：0代表当天不持有股票（只能买）；1代表当天持有股票（只能卖）
     *
     * 第i天交易k次没有股票，有两种情况：前一天没有股票，前一天有股票今天卖掉，即
     *     dp[i, k, 0] = max(dp[i - 1, k, 0], dp[i - 1, k - 1, 1] + prices[i])
     * 第i天交易k次有股票，有两种情况：前一天有股票，前一天没有股票今天买入，即
     *     dp[i, k, 1] = max(dp[i - 1, k, 1], dp[i - 1, k - 1, 0] - prices[i])
     * 最终结果：max( dp[n - 1, {0 ~ k}, 0] )
     */
    private int maxProfit(int[] prices) {
        return 0;
    }
}
