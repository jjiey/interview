package interview.tree;

/**
 * 中序遍历
 */
public class InOrder {

    /**
     * 寻找中序遍历时的下一个节点
     * @param node
     * @return
     */
    public TreeNode next(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.getRight() != null) { // 如果右子树不为null
            // 返回右子树的第一个结点
            return first(node.getRight());
        } else {  // 如果右子树为null，往上走
            while (node.getParent() != null && node.getParent().getRight() == node) {
                node = node.getParent();
            }
            // 此时会有两种情况：node.getParent() == null || node is left child of its parent
            return node.getParent();
        }
    }

    /**
     * 求中序遍历的第一个结点
     * @param node
     * @return
     */
    private TreeNode first(TreeNode node) {
        if(node == null) {
            return null;
        }

        TreeNode curNode = node;
        while(curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }
        return curNode;
    }

    /**
     * 借助上边两个方法完成中序遍历的非递归实现
     * @param root
     */
    public void inOrderNR(TreeNode root) {
        for(TreeNode node = first(root); node != null; node = next(node)) {
            System.out.print(node.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeCreator creator = new TreeCreator();
        InOrder inOder = new InOrder();

        TreeNode sampleTree = creator.createSampleTree();
        inOder.inOrderNR(sampleTree);

        inOder.inOrderNR(creator.createTree("", ""));
        inOder.inOrderNR(creator.createTree("A", "A"));
        inOder.inOrderNR(creator.createTree("AB", "BA"));
        inOder.inOrderNR(creator.createTree("ABCD", "DCBA"));
        inOder.inOrderNR(creator.createTree("ABCD", "ABCD"));
    }

}
