package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Triangle
 * 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 */
public class Solution120 {

    private List<List<Integer>> createTriangle() {
        List<Integer> one = new ArrayList<Integer>(){{
            add(2);
        }};
        List<Integer> two = new ArrayList<Integer>(){{
            add(3);add(4);
        }};
        List<Integer> three = new ArrayList<Integer>(){{
            add(6);add(5);add(7);
        }};
        List<Integer> four = new ArrayList<Integer>(){{
            add(4);add(1);add(8);add(3);
        }};
        return new ArrayList<List<Integer>>(){{
            add(one);add(two);add(three);add(four);
        }};
    }

    public static void main(String[] args) {
        Solution120 lc = new Solution120();
        int res = lc.minimumTotal3(lc.createTriangle());
        System.out.println(res);
    }

    /**
     * 暴力
     */
    private int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotalHelper(triangle, 0, 0, triangle.size());
    }

    private int minimumTotalHelper(List<List<Integer>> triangle, int curRow, int curCol, int level) {
        if (curRow >= level || curCol >= triangle.get(curRow).size()) {
            return 0;
        }
        int left = minimumTotalHelper(triangle, curRow + 1, curCol, level);
        int right = minimumTotalHelper(triangle, curRow + 1, curCol + 1, level);
        System.out.println(curRow + ", " + curCol);
        return Math.min(left, right) + triangle.get(curRow).get(curCol);
    }

    /**
     * dp 二维
     *
     * 状态：dp[i, j]：从三角形底部开始到i行j列的最小路径和
     *
     * 状态转移方程：
     * dp[i, j] = min(DP[i + 1, j], DP[i + 1, j + 1]) + triangle[i, j]
     *
     * finally return dp[0, 0]
     */
    private int minimumTotal2(List<List<Integer>> triangle) {
        int[][] mini = new int[triangle.size()][triangle.size()];
        // base case：mini最后一行赋值为triangle最后一行的值
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < lastRow.size(); i++) {
            mini[triangle.size() - 1][i] = lastRow.get(i);
        }
        // 从倒数第二层开始递推
        List<Integer> tempRow;
        for (int row = triangle.size() - 2; row >= 0; row--) {
            // 遍历该层所有值
            tempRow = triangle.get(row);
            for (int col = 0; col < tempRow.size(); col++) {
                // 下一层的最小值加上该层的值
                mini[row][col] = Math.min(mini[row + 1][col], mini[row + 1][col + 1]) + tempRow.get(col);
            }
        }
        return mini[0][0];
    }

    /**
     * dp 一维, 优化minimumTotal2, 一直复用mini[j]
     */
    private int minimumTotal3(List<List<Integer>> triangle) {
        int[] mini = new int[triangle.size()];
        // base case：mini最后一行赋值为triangle最后一行的值
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < lastRow.size(); i++) {
            mini[i] = lastRow.get(i);
        }
        // 从倒数第二层开始递推
        List<Integer> tempRow;
        for (int i = triangle.size() - 2; i >= 0; i--) {
            // 遍历该层所有值
            tempRow = triangle.get(i);
            for (int j = 0; j < tempRow.size(); j++) {
                mini[j] = Math.min(mini[j], mini[j + 1]) + tempRow.get(j);
            }
        }
        return mini[0];
    }
}
