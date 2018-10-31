/*
	Given a non-empty binary tree, find the maximum path sum.

	For this problem, a path is defined as any sequence of nodes 
	from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

	Example 1:

	Input: [1,2,3]

	       1
	      / \
	     2   3

	Output: 6
	Example 2:

	Input: [-10,9,20,null,null,15,7]

	   -10
	   / \
	  9  20
	    /  \
	   15   7

	Output: 42
*/

// postorder traversal
// consider each node and its subtree, find the max sum at this node as:
// root.val + left + right
// if left or right sum is negative, replace it with 0
// and keep updating the current max
class Solution {
	// max stores the max path sum
	private int max = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		helper(root);
		return max;
	}
	private int helper(TreeNode root) {
		if (root == null) return 0;
		int left = Math.max(helper(root.left), 0);
		int right = Math.max(helper(root.right), 0);
		max = Math.max(max, root.val + left + right);
		return root.val + Math.max(left, right);
	}
}