package interview.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary Tree Level Order Traversal
 * 二叉树的层次遍历
 */
public class Solution102 {

    public static void main(String[] args) {
        Solution102 lc = new Solution102();
//        List<List<Integer>> res = lc.levelOrder();
//        for (List<Integer> re : res) {
//            System.out.println(re.toString());
//        }
    }

    /**
     * BFS广度优先搜索
     * 一层一层填满
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // 当前层的总长度
            int levelSize = q.size();
            // 当前层的结果
            List<Integer> currLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = q.poll();
                currLevel.add(currNode.val);
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            res.add(currLevel);
        }
        return res;
    }

    /**
     * DFS深度优先搜索
     * 每一次遍历种, 每层遍历了几个元素就先放进去, 一直到遍历完
     */
    private List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        _dfs(res, root, 0);
        return res;
    }

    private void _dfs(List<List<Integer>> res, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        // res.size()层数
        if (res.size() < level + 1) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        _dfs(res, node.left, level + 1);
        _dfs(res, node.right, level + 1);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

}
