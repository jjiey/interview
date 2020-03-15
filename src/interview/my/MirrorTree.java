package interview.my;

import interview.tree.TreeCreator;
import interview.tree.TreeNode;

import java.util.*;

/**
 * 求一棵二叉树的镜像二叉树
 */
public class MirrorTree {

    public static void main(String[] args) {
        new MirrorTree().getMirrorTree(new TreeCreator().createSampleTree());
        System.out.println();
        new MirrorTree().mirror(new TreeCreator().createSampleTree());
    }

    private void getMirrorTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 当前层的总节点数
            int levelSize = queue.size();
            // 当前层的结果
            List<Character> currLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = queue.poll();
                currLevel.add(currNode.getValue());
                if (currNode.getLeft() != null) {
                    queue.add(currNode.getLeft());
                }
                if (currNode.getRight() != null) {
                    queue.add(currNode.getRight());
                }
            }
            // 反转list
            Collections.reverse(currLevel);
            currLevel.forEach(System.out::print);
        }
    }

    private void mirror(TreeNode root){
        if (root == null || (root.getLeft() == null && root.getRight() == null)) {
            return;
        }
        swapLeftAndRight(root);
        if (root.getLeft() != null) {
            swapLeftAndRight(root.getLeft());
        }
        if (root.getRight() != null) {
            swapLeftAndRight(root.getRight());
        }
    }

    private void swapLeftAndRight(TreeNode node){
        TreeNode temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);
    }
}
