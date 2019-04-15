package interview.my;

import interview.common.Node;
import interview.recursion.LinkedListCreator;

import java.util.Arrays;

public class Example {

    /**
     * 判断单链表是否存在环
     * 假设从链表头节点到入环点的距离是D，链表的环长是S。那么循环会进行S次（为什么是S次），可以简单理解为O（N）
     * 除了两个指针以外，没有使用任何额外存储空间，所以空间复杂度是O（1）
     * @param head
     * @return
     */
    public boolean isCyclicList(Node head) {
        Node slow = head, fast = head;

        //使用快慢指针，慢指针每次向前一步，快指针每次两步
        while(fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            //两指针相遇则有环
            if(fast == slow) return true;
        }

        return false;
    }

    /**
     * 找到有环链表的入口
     * @param head
     * @return
     */
    public Node findCyclicList(Node head) {
        Node slow = head, fast = head;
        boolean isLoop = false;

        //使用快慢指针，慢指针每次向前一步，快指针每次两步
        while(fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            //两指针相遇则有环
            if(fast == slow) {
                isLoop = true;
                break;
            }
        }

        //一个指针从链表头开始，一个从相遇点开始，每次一步，再次相遇的点即是入口节点
        if(isLoop){
            slow = head;
            while(fast != null && fast.getNext() != null){
                //两指针相遇的点即是入口节点
                if(slow == fast){
                    return slow;
                }

                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        Node head = creator.createLinkedList(Arrays.asList(1, 2, 3, 4));
        head.getNext().getNext().getNext().setNext(head.getNext().getNext());
        Example example = new Example();
        System.out.println(example.isCyclicList(head));
        System.out.println(example.findCyclicList(head).getValue());
    }

}
