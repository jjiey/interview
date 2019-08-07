package interview.leetcode.medium;

import interview.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Validate Binary Search Tree
 * 验证二叉搜索树
 * 思路: 1.中序遍历, 不能有重复元素, 且升序
 *     2.递归
 */
public class Solution98 {

    public static void main(String[] args) {
        Solution98 lc = new Solution98();
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        five.left = one;
        five.right = four;
        four.left = three;
        four.right = six;
//        boolean res = lc.isValidBST(five);
        boolean res = lc.isValidBST2(five);
        System.out.println(res);
    }

    /**
     * 中序遍历升序
     */
    private boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        for (int i = 0; i < res.size() - 1; i++) {
            // 有重复元素或不是升序, 返回false
            if (res.get(i) >= res.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root, List<Integer> res){
        if (root == null) {
            return;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    /**
     * 递归
     * 直到所有节点都满足: 右子树最小值 > 根节点, 左子树最大值 < 根节点
     */
    private boolean isValidBST2(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        // 检查右子树, 右子树取最小值
        if (min != null && root.val <= min) {
            return false;
        }
        // 检查左子树, 左子树取最大值
        if (max != null && root.val >= max) {
            return false;
        }
        // 左子树 && 右子树
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

}
