package interview.leetcode.stoke.medium;

/**
 * todo
 * Best Time to Buy and Sell Stock with Transaction Fee
 * 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 */
public class Solution714 {

    public static void main(String[] args) {
        Solution714 lc = new Solution714();
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int res = lc.maxProfit(prices, 2);
        System.out.println(res);
    }

    private int maxProfit(int[] prices, int fee) {
        return 0;
    }
}
