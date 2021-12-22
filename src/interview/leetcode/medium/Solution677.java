package interview.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Map Sum Pairs
 */
public class Solution677 {

    private class MapSum {

        private final Node root;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (cur.notContainsKey(c)) {
                    cur.put(c);
                }
                cur = cur.get(c);
            }
            cur.setValue(val);
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.notContainsKey(c)) {
                    return 0;
                }
                cur = cur.get(c);
            }
            return sum(cur);
        }

        private int sum(Node node) {
//            if (node.getNext().size() == 0) {
//                return node.getValue();
//            }
            int res = node.getValue();
            for (char c : node.getNext().keySet()) {
                res += sum(node.get(c));
            }
            return res;
        }

        private class Node {

            private int value;

            private final Map<Character, Node> next;

            private Node(int value) {
                this.value = value;
                next = new HashMap<>();
            }

            public Node() {
                this(0);
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }

            public Map<Character, Node> getNext() {
                return next;
            }

            public boolean notContainsKey(char c) {
                return !this.containsKey(c);
            }

            public boolean containsKey(char c) {
                return next.containsKey(c);
            }

            public Node get(char c) {
                return next.get(c);
            }

            public void put(char c) {
                next.put(c, new Node());
            }
        }
    }
}
