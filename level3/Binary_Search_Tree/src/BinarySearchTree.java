public class BinarySearchTree {

    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private TreeNode insertNode(TreeNode current, int data) {

        if (current == null) {
            return new TreeNode(data);
        }

        if (data < current.data) {
            current.left = insertNode(current.left, data);
        }
        else if (data > current.data) {
            current.right = insertNode(current.right, data);
        }

        return current;
    }
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(TreeNode current, int data) {

        if (current == null) {
            return false;
        }

        if (current.data == data) {
            return true;
        }

        if (data < current.data) {
            return searchNode(current.left, data);
        }

        return searchNode(current.right, data);
    }
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private TreeNode deleteNode(TreeNode current, int data) {

        if (current == null) {
            return null;
        }

        if (data < current.data) {
            current.left = deleteNode(current.left, data);
        }
        else if (data > current.data) {
            current.right = deleteNode(current.right, data);
        }
        else {

            // Case 1: No children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: One child
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            // Case 3: Two children
            TreeNode successor = findMin(current.right);
            current.data = successor.data;
            current.right = deleteNode(current.right, successor.data);
        }

        return current;
    }

    private TreeNode findMin(TreeNode current) {
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }
    // Inorder Traversal
    public void inorder() {
        inorderTraversal(root);
    }

    private void inorderTraversal(TreeNode current) {
        if (current != null) {
            inorderTraversal(current.left);
            System.out.print(current.data + " ");
            inorderTraversal(current.right);
        }
    }

    // Preorder Traversal
    public void preorder() {
        preorderTraversal(root);
    }

    private void preorderTraversal(TreeNode current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preorderTraversal(current.left);
            preorderTraversal(current.right);
        }
    }

    // Postorder Traversal
    public void postorder() {
        postorderTraversal(root);
    }

    private void postorderTraversal(TreeNode current) {
        if (current != null) {
            postorderTraversal(current.left);
            postorderTraversal(current.right);
            System.out.print(current.data + " ");
        }
    }
}