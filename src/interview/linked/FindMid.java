package interview.linked;

import interview.common.Node;
import interview.recursion.LinkedListCreator;

import java.util.Arrays;

/**
 * 链表指针不允许回头，求中点
 */
public class FindMid {

    /**
     * 链表指针不允许回头，求中点
     * @param head 链表头节点
     * @return 中点结点
     */
    private static Node findMid(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast.getNext() != null){
            if (fast.getNext().getNext() != null) {
                slow = slow.getNext();
                fast = fast.getNext().getNext();
            } else {
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        Node head = creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        Node.printLinkedList(head);
        Node mid = findMid(head);
        System.out.println(mid.getValue());
    }

}
