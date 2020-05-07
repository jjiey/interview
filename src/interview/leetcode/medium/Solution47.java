package interview.leetcode.medium;

import java.util.*;

/**
 * Permutations II
 * 全排列 II
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 */
public class Solution47 {

    private final List<List<Integer>> output = new ArrayList<>();

    public static void main(String[] args) {
        Solution47 lc = new Solution47();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(lc.permuteUnique(nums));
    }

    private final Map<Integer, List<Integer>> sumMap = new HashMap<>();
    private boolean[] used;

    private List<List<Integer>> permuteUnique(int[] nums) {
        if (Objects.nonNull(nums) && nums.length > 0) {
            used = new boolean[nums.length];
            Arrays.fill(used, false);
            permuteUniqueHelper(nums, 0, new ArrayList<>());
        }
        return output;
    }

    /**
     * todo 待修改，但是改也没意义
     */
    private void permuteUniqueHelper(int[] nums, int index, List<Integer> l) {
        if (index == nums.length) {
            int sum = 0;
            for (Integer i : l) {
                sum += i;
            }
            List<Integer> list = sumMap.get(sum);
            int r = 0;
            if (Objects.nonNull(list)) {
                for (Integer i : l) {
                    if (list.contains(i)) {
                        r ++;
                    }
                }
            }
            if (r == nums.length) {
                return;
            }
            sumMap.put(sum, new ArrayList<>(l));
            output.add(new ArrayList<>(l));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                l.add(nums[i]);
                used[i] = true;
                permuteUniqueHelper(nums, index + 1, l);
                l.remove((Object) nums[i]);
                used[i] = false;
            }
        }
    }
}
