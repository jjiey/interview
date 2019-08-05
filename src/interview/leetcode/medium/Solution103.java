package interview.leetcode.medium;

import interview.leetcode.common.TreeNode;

import java.util.*;

/**
 * Binary Tree Zigzag Level Order Traversal
 * 二叉树的锯齿形层次遍历
 * my TODO
 */
public class Solution103 {

    public static void main(String[] args) {
        Solution103 lc = new Solution103();
//        List<List<Integer>> res = lc.zigzagLevelOrder();
//        for (List<Integer> re : res) {
//            System.out.println(re.toString());
//        }
    }

    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        // 二叉树的深度从1开始
        int depth = 0;
        while (!q.isEmpty()) {
            depth ++;
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
            // 如果是偶数, 反转list
            if ((depth & 1) == 0) {
                Collections.reverse(currLevel);
            }
            res.add(currLevel);
        }
        return res;
    }

}
