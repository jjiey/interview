package interview.leetcode.medium;

import java.util.TreeMap;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie (前缀树)
 * 使用treeMap实现
 */
public class Solution208_2 {

    public static void main(String[] args) {
        Trie trie = new Solution208_2().new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("app")); // false
        System.out.println(trie.startsWith("app")); // true
        trie.insert("app");
        System.out.println(trie.search("app")); // true
    }

    class Trie {

        class Node {
            // 标识一个单词的结束
            public boolean isWord;
            // 该字符的所有子节点
            public TreeMap<Character, Node> children;

            public Node(boolean isWord) {
                this.isWord = isWord;
                children = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node cur = root;
            for (int i = 0 ; i < word.length(); i++){
                char c = word.charAt(i);
                if (cur.children.get(c) == null) {
                    cur.children.put(c, new Node());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node cur = root;
            for (int i = 0 ; i < word.length(); i++){
                char c = word.charAt(i);
                if (cur.children.get(c) == null) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0 ; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if (cur.children.get(c) == null) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return true;
        }
    }

}
