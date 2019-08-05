package interview.leetcode.medium;

import interview.leetcode.common.ListNode;

import static interview.leetcode.common.ListNodeUtil.createListNode;
import static interview.leetcode.common.ListNodeUtil.printListNode;

/**
 * Remove Nth Node From End of List
 * 删除链表的倒数第N个节点
 */
public class Solution19 {

    public static void main(String[] args) {
        Solution19 lc = new Solution19();
        ListNode head = createListNode(new int[]{1, 2, 3, 4, 5});
        int n = 2;
        ListNode res = lc.removeNthFromEnd2(head, n);
        printListNode(res);
    }

    /**
     * 两遍扫描
     */
    private ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int length = 0;
        ListNode curHead = head;
        while (curHead != null) {
            length ++;
            curHead = curHead.next;
        }
        curHead = dummyHead;
        for (int i = 0; i < length - n; i++) {
            curHead = curHead.next;
        }
        curHead.next = curHead.next.next;
        return dummyHead.next;
    }

    /**
     * 一遍扫描
     */
    private ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead;
        ListNode second = dummyHead;
        // 中间是相隔n个
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyHead.next;
    }

}
