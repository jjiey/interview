package interview.leetcode.easy;

/**
 * Best Time to Buy and Sell Stock II
 * 买卖股票的最佳时机 II
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
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                res += prices[i + 1] - prices[i];
            }
        }
        return res;
    }

}
