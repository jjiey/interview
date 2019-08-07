package interview.leetcode.medium;

/**
 * Implement Trie (Prefix Tree)
 * 实现 Trie (前缀树)
 * 使用数组实现
 */
public class Solution208_1 {

    public static void main(String[] args) {
        Trie trie = new Solution208_1().new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("app")); // false
        System.out.println(trie.startsWith("app")); // true
        trie.insert("app");
        System.out.println(trie.search("app")); // true
    }

    class Trie {

        class TrieNode {
            public char val;
            // 标识一个单词的结束
            public boolean isWord = false;
            // 该字符的所有子节点
            public TrieNode[] children = new TrieNode[26];

            public TrieNode() {
                this.val = ' ';
            }
            public TrieNode(char c) {
                this();
                this.val = c;
            }
        }

        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0 ; i < word.length(); i++){
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode(c);
                }
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode cur = root;
            for (int i = 0 ; i < word.length(); i++){
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                cur = cur.children[c - 'a'];
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0 ; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                cur = cur.children[c - 'a'];
            }
            return true;
        }
    }

}
