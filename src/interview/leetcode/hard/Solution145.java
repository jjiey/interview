package interview.leetcode.hard;

import interview.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Tree Postorder Traversal
 * 二叉树的后序遍历
 */
public class Solution145 {

    public static void main(String[] args) {
        Solution145 lc = new Solution145();
//        lc.postorderTraversal();
    }

    /**
     * 非递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // 双向链表
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                linkedList.add(node.left);
            }
            if (node.right != null) {
                linkedList.add(node.right);
            }
        }
        return output;
    }

    /**
     * 递归
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }

    private void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        helper(res, root.left);
        helper(res, root.right);
        res.add(root.val);
    }

}
