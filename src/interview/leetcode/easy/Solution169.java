package interview.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Majority Element
 * 求众数
 * 题目要求: 数组是非空的，并且给定的数组总是存在众数
 */
public class Solution169 {

    public static void main(String[] args) {
        Solution169 lc = new Solution169();
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        int res = lc.majorityElement(nums);
        System.out.println(res);
    }

    /**
     * 投机取巧法 sort: 排序之后取中间的值就可以了, 因为题目说肯定存在这样的数出现的次数大于n/2
     * 别的思路: 1.暴力
     *     2.map
     *     3.排序后遍历 数量 > (n / 2)即可
     *     4.分治
     */
    private int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    /**
     * 暴力
     */
    private int majorityElement2(int[] nums) {
        int majorityCount = nums.length/2;
        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }
            if (count > majorityCount) {
                return num;
            }
        }
        return -1;
    }

    /**
     * map
     */
    private int majorityElement3(int[] nums) {
        // K - num V - num出现次数
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        // 遍历map, 找V最大的K
        Map.Entry<Integer, Integer> resEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (resEntry == null || entry.getValue() > resEntry.getValue()) {
                resEntry = entry;
            }
        }
        return resEntry.getKey();
    }

    /**
     * 分治 TODO
     */
    private int majorityElement4(int[] nums) {
        return 0;
    }

}
