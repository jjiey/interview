package interview.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 * 两数之和
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
     * 暴力
     * 时间复杂度: O(N^2) 空间复杂度: O(1)
     */
    private int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 遍历两遍map
     */
    private int[] twoSum2(int[] nums, int target) {
        // K -> val; V -> index
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another) && map.get(another) != i) {
                return new int[]{map.get(another), i};
            }
        }
        return null;
    }

    /**
     * 遍历一遍map
     * 时间复杂度: O(N) 空间复杂度: O(N)
     */
    private int[] twoSum3(int[] nums, int target) {
        // K -> val; V -> index
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
