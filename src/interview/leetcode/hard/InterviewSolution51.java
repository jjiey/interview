package interview.leetcode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 面试题51. 数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 */
public class InterviewSolution51 {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 5, 6, 4};
        InterviewSolution51 lc = new InterviewSolution51();
        System.out.println(lc.reversePairs2(nums));
        System.out.println(lc.reversePairs4(new int[]{1, 2, 3, 4, 5, 6, 9}));
    }

    /**
     * 暴力法
     */
    private int reversePairs(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    ret++;
                }
            }
        }
        return ret;
    }

    /**
     * 归并排序
     */
    private int reversePairs2(int[] nums) {
        int ret = 0, n = nums.length;
        if (n < 2) {
            return 0;
        }
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n - i; j += 2 * i) {
                int mid = j + i - 1;
                if (nums[mid] > nums[j + i]) {
//                    ret += reversePairsHelper2(nums, j, mid, Math.min(j + 2 * i - 1, n - 1));
                    ret += reversePairsHelper2(nums, j, mid, Math.min(mid + i, n - 1));
                }
            }
        }
        return ret;
    }

    private int reversePairsHelper(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, ret = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                ret += mid - i + 1;
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, left, temp.length);
        return ret;
    }

    private int reversePairsHelper2(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, ret = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                // 换一种思路考虑
                ret += j - mid - 1;
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            ret += right - mid;
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, left, temp.length);
        return ret;
    }

    /**
     * 官方题解
     */
    private int reversePairs3(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        int[] copy = new int[len];
        System.arraycopy(nums, 0, copy, 0, len);

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    /**
     * nums[left..right] 计算逆序对个数并且排序
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     * nums[left..mid] 有序，nums[mid + 1..right] 有序
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            // 如果左半部分元素已经全部处理完毕
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            }
            // 如果右半部分元素已经全部处理完毕
            else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            }
            // 左半部分所指元素 <= 右半部分所指元素
            else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            }
            // 左半部分所指元素 > 右半部分所指元素
            else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

    /**
     * 树状数组
     */
    public int reversePairs4(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        // 离散化：使得数字更紧凑，节约树状数组的空间
        // 1、使用二分搜索树是为了去重并排序
        Set<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }
        // 2、把排名存在哈希表里方便查询
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rankIndex = 1;
        for (Integer num : treeSet) {
            rankMap.put(num, rankIndex++);
        }
        // 在树状数组内部完成前缀和的计算
        // 规则是：从后向前，先给对应的排名 + 1，再查询前缀和
        FenwickTree fenwickTree = new FenwickTree(rankMap.size());
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            int rank = rankMap.get(nums[i]);
            fenwickTree.update(rank, 1);
            count += fenwickTree.query(rank - 1);
        }
        return count;
    }

    /**
     * 树状数组
     */
    private class FenwickTree {

        private int[] tree;

        private int len;

        public FenwickTree(int len) {
            this.tree = new int[len + 1];
            this.len = len;
        }

        /**
         * 单点更新：把数组 i 位置的值加上 v
         */
        public void update(int i, int v) {
            // 从下到上更新
            while (i <= this.len) {
                this.tree[i] += v;
                i += lowbit(i);
            }
        }

        /**
         * 区间查询：查询序列 [1 ⋯ i] 区间的区间和，即 i 位置的前缀和
         */
        public int query(int i) {
            // 从右到左查询
            int sum = 0;
            while (i > 0) {
                sum += this.tree[i];
                i -= lowbit(i);
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

    private void test() {
        for (int i = 1; i <= 20; i++) {
            if ((i & 1) == 0) {
                System.out.println(i + " 的二进制为 " + Integer.toBinaryString(i) + " 二进制中自右往左0的个数为 " + (int) (Math.log(lowbit(i)) / Math.log(2)) + " 所以lowbit(" + i + ")为 " + lowbit(i) + " lowbit(" + i + ")的二进制为 " + Integer.toBinaryString(lowbit(i)));
                System.out.println("管理区间为：[" + (i - lowbit(i) + 1) + ", " + i + "]");
            }
            System.out.print((i + lowbit(i)) + " , " + (i - lowbit(i)));
            System.out.println();
        }

        for (int i = 1; i <= 20; i++) {
            int n = i;
            while (n <= 20) {
                System.out.print(n + " , ");
                n += lowbit(n);
            }
            System.out.println();
        }
        System.out.println("====================");
        for (int i = 20; i >= 1; i--) {
            int n = i;
            while (n > 0) {
                System.out.print(n + " , ");
                n -= lowbit(n);
            }
            System.out.println();
        }
    }

    private static int lowbit(int x) {
        return x & (-x);
    }
}
