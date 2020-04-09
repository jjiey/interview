package interview.leetcode.easy;

import interview.leetcode.common.TreeNode;

public class Solution110 {

    public static void main(String[] args) {
        Solution110 lc = new Solution110();

        // 求二叉树的最大深度
//        Solution104 lc104 = new Solution104();
//        Math.abs(lc104.maxDepth(root.left) - lc104.maxDepth(root.right)) > 1;
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedTreeHelper(root) != -1;
    }

    /**
     * 检查root树是否是平衡的
     * @return 定义-1为特殊值，如果返回-1，则说明数不平衡；否则会返回树的最大高度
     */
    private int isBalancedTreeHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 检查左子树是否是平衡的
        int leftDepth = isBalancedTreeHelper(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        // 检查右子树是否是平衡的
        int rightDepth = isBalancedTreeHelper(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        return Math.abs(leftDepth - rightDepth) > 1 ? -1 : 1 + Math.max(leftDepth, rightDepth);
    }
}
