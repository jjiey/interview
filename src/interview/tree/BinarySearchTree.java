package interview.tree;

public class BinarySearchTree {
	
	int data;
	BinarySearchTree left;
	BinarySearchTree right;
	
	public BinarySearchTree(int data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	public void insert(BinarySearchTree root, int data) { // 构建树
		if(root.data < data) { // 根结点小于 data 插入右子树
			if(root.right == null) {
				root.right = new BinarySearchTree(data);
			}else { // 表示子树不为空 ，那么还要继续往下找，要找到叶子结点才能插入
				insert(root.right, data);
			}
			//insert(root, data);
		}else {
			if(root.left == null) {
				root.left = new BinarySearchTree(data);
			}else {
				insert(root.left, data);
			}
		}
	}
	
	public BinarySearchTree find(BinarySearchTree root, int data) { // 查找（递归）
		if (root == null)
			return root;
		if (data < root.data)
			return find(root.left, data);
		else if (data > root.data)
			return find(root.right, data);
		else
			return root;
	}

	public BinarySearchTree findNR(BinarySearchTree root, int data) { // 查找（非递归）
		while (root != null) {
			if (data < root.data)
				root = root.left;
			else if (data > root.data)
				root = root.right;
			else
				return root;
		}
		return root;
	}

	public void pre() { // 前序遍历
			
	}

	public void post() { // 后序遍历
		
	}

	public void in(BinarySearchTree root) { //中序遍历
		if(root != null) {
			in(root.left);
			System.out.print(root.data + " ");
			in(root.right);
		}
	}

	public static void main(String[] args) {
		int data[] = {0,5,9,1,2,3,10};
		BinarySearchTree root = new BinarySearchTree(data[0]); // 第一个点作为跟结点
		for(int i = 1 ; i < data.length ; i++) {
			root.insert(root, data[i]);
		}
		System.out.println("中序遍历:");
		root.in(root);
	}
	
}
