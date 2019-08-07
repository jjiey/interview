package interview.leetcode.medium;

import interview.leetcode.common.ListNode;

import static interview.leetcode.common.ListNodeUtil.createListNode;
import static interview.leetcode.common.ListNodeUtil.printListNode;

/**
 * Swap Nodes in Pairs
 * 两两交换链表中的节点
 * 示例: 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution24 {

    public static void main(String[] args) {
        Solution24 lc = new Solution24();
        ListNode head = createListNode(new int[]{1, 2, 3, 4});
        ListNode res = lc.swapPairs(head);
        printListNode(res);
    }

    /**
     *  思路: 原链表: 1 -> 2 -> 3 -> 4; 加上dummyHead和res(res为返回用)后: 0 -> 1 -> 2 -> 3 -> 4
     *      第一次循环: res = 0; pre = 0; a = 1; b = 2
     *      首先: a.next = b.next; 开始交换: dummyHead.next = b; b.next = a; 交换完成后: dummyHead = a;进入下一次循环
     *      第一次循环之后结果: 0 -> 2 -> 1 -> xxx
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode res = dummyHead;
        while (dummyHead.next != null && dummyHead.next.next != null) {
            ListNode a = dummyHead.next;
            ListNode b = a.next;
            // important
            a.next = b.next;
            dummyHead.next = b;
            b.next = a;
            dummyHead = a;
        }
        return res.next;
    }

}
