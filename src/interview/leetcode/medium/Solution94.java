package interview.leetcode.medium;

import interview.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Binary Tree Inorder Traversal
 * 二叉树的中序遍历
 */
public class Solution94 {

    public static void main(String[] args) {
        Solution94 lc = new Solution94();
//        lc.inorderTraversal();
    }

    /**
     * 非递归
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 防止改变原root
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            // 左孩子都加进去
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    /**
     * 递归
     */
    private List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }

    private void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }

}
