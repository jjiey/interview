package interview.tree;

public class TreeNode {
    private final char value;
    private TreeNode left;
    private TreeNode right;
    // 记录父节点，可以用来寻找中序遍历的下一个节点
    private TreeNode parent;

    public TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public char getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
        if(this.left != null) {
            this.left.setParent(this);
        }
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
        if(this.right != null) {
            this.right.setParent(this);
        }
    }

    public TreeNode getParent() {
        return parent;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
    } // 在TreeNode里的setLeft和setRight已经处理，设置为private
}
