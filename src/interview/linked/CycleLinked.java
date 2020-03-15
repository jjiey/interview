package interview.linked;

import interview.common.Node;

/**
 * 环形链表求环长
 * 判断链表是否有环，见 Solution141
 * 求入环点，见 Solution142
 */
public class CycleLinked {

    /**
     * 求环长
     * @param head 链表头节点
     * @return 环长
     * 思路：
     *     当两个指针首次相遇后，让两个指针在相遇点继续以原速度前进，此时开始统计前进次数，当两个指针第二次相遇时，此时 前进次数 = 环长
     *     因为再次相遇时，fast比slow多走一圈，所以环长 = 每一次速度差 * 前进次数 = （2 - 1） * 前进次数 = 前进次数
     */
    private static int getLenCycle(Node head) {
        Node slow = head;
        Node fast = head;
        // 相遇次数
        int meetCount = 0;
        // 前进次数（环长）
        int length = 0;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext();
            if (fast.getNext() != null){
                slow = slow.getNext();
                if (meetCount == 1) {
                    length ++;
                }
                fast = fast.getNext();
                if (slow.getValue() == fast.getValue()) {
                    meetCount ++;
                    if (meetCount == 2) {
                        return length;
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node2);
        System.out.println(getLenCycle(node1));
    }

}
