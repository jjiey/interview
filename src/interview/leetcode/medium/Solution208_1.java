package interview.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie（前缀树）
 * 封装前
 * HashMap 实现（或 TreeMap）
 */
public class Solution208_1 {

    /**
     * The root node.
     */
    private final Node root;

    /**
     * Initialize the data structure.
     */
    public Solution208_1() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isEndingChar = true;
    }

    /**
     * search a prefix or whole key in trie and returns the node where search ends
     */
    private Node searchPrefix(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                return null;
            }
            cur = cur.next.get(c);
        }
        return cur;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node != null && node.isEndingChar;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String word) {
        Node node = searchPrefix(word);
        return node != null;
    }

    private static class Node {

        /**
         * 该节点是否是字符串的结尾
         */
        public boolean isEndingChar;

        /**
         * 指向后续节点的指针
         */
        public Map<Character, Node> next;

        public Node(boolean isEndingChar) {
            this.isEndingChar = isEndingChar;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
