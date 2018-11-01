/*
    Given a binary tree containing digits from 0-9 only, 
    each root-to-leaf path could represent a number.

    An example is the root-to-leaf path 1->2->3 
    which represents the number 123.

    Find the total sum of all root-to-leaf numbers.

    Note: A leaf is a node with no children.
*/

// postorder traversal
class Solution {
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        helper(root);
        return sum;
    }
    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int level = Math.max(left + right, 1);
        sum += level * root.val;
        return 10 * level;
    }
}