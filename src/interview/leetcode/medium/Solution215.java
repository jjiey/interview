package interview.leetcode.medium;

public class Solution215 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        Solution215 lc = new Solution215();
        int res = lc.findKthLargest(nums, k);
//        System.out.println(res);
    }

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1, nums.length - k);
        return 0;
    }

    private void quickSort(int[] data, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(data, left, right);
        if (pivotIndex == k) {
            System.out.println(data[pivotIndex]);
        }
        quickSort(data, left, pivotIndex - 1, k);
        quickSort(data, pivotIndex + 1, right, k);
    }

    private int partition(int[] data, int left, int right) {
        int ll = left;
        int rr = right;
        int pivot = data[left];
        while (ll != rr) {
            while (ll < rr && data[rr] > pivot) {
                rr --;
            }
            while (ll < rr && data[ll] <= pivot) {
                ll ++;
            }
            if (ll < rr) {
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
            }
        }
        data[left] = data[ll];
        data[ll] = pivot;
        return ll;
    }

}
