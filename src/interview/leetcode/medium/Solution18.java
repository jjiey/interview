package interview.leetcode.medium;

import java.util.List;

/**
 * 4Sum
 * 四数之和
 * TODO
 */
public class Solution18 {

    public static void main(String[] args) {
        Solution18 lc = new Solution18();
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> res = lc.fourSum(nums, target);
        for (List<Integer> re : res) {
            System.out.println(re.toString());
        }
    }

    private List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

}
