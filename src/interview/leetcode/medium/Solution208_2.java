package interview.leetcode.medium;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie（前缀树）
 * 封装前
 * 数组实现
 */
public class Solution208_2 {

    /**
     * The root node.
     */
    private final Node root;

    /**
     * Initialize the data structure.
     */
    public Solution208_2() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Node();
            }
            cur = cur.next[index];
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
            int index = c - 'a';
            if (cur.next[index] == null) {
                return null;
            }
            cur = cur.next[index];
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
        public Node[] next;

        public Node(boolean isEndingChar) {
            this.isEndingChar = isEndingChar;
            next = new Node[26];
        }

        public Node() {
            this(false);
        }
    }
}
