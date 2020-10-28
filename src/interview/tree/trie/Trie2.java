package interview.tree.trie;

/**
 * 数组实现
 */
public class Trie2 {

    /**
     * 根节点
     */
    private Node root;

    /**
     * Trie 中存储的单词数量
     */
    private int size;

    public Trie2() {
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
            int index = c - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Node();
            }
            cur = cur.next[index];
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
            int index = c - 'a';
            if (cur.next[index] == null) {
                return null;
            }
            cur = cur.next[index];
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
        public Node[] next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new Node[26];
        }

        public Node() {
            this(false);
        }
    }
}
