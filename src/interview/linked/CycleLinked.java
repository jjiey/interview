package interview.linked;

import interview.common.Node;

/**
 * 判断单链表是否有环
 * 时间复杂度O(N)
 * 没有使用任何额外存储空间，空间复杂度是O(1)
 */
public class CycleLinked {

    /**
     * isCycle
     * @param head 链表头节点
     * @return 是否有环
     * 思路：快慢指针
     * 其它思路：
     *     1.每遍历一个节点，就从头开始遍历一遍比较值
     *     2.每遍历一个节点，和HashSet中的值比较，如果值存在，有环，否则存入HashSet，继续遍历
     */
    private static boolean isCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext();
            if (fast.getNext() != null) {
                slow = slow.getNext();
                fast = fast.getNext();
                if (slow.getValue() == fast.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 求环长
     * @param head 链表头节点
     * @return 环长
     * 思路：
     *     当两个指针首次相遇后，让两个指针在相遇点继续循环前进，统计前进次数，当两个指针第二次相遇，前进次数 = 环长
     *     因为再次相遇时，fast比slow多走一圈，所以环长 = 每一次速度差 * 前进次数 = 1 * 前进次数 = 前进次数
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

    /**
     * 求入环点
     * @param head 链表头节点
     * @return 入环点
     * 思路：
     *     假设链表头节点到入环点距离为D，入环点到两个指针首次相遇点距离为s1，两个指针首次相遇点回到入环点距离为s2
     *     当两个指针首次相遇时：
     *         slow指针走的距离 = D + s1
     *         fast指针走的距离 = D + s1 + s2 + s1 = D + 2s1 + s2
     *         而fast指针走的距离还 = 2 * (slow指针走的距离) = 2 * (D + s1)
     *         由D + 2s1 + s2 = 2 * (D + s1)得D = s2，即链表头节点到入环点距离等于两个指针首次相遇点回到入环点距离
     *     把其中一个指针放回到头节点，另一个指针在首次相遇点，两个指针每次走一步，相遇点即为入环点
     */
    private static Node getCyclePoint(Node head) {
        Node slow = head;
        Node fast = head;
        boolean isLoop = false;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext();
            if (fast.getNext() != null){
                slow = slow.getNext();
                fast = fast.getNext();
                if (slow.getValue() == fast.getValue()) {
                    isLoop = true;
                    break;
                }
            }
        }
        // 如果有环
        if(isLoop){
            // slow指针回到头节点
            slow = head;
            while (true) {
                // 两指针相遇的点即是入环点
                if(slow.getValue() == fast.getValue()){
                    return slow;
                }
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return null;
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
        System.out.println(isCycle(node1));
        System.out.println(getLenCycle(node1));
        System.out.println(getCyclePoint(node1) != null ? getCyclePoint(node1).getValue() : null);
    }

}
