package interview.leetcode.easy;

import interview.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Maximum Depth of Binary Tree
 * 二叉树的最大深度
 */
public class Solution104 {

    public static void main(String[] args) {
        Solution104 lc = new Solution104();
    }

    /**
     * DFS or 分治
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * BFS
     * 层序遍历层数即为二叉树的最大深度
     */
    public int maxDepth2 (TreeNode root) {
        if(root == null) {
            return 0;
        }
        int maxDepth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            maxDepth ++;
            // 当前层的总节点数
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = q.poll();
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
        return maxDepth;
    }

}
