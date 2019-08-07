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

    /*
         递归, 对以root为根的(子)树进行查找p和q, 得到两条路径, 最早重合的节点即为二叉树的最近公共祖先
         根据左右子树的返回值判断:
         1. 如果左右子树返回值都不为null, 由于左右子树的返回值就是p和q, 所以此时root为LCA
         2. 如果左右子树返回值只有一个不为null, 说明p和q存在于左子树或右子树中, 最先找到的那个节点为LCA
         3. 如果左右子树返回值都为null, 说明p和q均不在树中, 返回null
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果root == null || root == p || root == q直接返回root, 表示对于当前树的查找已经完毕
        if (root == null || root == p || root == q) {
            return root;
        }
        // 否则
        // 在左子树里找p或q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 在右子树里找p或q
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 这里不管right是否为null, 如果right是null, 说明left和right都是null, 返回null, 否则返回right
        if (left == null) {
            return right;
        }
        // 到这里, left不为null, 如果right是null, 返回left, 否则在下边返回root
        if (right == null) {
            return left;
        }
        // left和right都不为null, 返回root
        return root;
    }

}
