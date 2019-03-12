/*
Given the root node of a binary search tree (BST) and a value to be inserted into the tree, 
insert the value into the BST. Return the root node of the BST after the insertion. 
It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, 
as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
*/


// iterative solution
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode curr = root;
        while (1 > 0) {
            if (val > curr.val) {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                }
                else curr = curr.right;
            } else {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                }
                else curr = curr.left;
            }
        }
        return root;
    }
}

// recursive solution
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return helper(root, val);
    }
    private TreeNode helper(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) root.right = helper(root.right, val);
        if (val < root.val) root.left = helper(root.left, val);
        return root;
        
    }
}