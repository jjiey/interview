package interview.leetcode.stoke.hard;

/**
 * Best Time to Buy and Sell Stock IV
 * 买卖股票的最佳时机 IV
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Solution188 {

    public static void main(String[] args) {
        Solution188 lc = new Solution188();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int res = lc.maxProfit(2, prices);
        System.out.println(res);
    }

    /**
     * 买卖一次算一次交易，买入时交易次数不变，卖出时增加交易次数
     *
     * 状态：
     * dp[i][k][j]
     * i：第i天
     * k：代表之前交易的次数
     * j：0代表当天不持有股票（只能买）；1代表当天持有股票（只能卖）
     *
     * 第i天交易k次没有股票，有两种情况：前一天交易k次没有股票，前一天交易k - 1次有股票今天卖掉（增加交易次数），即
     *     dp[i, k, 0] = max(dp[i - 1, k, 0], dp[i - 1, k - 1, 1] + prices[i])
     * 第i天交易k次有股票，有两种情况：前一天交易k次有股票，前一天交易k次没有股票今天买入（不增加交易次数），即
     *     dp[i, k, 1] = max(dp[i - 1, k, 1], dp[i - 1, k, 0] - prices[i])
     * 最终结果：max( dp[n - 1, {0 ~ k}, 0] )
     */
    private int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        /*
        当k大于等于数组长度一半时, 问题退化为贪心问题
        此时采用【买卖股票的最佳时机 II】的贪心方法解决可以大幅提升时间性能，如果不用这个贪心，有个testCase过不去，会报空间超限
         */
        if (k >= prices.length / 2) {
            return greedy(prices);
        }
        int[][][] dp = new int[prices.length][k + 1][2];
        // 第一天为初始值
        for (int i = 0; i <= k; i++) {
            // 第 1 天交易 k 次后没有股票，初始值为 0
            dp[0][i][0] = 0;
            // 第 1 天交易 k 次后有股票，初始值为 -prices[0]
            dp[0][i][1] = -prices[0];
        }
        // 从第一天开始循环
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j <= k; j++) {
                // 防止 j - 1 出错
                dp[i][j][0] = j != 0 ? Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]) : dp[i - 1][j][0];
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, dp[prices.length - 1][i][0]);
        }
        return max;
    }

    private int greedy(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;
    }

    /**
     * dp同上边k和j交换位置
     */
    private int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return greedy(prices);
        }
        int[][][] mp = new int[prices.length][2][k + 1];
        for (int i = 0; i <= k; i++) {
            mp[0][0][i] = 0;
            mp[0][1][i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j <= k; j++) {
                mp[i][0][j] = j != 0 ? Math.max(mp[i - 1][1][j - 1] + prices[i], mp[i - 1][0][j]) : mp[i - 1][0][j];
                mp[i][1][j] = Math.max(mp[i - 1][0][j] - prices[i], mp[i - 1][1][j]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, mp[prices.length - 1][0][i]);
        }
        return max;
    }
}
