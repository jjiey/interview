package interview.leetcode.medium;

import interview.leetcode.common.ListNode;

/**
 * Linked List Cycle II
 * 环形链表 II
 * 求入环点
 */
public class Solution142 {

    public static void main(String[] args) {
        Solution142 lc = new Solution142();
        ListNode head = lc.createListNode();
        ListNode res = lc.detectCycle(head);
        System.out.println(res == null ? "null" : res.val);
    }

    /**
     * 思路：
     *     假设链表头节点到入环点距离为D，入环点到两个指针首次相遇点距离为s1，两个指针首次相遇点回到入环点距离为s2
     *     当两个指针首次相遇时：
     *         slow指针走的距离 = D + s1
     *         fast指针走的距离 = D + s1 + s2 + s1 = D + 2s1 + s2
     *         而fast指针走的距离还 = 2 * (slow指针走的距离) = 2 * (D + s1)
     *         由D + 2s1 + s2 = 2 * (D + s1)得D = s2，即链表头节点到入环点距离等于两个指针首次相遇点回到入环点距离
     *     把其中一个指针放回到头节点，另一个指针在首次相遇点，两个指针每次走一步，相遇点即为入环点
     */
    private ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean isLoop = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isLoop = true;
                break;
            }
        }
        // 如果有环
        if (isLoop) {
            // slow指针回到头节点
            slow = head;
            while (true) {
                // 两指针相遇的点即是入环点
                if(slow == fast){
                    return slow;
                }
                slow = slow.next;
                fast = fast.next;
            }
        }
        return null;
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
