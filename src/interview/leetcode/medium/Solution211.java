package interview.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Design Add and Search Words Data Structure
 * 添加与搜索单词 - 数据结构设计
 */
public class Solution211 {

    private class WordDictionary {

        private final Node root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new Node();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.notContainsKey(c)) {
                    cur.put(c);
                }
                cur = cur.get(c);
            }
            cur.setEndingChar();
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return match(root, word, 0);
        }

        private boolean match(Node node, String word, int index) {
            if (index == word.length()) {
                return node.isEndingChar();
            }
            char c = word.charAt(index);
            if (c != '.') {
                if (node.notContainsKey(c)) {
                    return false;
                }
                return match(node.get(c), word, index + 1);
            } else {
                for (char nextChar : node.getNext().keySet()) {
                    if (match(node.get(nextChar), word, index + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }

        private class Node {

            private boolean isEndingChar;

            private final Map<Character, Node> next;

            private Node(boolean isEndingChar) {
                this.isEndingChar = isEndingChar;
                next = new HashMap<>();
            }

            public Node() {
                this(false);
            }

            public void setEndingChar() {
                isEndingChar = true;
            }

            public boolean isEndingChar() {
                return isEndingChar;
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
