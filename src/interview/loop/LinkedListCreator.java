package interview.loop;

import interview.common.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 循环创建链表
 */
public class LinkedListCreator {

    public Node createLargeLinkedList2(int size){
        Node prev = null;
        // head节点
        Node head = null;
        for (int i = 1; i <= size; i++) {
            Node node = new Node(i);
            if (prev != null) {
                prev.setNext(node);
            } else {
                head = node;
            }
            prev = node;
        }
        return head;
    }

    public Node createLargeLinkedList(int size){
        Node dummyHead = new Node(0);
        Node head = dummyHead;
        for (int i = 1; i <= size; i++) {
            head.setNext(new Node(i));
            head = head.getNext();
        }
        return dummyHead.getNext();
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();

        Node.printLinkedList(creator.createLargeLinkedList(0));
        Node.printLinkedList(creator.createLargeLinkedList(1));
        Node.printLinkedList(creator.createLargeLinkedList(5));
        Node.printLinkedList(creator.createLargeLinkedList(100));
        creator.createLargeLinkedList(1000000);
    }

}
