package interview.tree;

import java.util.*;

/**
 * 遍历树
 */
public class TreeTraversal {

    /**
     * 前序遍历：根左右
     * @param root
     */
    public void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    /**
     * 前序遍历非递归实现
     * @param root
     */
    public void preOrderNR(TreeNode root){
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            System.out.print(cur.getValue());
            if(cur.getRight() != null)
                stack.push(cur.getRight());
            if(cur.getLeft() != null)
                stack.push(cur.getLeft());
        }
    }

    /**
     * 层序遍历（广度优先遍历）
     * 和非递归前序遍历逻辑一致，只是使用的数据结构不一致
     * @param root
     */
    public void levelOrder(TreeNode root){
        if(root == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode cur = q.remove();
            System.out.print(cur.getValue());
            if(cur.getLeft() != null)
                q.add(cur.getLeft());
            if(cur.getRight() != null)
                q.add(cur.getRight());
        }
    }

    /**
     * 中序遍历：左根右
     * @param root
     */
    public void inOrder(TreeNode root){
        if(root == null){
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getValue());
        inOrder(root.getRight());
    }

    /**
     * 后序遍历：左右根
     * @param root
     */
    public void postOrder(TreeNode root){
        if(root == null){
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }

    /**
     * 根据前序遍历和中序遍历直接输出后序遍历（不构造树）
     * @param preOrder
     * @param inOrder
     * @return
     */
    public String postOrder(String preOrder, String inOrder){
        if(preOrder.isEmpty()){
            return "";
        }

        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        // 左子树后序遍历的序列 + 右子树后序遍历的序列 + 根
        return postOrder(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex)) +
                postOrder(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex)) +
                rootValue;
    }

    public static void main(String[] args) {
        TreeCreator creator = new TreeCreator();
        TreeTraversal traversal = new TreeTraversal();
        TreeNode sampleTree = creator.createSampleTree();

        System.out.println("Sample tree traversal");
        traversal.preOrder(sampleTree);
        System.out.println();
        traversal.preOrderNR(sampleTree);
        System.out.println();
        traversal.inOrder(sampleTree);
        System.out.println();
        traversal.postOrder(sampleTree);
        System.out.println();
        traversal.levelOrder(sampleTree);
        //
        System.out.println();
        traversal.levelOrder2(sampleTree);
        System.out.println();

        System.out.println("==========");

        System.out.println("Creating tree from preOrder and inOrder");
        traversal.postOrder(creator.createTree("ABDEGCF", "DBGEACF"));
        System.out.println();
        traversal.postOrder(creator.createTree("", ""));
        System.out.println();
        traversal.postOrder(creator.createTree("A", "A"));
        System.out.println();
        traversal.postOrder(creator.createTree("AB", "BA"));
        System.out.println();

        System.out.println("==========");

        System.out.println("Generating postOrder directly");
        System.out.println(traversal.postOrder("ABDEGCF", "DBGEACF"));
        System.out.println(traversal.postOrder("", ""));
        System.out.println(traversal.postOrder("A", "A"));
        System.out.println(traversal.postOrder("AB", "BA"));
    }

    private List<List<Character>> levelOrder2(TreeNode root) {
        ArrayList<List<Character>> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int levelNum = 1;
        while(!queue.isEmpty()){
            int newLevelNum = 0;
            ArrayList<Character> level = new ArrayList<>();
            for(int i = 0; i < levelNum; i++){
                TreeNode node = queue.remove();
                level.add(node.getValue());

                if(node.getLeft() != null){
                    queue.add(node.getLeft());
                    newLevelNum ++;
                }
                if(node.getRight() != null){
                    queue.add(node.getRight());
                    newLevelNum ++;
                }
            }
            res.add(level);
            levelNum = newLevelNum;
        }
        System.out.println(res.toString());
        return res;
    }

}
