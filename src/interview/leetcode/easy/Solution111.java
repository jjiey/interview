package interview.leetcode.easy;

import interview.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Minimum Depth of Binary Tree
 * 二叉树的最小深度
 */
public class Solution111 {

    public static void main(String[] args) {
        Solution111 lc = new Solution111();
    }

    /**
     * DFS or 分治
     * 题目: 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
     */
    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果该节点只有右子树
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        // 如果该节点只有左子树
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        // 到这里, 说明该节点有左右子树, 进行分治
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * minDepth的简化写法, 但是性能会比minDepth差一点
     * 因为如果左子树深度为1，而右子树的深度为10000，那么这种方法需要把右子树10000的深度全部递归完，其实没有必要
     */
    private int minDepth_simple(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.right);
        int right = minDepth(root.left);
        return (root.left == null || root.right == null) ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * BFS
     * 第一个叶子节点的层数即为二叉树的最小深度
     */
    private int minDepth2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int minDepth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            minDepth ++;
            // 当前层的总长度
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = q.poll();
                // 如果是叶子节点, 直接返回此时的minDepth
                if (currNode.left == null && currNode.right == null) {
                    return minDepth;
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
        return minDepth;
    }

}
