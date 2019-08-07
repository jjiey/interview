package interview.leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * N-Queens
 * N皇后
 * 皇后可以攻击横竖斜区域
 */
public class Solution51 {

    public static void main(String[] args) {
        Solution51 lc = new Solution51();
        List<List<String>> res = lc.solveNQueens(4);
        for (List<String> re : res) {
            System.out.println(re.toString());
        }
    }

    private List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        _dfs(n, res, new HashSet<>(), new HashSet<>(), new HashSet<>(), 0, new ArrayList<>());
        return res;
    }

    /**
     * _dfs
     * @param n n个皇后
     * @param res 最终结果
     * @param cols 列, 记录可以被攻击的列区域
     * @param pie 撇, 记录可以被攻击的撇区域, 皇后位置和撇区域关系: x + y = c
     * @param na 捺, 记录可以被攻击的捺区域, 皇后位置和捺区域关系: x - y = c
     * @param row 行号
     * @param tempList 每一次的结果
     * 空间换时间
     */
    private void _dfs(int n, List<List<String>> res, Set<Integer> cols, Set<Integer> pie, Set<Integer> na, int row, List<String> tempList) {
        if (row >= n) {
            // 因为tempList最后会remove, 所以需要复制一份tempList, 否则返回为空
            res.add(tempList.stream().collect(Collectors.toList()));
            return;
        }
        // 遍历列
        for (int j = 0; j < n; j++) {
            // 如果在会被攻击的区域, continue
            if (pie.contains(row + j) || na.contains(j - row) || cols.contains(j)) {
                continue;
            }
            // 记录攻击区域
            pie.add(row + j);
            na.add(j - row);
            cols.add(j);

            String temp = getStr(n, j);
            tempList.add(temp);

            _dfs(n, res, cols, pie, na, row + 1, tempList);

            // remove元素, 为下一次用, 因为这几个元素是全局变量
            pie.remove(row + j);
            na.remove(j - row);
            cols.remove(j);
            tempList.remove(temp);
        }
    }

    private String getStr(int n, int j) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(i == j ? "Q" : ".");
        }
        return stringBuilder.toString();
    }

}
