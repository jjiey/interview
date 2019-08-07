package interview.loop;

import interview.common.Node;
import interview.recursion.LinkedListCreator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 链表循环删除指定值的节点
 */
public class LinkedListDeletor {

    public Node deleteIfEquals(Node head, int value) {
        // 注意这里是while, 连续处理头节点
        while (head != null && head.getValue() == value) {
            head = head.getNext();
        }
        if (head == null) {
            return head;
        }
        Node prev = head;
        // Loop invariant: list nodes from head up to prev has been processed. (Nodes with values equal to value are deleted.)
        while (prev.getNext() != null) {
            if (prev.getNext().getValue() == value) {
                // 删除
                prev.setNext(prev.getNext().getNext());
            } else {
                prev = prev.getNext();
            }
        }

        return head;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        LinkedListDeletor deletor = new LinkedListDeletor();

        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(1, 2, 3, 2, 5)), 2));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(1, 2, 3, 2, 2)), 2));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(1, 2, 3, 2, 2)), 1));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(2, 2, 3, 2, 2)), 2));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(2, 2, 2, 2, 2)), 2));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(2)), 2));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(Arrays.asList(2)), 1));
        Node.printLinkedList(deletor.deleteIfEquals(creator.createLinkedList(new ArrayList<Integer>()), 1));
    }

}
