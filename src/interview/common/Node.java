package interview.common;

/**
 * 单链表结点
 */
public class Node {

    // final避免用户创建完之后修改
    private final int value;

    private Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static void printLinkedList(Node head){
        while (head != null){
            System.out.print(head.getValue());
            System.out.print(" ");
            head = head.getNext();
        }
        System.out.println();
    }

}
