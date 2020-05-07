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

    public static void main(String[] args) {
        Solution300 lc = new Solution300();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
//        int res = lc.lengthOfLIS(nums);
        int res = lc.lengthOfLIS4(nums);
        System.out.println(res);
    }

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
        for (int i = 0; i < nums.length; i++) {
            if (lis.isEmpty() || nums[i] > lis.get(lis.size() - 1)) {
                lis.add(nums[i]);
            } else {
                int index = lowerBound(lis.toArray(new Integer[lis.size()]), 0, lis.size(), nums[i]);
                lis.add(index, nums[i]);
                lis.remove(index + 1);
            }
        }
        return lis.size();
    }

    private int lowerBound(Integer[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid +1;
            }
        }
        return l;
    }
    private int lowerBound2(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int upperBound(int []nums ,int l,int r, int target) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
