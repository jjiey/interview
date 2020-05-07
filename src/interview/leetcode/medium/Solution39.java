package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Combination Sum
 * 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 */
public class Solution39 {

    private final List<List<Integer>> output = new ArrayList<>();

    public static void main(String[] args) {
        Solution39 lc = new Solution39();
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        System.out.println(lc.combinationSum(candidates, target));
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumHelper(candidates, target);
        return output;
    }

    private void combinationSumHelper(int[] candidates, int target) {

    }
}
