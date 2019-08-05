package interview.leetcode.medium;

import interview.leetcode.common.ListNode;

/**
 * Swap Nodes in Pairs
 * 两两交换链表中的节点
 * 示例: 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution24 {

    public static void main(String[] args) {
        Solution24 lc = new Solution24();
        ListNode head = lc.createListNode();
        ListNode res = lc.swapPairs(head);
        lc.printListNode(res);
    }

    /**
     *  思路: 原链表: 1 -> 2 -> 3 -> 4; 加上pre和res(res为返回用)后: 0 -> 1 -> 2 -> 3 -> 4
     *      第一次循环: res = 0; pre = 0; a = 1; b = 2
     *      important: 第一次循环之后结果: 0 -> 2 -> 1 -> xxx
     *      所以首先: a.next = b.next; 开始交换: pre.next = b; b.next = a; 交换完成后: pre = a;进入下一次循环
     */
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode res = pre;
        while (pre.next != null && pre.next.next != null) {
            ListNode a = pre.next;
            ListNode b = a.next;
            // important
            a.next = b.next;
            pre.next = b;
            b.next = a;
            pre = a;
        }
        return res.next;
    }

    private ListNode createListNode() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.next = two;
        two.next = three;
        three.next = four;
        return one;
    }

    private void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
    }

}
