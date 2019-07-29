package interview.linked;

import interview.common.Node;
import interview.recursion.LinkedListCreator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 递归反转链表
 */
public class LinkedListReverser {

    /**
     * reverses a linked list.
     * @param head the linked list to reverse
     * @return head of the reversed linked list
     */
    public Node reverseLinkedList(Node head){
        // size == 0 or size == 1
        if (head == null || head.getNext() == null){
            return head;
        }

        Node newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        LinkedListReverser reverser = new LinkedListReverser();

        Node.printLinkedList(reverser.reverseLinkedList(creator.createLinkedList(new ArrayList<>())));
        Node.printLinkedList(reverser.reverseLinkedList(creator.createLinkedList(Arrays.asList(1))));
        Node.printLinkedList(reverser.reverseLinkedList(creator.createLinkedList(Arrays.asList(1,2,3,4,5))));
    }

}
