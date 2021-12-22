package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Longest Increasing Subsequence
 * 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 */
public class Solution300 {

//    public static void main(String[] args) {
//        Solution300 lc = new Solution300();
//        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
////        int res = lc.lengthOfLIS(nums);
//        int res = lc.lengthOfLIS4(nums);
//        System.out.println(res);
//    }

    /**
     * 暴力
     */
    private int lengthOfLIS(int[] nums) {
        return lengthOfLISHelper(nums, Integer.MIN_VALUE, 0);
    }

    private int lengthOfLISHelper(int[] nums, int curMax, int curIndex) {
        if (nums.length == curIndex) {
            return 0;
        }
        // 如果包含当前index的数字，其递增的长度
        int takenLength  = 0;
        if (nums[curIndex] > curMax) {
            takenLength  = 1  + lengthOfLISHelper(nums, nums[curIndex], curIndex + 1);
        }
        // 如果不包含当前index的数字，其递增的长度
        int nonTakenLength = lengthOfLISHelper(nums, curMax, curIndex + 1);
        // 返回较大的值
        return Math.max(takenLength , nonTakenLength);
    }

    /**
     * 记录中间状态
     * todo
     */
    private int lengthOfLIS2(int[] nums) {
        return 0;
    }

    /**
     * 状态：dp[i]
     * i：从头开始到第i个元素，且包含第i个元素，最长上升子序列的长度
     *
     * 状态转移方程：
     * for i: 0 - (n - 1)
     *     for j: 0 - (i - 1)
     *         if (a[j] < a[i])
     *             DP[i] = max(DP[j]) + 1
     *     res = max(res, DP[i])
     *
     * finally return max(dp[0], dp[1], dp[2], ..., dp[i - 1])
     */
    private int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 获取最长上升子序列用O(NlogN)解法结果不一定正确, 比如:[7,8,9,1,2], 结果为[1,2,9]，且java里没有类似C++里的lower_bound, 该解法只能求出最后的最长上升子序列的值是保证正确的
    /**
     * O(NlogN)解法
     * 自己实现lower_bound方法，但是写起来比较恶心
     */
    private int lengthOfLIS4(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            if (lis.isEmpty() || num > lis.get(lis.size() - 1)) {
                lis.add(num);
            } else {
                int index = lowerBound(lis.toArray(new Integer[lis.size()]), num);
                lis.add(index, num);
                lis.remove(index + 1);
            }
        }
        return lis.size();
    }

    /**
     * 利用二分法，求 nums 中第一个大于等于 target 的地址，不存在返回 nums 最后一个元素索引
     * [l...r)
     *
     * 如果求 nums 中第一个小于 target 的地址，结果减 1 就可以了。注意如果查找的元素比第一个元素还要小，需要做特殊判断
     */
    private int lowerBound(Integer[] nums, int target) {
        int len = nums.length, l = 0, r = len;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid +1;
            }
        }
        return l < len ? l : len - 1;
    }

    /**
     * 利用二分法，求 nums 中第一个大于等于 target 的地址，不存在返回 nums 最后一个元素索引
     * [l...r]
     *
     * 如果求 nums 中第一个小于 target 的地址，结果减 1 就可以了。注意如果查找的元素比第一个元素还要小，需要做特殊判断
     */
    private int lowerBound2(Integer[] nums, int target) {
        int len = nums.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l < len ? l : r;
    }

    /**
     * 利用二分法，求 nums 中第一个大于 target 的地址，不存在返回 nums 最后一个元素索引
     * [l...r)
     *
     * 如果求 nums 中第一个小于等于 target 的地址，结果减 1 就可以了（有重复值不行）。注意如果查找的元素比第一个元素还要小，需要做特殊判断
     */
    private int upperBound(Integer[] nums, int target) {
        int len = nums.length, l = 0, r = len;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l < len ? l : len - 1;
    }

    /**
     * 利用二分法，求 nums 中第一个大于 target 的地址，不存在返回 nums 最后一个元素索引
     * [l...r]
     *
     * 如果求 nums 中第一个小于等于 target 的地址，结果减 1 就可以了（有重复值不行）。注意如果查找的元素比第一个元素还要小，需要做特殊判断
     */
    private int upperBound2(Integer[] nums, int target) {
        int len = nums.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l < len ? l : r;
    }

    public static void main(String[] args) {
        Solution300 lc = new Solution300();
        Integer[] aa = new Integer[]{1,1,2,2,3,3,5};
//        System.out.println(lc.lowerBound(aa, 3));
//        System.out.println(lc.lowerBound2(aa, 3));
//        System.out.println(lc.upperBound(aa, 3));
//        System.out.println(lc.upperBound2(aa, 3));

//        System.out.println(lc.lengthOfLIS4Temp(new int[]{10,9,2,5,3,7,101,18}));
    }

    private int lengthOfLIS4Temp(int[] nums) {
        int i = 0;
        int[] temp = new int[nums.length];
        for (int num : nums) {
            if (i == 0 || num > temp[i - 1]) {
                temp[i++] = num;
            } else {
                int index = Arrays.binarySearch(temp, 0, i, num);
                if (index < 0) {
                    index = -index;
                }
                if (index < nums.length) {
                    temp[i++] = num;
                    temp[i] = 0;
                }
            }
        }
        return temp[i - 1];
//        List<Integer> lis = new ArrayList<>();
//        for (int num : nums) {
//            if (lis.isEmpty() || num > lis.get(lis.size() - 1)) {
//                lis.add(num);
//            } else {
//                int index = lowerBound(lis.toArray(new Integer[lis.size()]), num);
//                lis.add(index, num);
//                lis.remove(index + 1);
//            }
//        }
//        return lis.size();
    }
}
