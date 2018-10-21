// Given a binary tree, determine if it is a valid binary search tree (BST).

// Assume a BST is defined as follows:

// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
// Example 1:

// Input:
//     2
//    / \
//   1   3
// Output: true
// Example 2:

//     5
//    / \
//   1   4
//      / \
//     3   6
// Output: false
// Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value is 5 but its right child's value is 4.

// Naive approach
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
//         1. check the left subtree
//         2. check the right subtree 
//         3. check if root > max(leftSubtree)
//         4. check if root < min(rightSubtree)
        if (isValidBST(root.left) && isValidBST(root.right)) {
            if ((root.left == null || root.val > leftMax(root.left)) && (root.right == null || root.val < rightMin(root.right))) return true;
            return false;
        }
        return false;
    }
    private int leftMax(TreeNode root) {
        if (root.right == null) return root.val;
        return leftMax(root.right);
    }
    private int rightMin(TreeNode root) {
        if (root.left == null) return root.val;
        return rightMin(root.left);
    }
    
}

// Better approach with a little math
// also need to note the use of Wrapper class "Integer"
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isChildBST(root, null, null);
    }
    private boolean isChildBST(TreeNode root, Integer low, Integer high) {
        if (root == null) return true;
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) return false;
        return isChildBST(root.left, low, root.val) && isChildBST(root.right, root.val, high);
    }
}

// Iterative inorder traversal
// Inorder traversal provides values in non-descending order
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
}