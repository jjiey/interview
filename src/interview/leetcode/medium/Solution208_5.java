package interview.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie（前缀树）
 * 封装两种数据结构实现
 */
public class Solution208_5 {

    /**
     * The root node.
     */
    private final Node root;

    /**
     * Initialize the data structure.
     */
    public Solution208_5() {
        // 修改处
        root = new ArrayTrieNode();
//        root = new HashMapTrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.notContainsKey(curLetter)) {
                node.put(curLetter);
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

    /**
     * 抽象类，可作为具体数据结构实现的父类
     */
    private abstract static class Node {

        /**
         * 该节点是否是字符串的结尾
         */
        protected boolean isEndingChar;

        protected void setEndingChar() {
            isEndingChar = true;
        }

        protected boolean isEndingChar() {
            return isEndingChar;
        }

        protected boolean notContainsKey(char c) {
            return !this.containsKey(c);
        }

        /**
         * 当前节点是否包含指向下一个字符的指针
         */
        abstract boolean containsKey(char c);

        /**
         * 获取下一个节点
         */
        abstract Node get(char c);

        /**
         * 往当前节点之后插入一个节点
         */
        abstract void put(char c);
    }

    /**
     * 数组实现
     */
    private static class ArrayTrieNode extends Node {

        /**
         * 指向后续节点的指针
         */
        private final ArrayTrieNode[] next;

        private ArrayTrieNode(boolean isEndingChar) {
            this.isEndingChar = isEndingChar;
            next = new ArrayTrieNode[26];
        }

        public ArrayTrieNode() {
            this(false);
        }

        @Override
        public boolean containsKey(char c) {
            return next[c - 'a'] != null;
        }

        @Override
        public Node get(char c) {
            return next[c - 'a'];
        }

        @Override
        public void put(char c) {
            next[c - 'a'] = new ArrayTrieNode();
        }
    }

    /**
     * HashMap 实现
     */
    private static class HashMapTrieNode extends Node {

        /**
         * 指向后续节点的指针
         */
        private final Map<Character, HashMapTrieNode> next;

        private HashMapTrieNode(boolean isEndingChar) {
            this.isEndingChar = isEndingChar;
            next = new HashMap<>();
        }

        public HashMapTrieNode() {
            this(false);
        }

        @Override
        public boolean containsKey(char c) {
            return next.containsKey(c);
        }

        @Override
        public Node get(char c) {
            return next.get(c);
        }

        @Override
        public void put(char c) {
            next.put(c, new HashMapTrieNode());
        }
    }
}
