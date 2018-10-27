/*
	Given a binary tree, flatten it to a linked list in-place.

	For example, given the following tree:

	    1
	   / \
	  2   5
	 / \   \
	3   4   6
	The flattened tree should look like:

	1
	 \
	  2
	   \
	    3
	     \
	      4
	       \
	        5
	         \
	          6
*/

// recursive DFS: reverse postorder traversal (right --> left --> root)
class Solution {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        prev = root;
        root.left = null;
        return;
    }


// iterative DFS: use a stack, push right subtree to bottom
class Solution {
	public void flatten(TreeNode root) {
		if (root == null) return;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode curr = stack.pop();
			if (curr.right != null) stack.push(curr.right);
			if (curr.left != null) stack.push(curr.left);
			if (!stack.empty()) curr.right = stack.peek();
			curr.left = null;
		}
		return;
	}
}