package interview.leetcode.easy;

import java.util.Stack;

/**
 * Implement Queue using Stacks
 * 用栈实现队列
 * 思路: 负负得正
 */
public class Solution232 {

    public static void main(String[] args) {
        Solution232 lc = new Solution232();
        MyQueue obj = lc.new MyQueue();
        obj.push(1);
        int param_2 = obj.pop();
        System.out.println(param_2);
        int param_3 = obj.peek();
        System.out.println(param_3);
        boolean param_4 = obj.empty();
        System.out.println(param_4);
    }

    class MyQueue {

        private Stack<Integer> input;
        private Stack<Integer> output;

        /** Initialize your data structure here. */
        public MyQueue() {
            input = new Stack<>();
            output = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            input.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        /**
         * 出队时如果output为空需要从input导入元素，否则直接出output
         */
        public int pop() {
            if (output.empty()) {
                if (input.empty()) {
                    return -1;
                }
                transfer();
            }
            return output.pop();
        }

        /**
         * input栈元素转移到output栈
         */
        private void transfer() {
            while (!input.empty()) {
                output.push(input.pop());
            }
        }

        /** Get the front element. */
        public int peek() {
            if (output.empty()) {
                if (input.empty()) {
                    return -1;
                }
                transfer();
            }
            return output.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return input.empty() && output.empty();
        }
    }

}
