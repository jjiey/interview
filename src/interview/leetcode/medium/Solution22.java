package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate Parentheses
 * 括号生成
 */
public class Solution22 {

    public static void main(String[] args) {
        Solution22 lc = new Solution22();
        int n = 3;
        List<String> res = lc.generateParenthesis(n);
        System.out.println(res.toString());
    }

    private List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, "", n, n);
        return res;
    }

    /**
     * 括号生成
     * @param res result
     * @param s 拼接结果
     * @param left 可用的左括号数
     * @param right 可用的右括号数
     * 先拼接左括号, 再拼接右括号
     * 其他思路: 一共n * 2个字符, 每个字符都可以是左括号或者右括号, 列出所有组合, 去掉不合法的
     */
    private void generate(List<String> res, String s, int left, int right) {
        // 当左右括号都用完, 一次s拼接完成, 加入res
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            generate(res, s + "(", left - 1, right);
        }
        // 当可用的右括号数量多于可用的左括号数量时, 才继续拼接右括号
        if (right > left) {
            generate(res, s + ")", left, right - 1);
        }
    }

}
