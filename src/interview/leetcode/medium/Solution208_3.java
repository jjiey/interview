package interview.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie（前缀树）
 * 封装后
 * HashMap 实现（或 TreeMap）
 */
public class Solution208_3 {

    /**
     * The root node.
     */
    private final Node root;

    /**
     * Initialize the data structure.
     */
    public Solution208_3() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.notContainsKey(curLetter)) {
                node.put(curLetter, new Node());
            }
            node = node.get(curLetter);
        }
        node.setEndingChar();
    }

    /**
     * search a prefix or whole key in trie and returns the node where search ends
     */
    private Node searchPrefix(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.notContainsKey(curLetter)) {
                return null;
            }
            node = node.get(curLetter);
        }
        return node;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node != null && node.isEndingChar();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node node = searchPrefix(prefix);
        return node != null;
    }

    private static class Node {

        /**
         * 该节点是否是字符串的结尾
         */
        private boolean isEndingChar;

        /**
         * 指向后续节点的指针
         */
        private final Map<Character, Node> next;

        public Node(boolean isEndingChar) {
            this.isEndingChar = isEndingChar;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }

        public boolean containsKey(char c) {
            return next.containsKey(c);
        }

        public boolean notContainsKey(char c) {
            return !next.containsKey(c);
        }

        public Node get(char c) {
            return next.get(c);
        }

        public void put(char c, Node node) {
            next.put(c, node);
        }

        public void setEndingChar() {
            isEndingChar = true;
        }

        public boolean isEndingChar() {
            return isEndingChar;
        }
    }
}
