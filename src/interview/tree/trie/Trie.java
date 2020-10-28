package interview.tree.trie;

import java.util.HashMap;

/**
 * HashMap 实现（或 TreeMap）
 */
public class Trie {

    /**
     * 根节点
     */
    private Node root;

    /**
     * Trie 中存储的单词数量
     */
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 向 Trie 中添加一个新的单词 word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

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
     * 查询单词 word 是否在 Trie 中
     */
    public boolean contains(String word) {
        Node node = searchPrefix(word);
        return node != null && node.isWord;
    }

    /**
     * 查询是否在 Trie 中有单词以 word 为前缀
     */
    public boolean isPrefix(String word) {
        Node node = searchPrefix(word);
        return node != null;
    }

    private class Node {

        /**
         * 该节点是否是单词的结尾
         */
        public boolean isWord;

        /**
         * 指向后续节点的指针
         */
        public HashMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
