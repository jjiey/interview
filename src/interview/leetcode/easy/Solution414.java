package interview.leetcode.easy;

/**
 * Third Maximum Number
 * 第三大的数
 *
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 */
public class Solution414 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        nums = new int[]{2, 2, 3, 1};
        Solution414 lc = new Solution414();
        int res = lc.thirdMax(nums);
        System.out.println(res);
    }

    // 获取第 3 大数，考虑重复元素（暂时不符合题意） todo
    private int thirdMax(int[] nums) {
        int len = nums.length;
        return thirdMaxHelper(nums, 0, len - 1, len >= 3 ? len - 3 : len - 1);
    }

    private int thirdMaxHelper(int[] nums, int left, int right, int k) {
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
            return thirdMaxHelper(nums, left, lt - 1, k);
        } else if (k > gt - 1) {
            return thirdMaxHelper(nums, gt, right, k);
        } else {
            return pivot;
        }
    }
}
