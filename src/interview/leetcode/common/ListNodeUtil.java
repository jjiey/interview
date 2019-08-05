package interview.leetcode.common;

public class ListNodeUtil {

    public static ListNode createListNode(int[] num) {
        ListNode dummyHead = new ListNode(0);
        ListNode curHead = dummyHead;
        for (int i : num) {
            curHead.next = new ListNode(i);
            curHead = curHead.next;
        }
        return dummyHead.next;
    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
    }

}
