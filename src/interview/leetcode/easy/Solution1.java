package interview.leetcode.easy;

import java.util.*;

/**
 * Two Sum
 * 两数之和
 * 思路: 1.暴力枚举
 *     2.遍历两遍map, 先把所有值存进map再循环
 *     3.遍历一遍map
 */
public class Solution1 {

    public static void main(String[] args) {
        Solution1 lc = new Solution1();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = lc.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 遍历一遍map
     */
    private int[] twoSum(int[] nums, int target) {
        // K -> val; V -> index
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                res[0] = map.get(another);
                res[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

}
