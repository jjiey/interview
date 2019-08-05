package interview.leetcode.medium;

import interview.leetcode.common.ListNode;

import static interview.leetcode.common.ListNodeUtil.createListNode;
import static interview.leetcode.common.ListNodeUtil.printListNode;

/**
 * Add Two Numbers
 * 两数相加
 */
public class Solution2 {

    public static void main(String[] args) {
        Solution2 lc = new Solution2();
        ListNode l1 = createListNode(new int[]{2, 4, 3});
        ListNode l2 = createListNode(new int[]{5, 6, 4});
        ListNode res = lc.addTwoNumbers(l1, l2);
        printListNode(res);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode curHead = dummyHead;
        // 计算的进位
        int carry = 0;
        while (p != null || q != null) {
            int pVal = p != null ? p.val : 0;
            int qVal = q != null ? q.val : 0;
            int sum = pVal + qVal + carry;
            carry = sum / 10;
            curHead.next = new ListNode(sum % 10);
            curHead = curHead.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curHead.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}
