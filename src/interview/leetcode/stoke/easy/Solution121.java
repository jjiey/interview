package interview.leetcode.stoke.easy;

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
}
