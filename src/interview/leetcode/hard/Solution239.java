package interview.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Sliding Window Maximum
 * 滑动窗口最大值
 * 思路：最大堆, 每次返回堆顶元素
 * 优化：双端队列
 */
public class Solution239 {

    public static void main(String[] args) {
        Solution239 lc = new Solution239();
        int k = 3;
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = lc.maxSlidingWindow(nums, k);
        for (int i : res) {
            System.out.print(i);
        }
    }

    /**
     * 最大堆
     */
    private int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new int[0];
        }
        // 最大堆, 容量为k
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (i1, i2) -> i2-i1);
        // 存最后的结果, 结果个数为nums.length - k + 1
        int[] res = new int[nums.length - k + 1];
        int left;
        for (int i = 0; i < nums.length; i++) {
            // 删除队列中小于窗口左边下标的元素
            if(i >= k) {
                priorityQueue.remove(nums[i - k]);
            }
            // 队列加入新的元素
            priorityQueue.offer(nums[i]);
            // 队列最左侧索引
            left = i - k + 1;
            // 队列中的最大值加入结果
            if(left >= 0) {
                res[left] = priorityQueue.peek();
            }
        }
        return res;
    }

    /**
     * 双端队列
     */
    private int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new int[0];
        }
        // 存最后的结果, 结果个数为nums.length - k + 1
        int[] res = new int[nums.length - k + 1];
        // 窗口, 存下标, 双端队列
        Deque<Integer> window = new ArrayDeque<>();
        int left;
        for (int i = 0; i < nums.length; i++) {
            // 队列最左侧索引
            left = i - k + 1;
            // 删除队列中小于窗口左边下标的元素
            if(i >= k && left > window.peek()) {
                window.remove();
            }
            // 从队列右侧开始, 删除小于nums[i] 的元素
            while(!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
                window.removeLast();
            }
            // 窗口滑动加入新的索引元素
            window.add(i);
            // 队列左侧是最大值,加入结果
            if(left >= 0) {
                res[left] = nums[window.peek()];
            }
        }
        return res;
    }

}
