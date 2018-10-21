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