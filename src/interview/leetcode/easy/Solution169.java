package interview.leetcode.easy;

import java.util.Arrays;

/**
 * Majority Element
 * 求众数
 */
public class Solution169 {

    public static void main(String[] args) {
        Solution169 lc = new Solution169();
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        int res = lc.majorityElement(nums);
        System.out.println(res);
    }

    /**
     * 排序之后取中间的值就可以了
     * 因为题目说肯定存在这样的数出现的次数大于n/2
     * 别的思路: 1.暴力
     *     2.map
     *     3.排序后遍历
     *     4.分治 ...
     */
    private int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

}
