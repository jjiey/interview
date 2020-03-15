package interview.tree;

/**
 * 构造二叉树
 */
public class TreeCreator {

    /**
     * 人肉构造二叉树
     * @return
     *          A
     *       /   \
     *     B      C
     *   / \       \
     * D    E       F
     *    /
     *  G
     */
    public TreeNode createSampleTree() {
        TreeNode root = new TreeNode('A');
        root.setLeft(new TreeNode('B'));
        root.getLeft().setLeft(new TreeNode('D'));
        root.getLeft().setRight(new TreeNode('E'));
        root.getLeft().getRight().setLeft(new TreeNode('G'));
        root.setRight(new TreeNode('C'));
        root.getRight().setRight(new TreeNode('F'));
        return root;
    }

    /**
     * 根据前序中序构造二叉树（假定输入合法）
     * @param preOrder
     * @param inOrder
     * @return
     */
    public TreeNode createTree(String preOrder, String inOrder){
        if(preOrder.isEmpty()){
            return null;
        }

        char rootValue = preOrder.charAt(0); // 前序第一个元素为根
        int rootIndex = inOrder.indexOf(rootValue); // 左子树长度也为中序的rootIndex

        TreeNode root = new TreeNode(rootValue); // 构建根节点
        root.setLeft(createTree(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex))); // 构建左子树
        root.setRight(createTree(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex))); // 构建右子树

        return root;
    }

}
