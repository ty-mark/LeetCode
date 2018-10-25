/*
	Given a binary tree and a sum, find all root-to-leaf paths 
	where each path's sum equals the given sum.

	Note: A leaf is a node with no children.

	Example:

	Given the below binary tree and sum = 22,

	      5
	     / \
	    4   8
	   /   / \
	  11  13  4
	 /  \    / \
	7    2  5   1
	Return:

	[
	   [5,4,11,2],
	   [5,8,4,5]
	]
*/

// Iterative DFS
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
       	List<List<Integer>> list = new ArrayList<>();
       	if (root == null) return list;
       	Stack<TreeNode> stack = new Stack<>();
       	List<Integer> path = new ArrayList<>();
       	int pathSum = 0;
       	TreeNode prev = null;
       	while (root != null || !stack.empty()) {
       		while (root != null) {
       			stack.push(root);
       			path.add(root.val);
       			pathSum += root.val;
       			root = root.left;
       		}
       		// why peek() rather than pop()
       		// there may be a right subtree and not time to pop out the current node
       		root = stack.peek();

       		// check if there is a right subtree
       		// prev marks the node that has been visited 
       		if (root.right != null && root.right != prev) {
       			root = root.right;
       			// start a new iteration
       			continue;
       		}

       		// check the path sum from root to a leaf node
       		if (root.left == null && root.right == null && pathSum == sum) {
       			// use a copy of path, not "list.add(path)"
	       		list.add(new ArrayList<Integer>(path));
       		}
       		stack.pop();
       		pathSum -= root.val;
       		path.remove(path.size() - 1);
       		// prev keeps track of the least popped out node
       		prev = root;
       		// set root to null to check the next node on stack
       		root = null;
       	}
       	return list;
    }
}

// Recursive DFS
class Solution {
	private List<List<Integer>> list = new ArrayList<>();
	private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
       	pathSumHelper(root, sum);
       	return list;
    }
    private void pathSumHelper(TreeNode root, int sum) {
    	if (root == null) return;
    	sum -= root.val;
    	path.add(root.val);
    	if (root.left == null && root.right == null && sum == 0) {
    		list.add(new ArrayList<Integer>(path));
    	} else {
    		pathSumHelper(root.left, sum);
    		pathSumHelper(root.right, sum);
    	}
    	path.remove(path.size() - 1);
    	return;
    }
}
