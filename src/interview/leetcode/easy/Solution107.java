package interview.leetcode.easy;

import interview.leetcode.common.TreeNode;

import java.util.*;

/**
 * Binary Tree Level Order Traversal II
 * 二叉树的层次遍历 II
 * my TODO
 */
public class Solution107 {

    public static void main(String[] args) {
        Solution107 lc = new Solution107();
//        List<List<Integer>> res = lc.levelOrderBottom();
//        System.out.println(res.toString());
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
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
        Collections.reverse(res);
        return res;
    }

}
