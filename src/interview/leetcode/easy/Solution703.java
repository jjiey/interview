package interview.leetcode.easy;

import java.util.PriorityQueue;

/**
 * Kth Largest Element in a Stream
 * 数据流中的第K大元素
 * 思路：优先队列
 */
public class Solution703 {

    public static void main(String[] args) {
        Solution703 lc = new Solution703();
        int k = 3;
        int[] arr = new int[]{4, 5, 8, 2};
        KthLargest kthLargest = lc.new KthLargest(k, arr);
        kthLargest.add(3);
        kthLargest.add(5);
        kthLargest.add(10);
        kthLargest.add(9);
        kthLargest.add(4);
    }

    class KthLargest {

        /**
         * PriorityQueue底层是严格的斐波那契堆，性能最好的堆
         * PriorityQueue默认是最小堆
         */
        final PriorityQueue<Integer> priorityQueue;
        final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            priorityQueue = new PriorityQueue<>();
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(val);
            } else if (priorityQueue.peek() < val) {
                priorityQueue.poll();
                priorityQueue.offer(val);
            }
            return priorityQueue.peek();
        }
    }

}
