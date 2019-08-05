package interview.leetcode.easy;

import interview.leetcode.common.TreeNode;

/**
 * Lowest Common Ancestor of a Binary Search Tree
 * 二叉搜索树的最近公共祖先
 */
public class Solution235 {

    public static void main(String[] args) {
        Solution235 lc = new Solution235();
    }

    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            if(p.val < root.val && q.val < root.val) {
                // 如果两个值都小于根节点, 走左子树
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                // 如果两个值都大于根节点, 走右子树
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

}
