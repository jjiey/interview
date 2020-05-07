package interview.leetcode.stoke.easy;

/**
 * Best Time to Buy and Sell Stock II
 * 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Solution122 {

    public static void main(String[] args) {
        Solution122 lc = new Solution122();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int res = lc.maxProfit(prices);
        System.out.println(res);
    }

    /**
     * 贪心算法, 只要第二天的股价比第一天的高, 就今天买入, 明天卖出
     * 可以用贪心算法是因为: 只能持一股, 每天可以买卖无数次, 没有手续费
     * 别的思路: 动态规划
     */
    private int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;
    }

    /**
     * dfs
     */
    private int maxProfit2(int[] prices) {
        return calculate(prices, 0);
    }

    private int calculate(int[] prices, int s) {
        if (s >= prices.length) {
            return 0;
        }
        int max = 0, maxProfit, profit;
        for (int start = s; start < prices.length; start++) {
            maxProfit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                // 如果前一天的股价 < 后一天的股价，才考虑卖出
                if (prices[start] < prices[i]) {
                    // 收益 = （i + 1天的收益） + i天卖出后的收益
                    profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }
            if (maxProfit > max) {
                max = maxProfit;
            }
        }
        return max;
    }

    /**
     * dp，思路参考Solution188
     */
    private int maxProfit3(int[] prices) {
        return 0;
    }
}
