package interview.leetcode.medium;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie（前缀树）
 * 封装后
 * 数组实现
 */
public class Solution208_4 {

    /**
     * The root node.
     */
    private final Node root;

    /**
     * Initialize the data structure.
     */
    public Solution208_4() {
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
        private final Node[] next;

        public Node(boolean isEndingChar) {
            this.isEndingChar = isEndingChar;
            next = new Node[26];
        }

        public Node() {
            this(false);
        }

        public boolean containsKey(char c) {
            return next[c - 'a'] != null;
        }

        public boolean notContainsKey(char c) {
            return next[c - 'a'] == null;
        }

        public Node get(char c) {
            return next[c - 'a'];
        }

        public void put(char c, Node node) {
            next[c - 'a'] = node;
        }

        public void setEndingChar() {
            isEndingChar = true;
        }

        public boolean isEndingChar() {
            return isEndingChar;
        }
    }
}
