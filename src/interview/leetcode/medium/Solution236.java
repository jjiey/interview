package interview.leetcode.medium;

import interview.leetcode.common.TreeNode;

/**
 * Lowest Common Ancestor of a Binary Tree
 * 二叉树的最近公共祖先
 * 输入的是 层序遍历结果
 */
public class Solution236 {

    public static void main(String[] args) {
        Solution236 lc = new Solution236();
    }

    /**
     递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
     表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
     1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
     2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
     3. 左右子树返回值均为null, p和q均不在树中, 返回null
     **/
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        // 在左子树里找p或q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 在右子树里找p或q
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

}
