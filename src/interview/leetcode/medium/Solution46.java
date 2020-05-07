package interview.leetcode.medium;

import java.util.*;

/**
 * Permutations
 * 全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class Solution46 {

    private final List<List<Integer>> output = new ArrayList<>();

    public static void main(String[] args) {
        Solution46 lc = new Solution46();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(lc.permute3(nums));
    }

    private List<List<Integer>> permute(int[] nums) {
        if (Objects.nonNull(nums) && nums.length > 0) {
            permuteHelper(nums, 0, new ArrayList<>());
        }
        return output;
    }

    /**
     * @param l 已经保存了一个有index个元素的排列，向l的末尾添加第index + 1个元素，获得一个有index + 1个元素的排列
     */
    private void permuteHelper(int[] nums, int index, List<Integer> l) {
        if (index == nums.length) {
            output.add(new ArrayList<>(l));
            return;
        }
        for (int num : nums) {
            if (!l.contains(num)) {
                l.add(num);
                permuteHelper(nums, index + 1, l);
                l.remove((Object) num);
            }
        }
    }

    /* ========== */

    private boolean[] used;

    private List<List<Integer>> permute2(int[] nums) {
        if (Objects.nonNull(nums) && nums.length > 0) {
            used = new boolean[nums.length];
            Arrays.fill(used, false);
            permuteHelper2(nums, 0, new ArrayList<>());
        }
        return output;
    }

    /**
     * 用used数组对permuteHelper加速
     */
    private void permuteHelper2(int[] nums, int index, List<Integer> l) {
        if (index == nums.length) {
            output.add(new ArrayList<>(l));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                l.add(nums[i]);
                used[i] = true;
                permuteHelper2(nums, index + 1, l);
                l.remove((Object) nums[i]);
                used[i] = false;
            }
        }
    }

    /* ==========permute3和permute4思路是一样的 */

    private List<List<Integer>> permute3(int[] nums){
        // convert nums into list since the output is a list of lists
        List<Integer> numsList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numsList.add(num);
        }
        permuteHelper3(numsList, nums.length, 0);
        return output;
    }

    private void permuteHelper3(List<Integer> numsList, int n, int first) {
        // if all integers are used up
        if (first == n) {
            output.add(new ArrayList<>(numsList));
        }
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(numsList, first, i);
            // use next integers to complete the permutations
            permuteHelper3(numsList, n, first + 1);
            // backtrack
            Collections.swap(numsList, first, i);
        }
    }

    /* ==========permute3和permute4思路是一样的 */

    private List<List<Integer>> permute4(int[] nums){
        if (Objects.nonNull(nums) && nums.length > 0) {
            permuteHelper4(nums, nums.length, 0);
        }
        return output;
    }

    private void permuteHelper4(int[] nums, int n, int first) {
        if (first == n) {
            List<Integer> numsList = new ArrayList<>(nums.length);
            for (int num : nums) {
                numsList.add(num);
            }
            output.add(new ArrayList<>(numsList));
        }
        for (int i = first; i < n; i++) {
            swap(nums, first, i);
            permuteHelper4(nums, n, first + 1);
            swap(nums, first, i);
        }
    }

    private void swap(int[] chars, int i, int j) {
        int temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
