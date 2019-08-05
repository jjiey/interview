package interview.tree;

import java.util.*;

/**
 * 层序遍历Z字形输出
 */
public class LevelOrderZ {

    public static void main(String[] args) {
        new LevelOrderZ().levelOrderZ(new TreeCreator().createSampleTree());
    }

    private void levelOrderZ(TreeNode root) {
        if(root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 二叉树的深度从1开始
        int depth = 0;
        while (!queue.isEmpty()) {
            depth ++;
            // 当前层的总长度
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
            // 如果是偶数, 反转list
            if ((depth & 1) == 0) {
                Collections.reverse(currLevel);
            }
            currLevel.forEach(System.out::print);
        }
    }

}
