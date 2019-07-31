package interview.leetcode.easy;

/**
 * Reverse Linked List
 * 反转链表
 */
public class Solution206 {

    public static void main(String[] args) {
        Solution206 lc = new Solution206();
        ListNode head = lc.createListNode(lc);
//        ListNode res = lc.reverseListRecursion(head);
        ListNode res = lc.reverseListNoRecursion(head);
        lc.printListNode(res);
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
     *     important: newNode是反转后被指向的节点, newNode和curHead同时往前走直到curHead为null
     */
    private ListNode reverseListNoRecursion(ListNode head){
        ListNode newNode = null;
        ListNode curHead = head;
        while (curHead != null) {
            ListNode next = curHead.next;
            // important
            curHead.next = newNode;
            newNode = curHead;
            curHead = next;
        }
        return newNode;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    private ListNode createListNode(Solution206 lc) {
        ListNode one = lc.new ListNode(1);
        ListNode two = lc.new ListNode(2);
        ListNode three = lc.new ListNode(3);
        ListNode four = lc.new ListNode(4);
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
