package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Triangle
 * 三角形最小路径和
 */
public class Solution120 {

    public static void main(String[] args) {
        Solution120 lc = new Solution120();
        int res = lc.minimumTotal(lc.createTriangle());
//        int res = lc.minimumTotal2(lc.createTriangle());
        System.out.println(res);
    }

    /**
     * 一维, 优化minimumTotal2, 一直复用mini[j]
     */
    /*
        1.状态：
        DP[i, j]：从三角形底部开始到[i, j]的最小路径和
        2.状态转移方程式：
        DP[i, j] = min(DP[i + 1, j], DP[i + 1, j + 1]) + Triangle[i, j]
        3.初始值：本题即为最底层的值：
        DP[m - 1, j] = Triangle[m - 1, j]
        伪代码：
        for i: (m - 1) - 0
          for j: 0 - (m - 1)
            mini[j];
        return mini[0];
     */
    private int minimumTotal(List<List<Integer>> triangle) {
        int[] mini = new int[triangle.size()];
        // mini最后一行赋值为triangle最后一行
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < lastRow.size(); i++) {
            mini[i] = lastRow.get(i);
        }
        // 开始递推, triangle.size() - 2从倒数第二层开始
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> tempRow = triangle.get(i);
            for (int j = 0; j < tempRow.size(); j++) {
                mini[j] = Math.min(mini[j], mini[j + 1]) + tempRow.get(j);
            }
        }
        return mini[0];
    }

    /**
     * 二维
     */
    private int minimumTotal2(List<List<Integer>> triangle) {
        int[][] mini = new int[triangle.size()][triangle.size()];
        // mini最后一行赋值为triangle最后一行
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < lastRow.size(); i++) {
            mini[triangle.size() - 1][i] = lastRow.get(i);
        }
        // 开始递推, triangle.size() - 2从倒数第二层开始
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> tempRow = triangle.get(i);
            for (int j = 0; j < tempRow.size(); j++) {
                mini[i][j] = Math.min(mini[i + 1][j], mini[i + 1][j + 1]) + tempRow.get(j);
            }
        }
        return mini[0][0];
    }

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

}
