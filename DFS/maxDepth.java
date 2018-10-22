// Given a binary tree, find its maximum depth.

// A recursive method with helper function
class Solution {
    public int maxDepth(TreeNode root) {
        return findDepth(root);
    }
    private int findDepth(TreeNode root) {
    	if (root == null) return 0;
    	else {
    		int leftDepth = findDepth(root.left);
    		int rightDepth = findDepth(root.right);
    		if (leftDepth > rightDepth) return 1 + leftDepth;
    		return 1 + rightDepth;
    	}
    }
}