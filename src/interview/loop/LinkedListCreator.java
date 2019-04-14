package interview.loop;

import interview.common.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 循环创建链表
 */
public class LinkedListCreator {

    public Node createLargeLinkedList(int size){
        Node prev = null;
        Node head = null; //head节点
        for(int i = 1; i <= size; i ++){
            Node node = new Node(i);
            if(prev != null){
                prev.setNext(node);
            }else{
                head = node;
            }
            prev = node;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();

        Node.printLinkedList(creator.createLargeLinkedList(0));
        Node.printLinkedList(creator.createLargeLinkedList(1));
        Node.printLinkedList(creator.createLargeLinkedList(5));
        creator.createLargeLinkedList(1000000);
    }

}
