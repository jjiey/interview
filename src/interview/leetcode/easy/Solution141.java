package interview.leetcode.easy;

import interview.leetcode.common.ListNode;

/**
 * Swap Nodes in Pairs
 * 环形链表
 */
public class Solution141 {

    public static void main(String[] args) {
        Solution141 lc = new Solution141();
        ListNode head = lc.createListNode();
        boolean res = lc.hasCycle(head);
        System.out.println(res);
    }

    /**
     * 思路：快慢指针
     *     快指针每次走两个节点，慢指针每次走一个节点
     * 其它思路：
     *     1.每遍历一个节点，就从头开始遍历一遍比较值
     *     2.每遍历一个节点，和HashSet中的值比较，如果值存在，有环，否则存入HashSet，继续遍历
     */
    private boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    private ListNode createListNode() {
        ListNode one = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(0);
        ListNode four = new ListNode(-4);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = two;
        return one;
    }

}
