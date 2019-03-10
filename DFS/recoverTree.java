// Two elements of a binary search tree (BST) are swapped by mistake.

// Recover the tree without changing its structure.

// Inorder traverse and find the two nodes that are misplaced
// reverse the values of the misplaced nodes
class Solution {
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode firstNode = null, secondNode = null, prevNode = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (firstNode == null && prevNode != null && prevNode.val > root.val) firstNode = prevNode;
            if (firstNode != null && firstNode.val < root.val) {
                secondNode = prevNode;
                break;
            }
            prevNode = root;
            root = root.right;
        }
        // if second node not found then it is the last node
        if (secondNode == null) secondNode = prevNode;
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }
}

// recursive inorder traversal
class Solution {
    TreeNode first = null, second = null, prev = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        if (second == null) second = prev;
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (first == null && prev != null && prev.val > root.val) first = prev;
        if (first != null && second == null && first.val < root.val) {
            second = prev;
            return;
        }
        prev = root;
        inorder(root.right);
    }
}