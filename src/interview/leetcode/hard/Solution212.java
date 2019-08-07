package interview.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Word Search II
 * 单词搜索 II
 */
public class Solution212 {

    public static void main(String[] args) {
        Solution212 lc = new Solution212();
        char[][] board = {{'o', 'a', 'a', 'n'},
                          {'e', 't', 'a', 'e'},
                          {'i', 'h', 'k', 'r'},
                          {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        List<String> res = lc.findWords(board, words);
        System.out.println(res.toString());
    }

    // 在board上可能会有不同的线路出现相同的单词, 在set里去重
    private Set<String> res = new HashSet<>();
    /**
     * 四个方向, 遍历顺序为: 上右下左
     *        x-1,y
     * x,y-1  x,y    x,y+1
     *        x+1,y
     */
    private int[][] direction = {{-1, 0},
                                 {0, 1},
                                 {1, 0},
                                 {0, -1}};
    // board上有多少行
    private int rows;
    // board上有多少列
    private int cols;
    // 标识已经访问过
    private boolean[][] visited;

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                searchWord(board, i, j, "", trie);
            }
        }
        return new ArrayList<>(res);
    }

    private void searchWord(char[][] board, int startX, int startY, String s, Trie trie) {
        // 如果不在区域内 或 访问过, 直接返回
        if (!inArea(startX, startY) || visited[startX][startY]) {
            return;
        }
        s += board[startX][startY];
        // 如果不是trie前缀, 直接返回
        if (!trie.startsWith(s)) {
            return;
        }
        // 如果在trie里搜索到, 加入结果集
        // 为什么不返回?因为我理解错trie的search方法了,我以为要搜索到叶子节点
        if (trie.search(s)) {
            res.add(s);
        }
        visited[startX][startY] = true;
        for (int i = 0; i < direction.length; i++){
            int newX = startX + direction[i][0];
            int newY = startY + direction[i][1];
            // 如果不在区域内 或 访问过, 就不继续查找了, 多写一行代码少一层递归
            if (inArea(newX, newY) && !visited[newX][newY]) {
                searchWord(board, newX, newY, s, trie);
            }
        }
        visited[startX][startY] = false;
    }

    /**
     * 判断(x, y)坐标是否在board内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /* Solution208 - Trie */
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
