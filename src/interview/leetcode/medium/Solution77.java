package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinations
 * 组合
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Solution77 {

    private final List<List<Integer>> output = new ArrayList<>();

    public static void main(String[] args) {
        Solution77 lc = new Solution77();
        int n = 4, k = 2;
        lc.combine(n, k);
        System.out.println();
    }

    private List<List<Integer>> combine(int n, int k) {
//        if (n <= 0 || k <= 0 || k > n) {
//            return output;
//        }
        if (n > 0 && k > 0 && k <= n) {
            combineHelper(n, k, 1, new ArrayList<>());
        }
        return output;
    }

    /**
     * 求解C(n, k)
     * @param start 从start开始搜索新的元素
     * @param l 当前已经找到的组合存储在c中
     */
    private void combineHelper(int n, int k, int start, List<Integer> l) {
        if (k == l.size()) {
            output.add(new ArrayList<>(l));
            return;
        }
        for (int i = start; i <= n; i++) {
            l.add(i);
            combineHelper(n, k, i + 1, l);
            l.remove((Object) i);
        }
    }

    /**
     * 剪枝优化combineHelper
     */
    private void combineHelper2(int n, int k, int start, List<Integer> l) {
        if (k == l.size()) {
            output.add(new ArrayList<>(l));
            return;
        }
        // 此时l中还有k - l.size()个空位，所以[i, n]中至少要有k - l.size()个元素，才能进入循环
        // 所以，i最多为：n - (k - l.size()) + 1
        for (int i = start; i <= n - (k - l.size()) + 1; i++) {
            l.add(i);
            combineHelper2(n, k, i + 1, l);
            l.remove((Object) i);
        }
    }
}
