package interview.leetcode.easy;

import interview.leetcode.common.ListNode;

import static interview.leetcode.common.ListNodeUtil.createListNode;
import static interview.leetcode.common.ListNodeUtil.printListNode;

/**
 * Merge Two Sorted Lists
 * 合并两个有序链表
 */
public class Solution21 {

    public static void main(String[] args) {
        Solution21 lc = new Solution21();
        ListNode l1 = createListNode(new int[]{1, 2, 4});
        ListNode l2 = createListNode(new int[]{1, 3, 4});
        ListNode res = lc.mergeTwoLists(l1, l2);
        printListNode(res);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode res = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                dummyHead.next = l1;
                l1 = l1.next;
            } else {
                dummyHead.next = l2;
                l2 = l2.next;
            }
            dummyHead = dummyHead.next;
        }
        // 此时有一个链表为空, 再次检查
        dummyHead.next = l1 == null ? l2 : l1;
        return res.next;
    }

}
