package interview.leetcode.medium;

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
        int res = lc.findKthLargest(nums, k);
        System.out.println(res);
    }

    // 暴力
    // 堆
    // 快排

    private int findKthLargest(int[] nums, int k) {
//        if (k > nums.length) {
//            return -1;
//        }
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

//    private int findKthLargest(int[] nums, int k) {
//        quickSort(nums, 0, nums.length - 1, nums.length - k);
//        return 0;
//    }
//
//    private void quickSort(int[] data, int left, int right, int k) {
//        if (left >= right) {
//            return;
//        }
//        int pivotIndex = partition(data, left, right);
//        if (pivotIndex == k) {
//            System.out.println(data[pivotIndex]);
//        }
//        quickSort(data, left, pivotIndex - 1, k);
//        quickSort(data, pivotIndex + 1, right, k);
//    }
//
//    private int partition(int[] data, int left, int right) {
//        int ll = left;
//        int rr = right;
//        int pivot = data[left];
//        while (ll != rr) {
//            while (ll < rr && data[rr] > pivot) {
//                rr --;
//            }
//            while (ll < rr && data[ll] <= pivot) {
//                ll ++;
//            }
//            if (ll < rr) {
//                int temp = data[rr];
//                data[rr] = data[ll];
//                data[ll] = temp;
//            }
//        }
//        data[left] = data[ll];
//        data[ll] = pivot;
//        return ll;
//    }
}
