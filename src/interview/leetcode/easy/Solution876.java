package interview.leetcode.easy;

import interview.leetcode.common.ListNode;

import static interview.leetcode.common.ListNodeUtil.createListNode;

/**
 * Middle of the Linked List
 * 链表的中间结点
 */
public class Solution876 {

    public static void main(String[] args) {
        Solution876 lc = new Solution876();
        ListNode head = createListNode(new int[]{1, 2, 3, 4, 5});
        ListNode res = lc.middleNode(head);
        System.out.println(res == null ? "null" : res.val);
    }

    /**
     * 思路: 快慢指针
     */
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
