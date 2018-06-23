package interview.tree;

public class InOrder {

    public TreeNode next(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.getRight() != null) {
            return first(node.getRight());
        } else {
            while (node.getParent() != null && node.getParent().getRight() == node) {
                node = node.getParent();
            }
            // node.getParent() == null
            // || node is left child of its parent
            return node.getParent();
        }
    }

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

    public void traverse(TreeNode root) {
        for(TreeNode node = first(root); node !=null; node = next(node)) {
            System.out.print(node.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeCreator creator = new TreeCreator();
        InOrder inOder = new InOrder();

        TreeNode sampleTree = creator.createSampleTree();
        inOder.traverse(sampleTree);

        inOder.traverse(creator.createTree("", ""));
        inOder.traverse(creator.createTree("A", "A"));
        inOder.traverse(creator.createTree("AB", "BA"));
        inOder.traverse(creator.createTree("ABCD", "DCBA"));
        inOder.traverse(creator.createTree("ABCD", "ABCD"));
    }

}
