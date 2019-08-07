package interview.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement Stack using Queues
 * 用队列实现栈
 * 思路: 负负得正
 */
public class Solution225 {

    public static void main(String[] args) {
        Solution225 lc = new Solution225();
        MyStack obj = lc.new MyStack();
        obj.push(1);
        obj.push(2);
        int param_3 = obj.top();
        System.out.println(param_3);
        int param_2 = obj.pop();
        System.out.println(param_2);
        boolean param_4 = obj.empty();
        System.out.println(param_4);
    }

    class MyStack {

        private Queue<Integer> input;
        private Queue<Integer> output;

        /** Initialize your data structure here. */
        public MyStack() {
            input = new LinkedList<>();
            output = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            input.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (empty()) {
                return -1;
            }
            // 如果input里只有一个元素, 直接出
            if (input.size() == 1) {
                return input.poll();
            } else {
                // 如果input里有多个元素, 把前input.size() - 1个元素倒腾到output里
                while (input.size() != 1) {
                    output.offer(input.poll());
                }
                // 从input里取出元素
                int temp = input.poll();
                // 再从output里倒腾回去到input
                while (!output.isEmpty()) {
                    input.offer(output.poll());
                }
                return temp;
            }
        }

        /** Get the top element. */
        public int top() {
            if (empty()) {
                return -1;
            }
            // 如果input里只有一个元素, 直接出
            if (input.size() == 1) {
                return input.peek();
            } else {
                // 如果input里有多个元素, 把前input.size() - 1个元素倒腾到output里
                while (input.size() != 1) {
                    output.offer(input.poll());
                }
                // 从input里取出元素
                int temp = input.poll();
                // 再把刚才取出的元素放进output里
                output.offer(temp);
                // 再从output里倒腾回去到input
                while (!output.isEmpty()) {
                    input.offer(output.poll());
                }
                return temp;
            }
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }
    }

}
