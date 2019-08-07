package interview.leetcode.easy;

import java.util.PriorityQueue;

/**
 * Kth Largest Element in a Stream
 * 数据流中的第K大元素
 * 思路：优先队列
 * 其他思路: sort
 */
public class Solution703 {

    public static void main(String[] args) {
        Solution703 lc = new Solution703();
        int k = 3;
        int[] arr = new int[]{4, 5, 8, 2};
        KthLargest kthLargest = lc.new KthLargest(k, arr);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
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
            // 队列始终维护k个元素
            priorityQueue = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            // 如果元素个数小于k, 直接入队
            if (priorityQueue.size() < k) {
                priorityQueue.offer(val);
            } else if (priorityQueue.peek() < val) {
                // 如果val比最顶上的元素大, 把最小的删掉把val放进去
                priorityQueue.poll();
                priorityQueue.offer(val);
            }
            return priorityQueue.peek();
        }
    }

}
