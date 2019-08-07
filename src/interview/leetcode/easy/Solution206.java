package interview.leetcode.easy;

import interview.leetcode.common.ListNode;

import static interview.leetcode.common.ListNodeUtil.createListNode;
import static interview.leetcode.common.ListNodeUtil.printListNode;

/**
 * Reverse Linked List
 * 反转链表
 */
public class Solution206 {

    public static void main(String[] args) {
        Solution206 lc = new Solution206();
        ListNode head = createListNode(new int[]{1, 2, 3, 4});
//        ListNode res = lc.reverseListRecursion(head);
        ListNode res = lc.reverseListNoRecursion(head);
        printListNode(res);
    }

    /**
     * 思路: 递归
     *     原链表: 1 -> 2 -> 3 -> 4
     *     第一次递归: 假设reverseList()能够正确反转2 -> 3 -> 4
     *     反转之后应该2 -> 1, 即: head.next.next = head;
     *     然后head -> null: head.next = null;
     */
    private ListNode reverseListRecursion(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 思路: 非递归, 循环, 三个指针
     *     原链表: 1 -> 2 -> 3 -> 4
     */
    private ListNode reverseListNoRecursion(ListNode head) {
        // 反转后的前驱结点
        ListNode newNode = null;
        ListNode curHead = head;
        while (curHead != null) {
            // 先记录一下原curHead.next
            ListNode next = curHead.next;
            // curHead的next指到它的前驱结点
            curHead.next = newNode;
            // newNode往前走一步
            newNode = curHead;
            // curHead往前走一步
            curHead = next;
        }
        return newNode;
    }

}
