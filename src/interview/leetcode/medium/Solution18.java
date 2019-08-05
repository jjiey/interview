package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 4Sum
 * 四数之和
 */
public class Solution18 {

    public static void main(String[] args) {
        Solution18 lc = new Solution18();
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> res = lc.fourSum(nums, target);
        System.out.println(res.toString());
    }

    /**
     * 双指针法
     * 原理同Solution15双指针法, 对于四数之和来说, 是固定两个数, 用双指针找另外两个数
     */
    private List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 4) {
            return res;
        }
        // 先对数组进行排序, 为了方便的过滤掉重复元素
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            // 过滤掉重复元素
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 过滤掉重复元素
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int L = j + 1;
                int R = nums.length - 1;
                while (L < R) {
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum < target) {
                        L ++;
                    } else if (sum > target) {
                        R --;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        // 去重
                        while (L < R && nums[L] == nums[L+1]) {
                            L ++;
                        }
                        // 去重
                        while (L < R && nums[R] == nums[R-1]) {
                            R --;
                        }
                        // 接着找
                        L ++;
                        R --;
                    }
                }
            }
        }
        return res;
    }

}
