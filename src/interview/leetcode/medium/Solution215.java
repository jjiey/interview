package interview.leetcode.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Kth Largest Element in an Array
 * 数组中的第K个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class Solution215 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        Solution215 lc = new Solution215();
        int res = lc.findKthLargest4(nums, k);
        System.out.println(res);
    }

    /**
     * 暴力
     */
    private int findKthLargest2(int[] nums, int k) {
        int len = nums.length, maxIndex, temp;
        for (int i = 0; i <= k; i++) {
            maxIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex > i) {
                // swap count and maxIndex
                temp = nums[i];
                nums[i] = nums[maxIndex];
                nums[maxIndex] = temp;
            }
        }
        return nums[k - 1];
    }

    /**
     * 排序
     */
    private int findKthLargest3(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 堆
     */
    private int findKthLargest4(int[] nums, int k) {
        // 小顶堆，k + 1 防止没必要的扩容
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);
        for (int n: nums) {
            heap.add(n);
            // 保证小顶堆中只有 k 个元素
            if (heap.size() > k) {
                // 去掉最小值
                heap.poll();
            }
        }
        // 直接返回堆顶元素
        return heap.poll();
    }

    /**
     * 三路快排
     */
    private int findKthLargest(int[] nums, int k) {
        return findKthLargestHelper(nums, 0, nums.length - 1, nums.length - k);
        // 在未排序的数组中找到排好序的第 k 个元素
//        return findKthLargestHelper(nums, 0, nums.length - 1, k - 1);
    }

    private int findKthLargestHelper(int[] nums, int left, int right, int k) {
        int randomIndex = (int) (Math.random() * (right - left + 1)) + left;
        int temp = nums[left];
        nums[left] = nums[randomIndex];
        nums[randomIndex] = temp;
        int pivot = nums[left], lt = left, gt = right + 1, i = left + 1;
        while (i < gt) {
            if (nums[i] < pivot) {
                temp = nums[i];
                nums[i] = nums[lt + 1];
                nums[lt + 1] = temp;
                i ++;
                lt ++;
            } else if (nums[i] > pivot) {
                temp = nums[i];
                nums[i] = nums[gt - 1];
                nums[gt - 1] = temp;
                gt --;
            } else {
                i ++;
            }
        }
        nums[left] = nums[lt];
        nums[lt] = pivot;
        if (k < lt) {
            return findKthLargestHelper(nums, left, lt - 1, k);
        } else if (k > gt - 1) {
            return findKthLargestHelper(nums, gt, right, k);
        } else {
            return pivot;
        }
    }
}
