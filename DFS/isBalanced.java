/*
	Given a binary tree, determine if it is height-balanced.
*/

// Top-down approach ~ O(NlgN) average
// compare each node from the top, recalculate the depth
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftLevel = level(root.left);
        int rightLevel = level(root.right);
        if (Math.abs(leftLevel - rightLevel) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int level(TreeNode root) {
        if (root == null) return 0;
        return Math.max(level(root.left), level(root.right)) + 1;
    }
}

// Bottom-up approach ~ O(N)
// pass value "-1" if it is not balanced along the way up
// save the unnecessary compares
class Solution {
	public boolean isBalanced(TreeNode root) {
		return depth(root) != -1;
	}
	private int depth(TreeNode root) {
		if (root == null) return 0;
		int leftDepth = depth(root.left);
		if (leftDepth == -1) return -1;
		int rightDepth = depth(root.right);
		if (rightDepth == -1) return -1;
		if (Math.abs(leftDepth - rightDepth) > 1) return -1;
		return Math.max(leftDepth, rightDepth) + 1;
	}
}