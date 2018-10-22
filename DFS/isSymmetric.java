/* 
	Given a binary tree, check whether it is a mirror of itself 
	(ie, symmetric around its center).
	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	But the following [1,2,2,null,3,null,3] is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
*/

// Recursive
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
    	if (root1 == null && root2 == null) return true;
    	else if ((root1 != null && root2 == null) || (root1 == null && root2 != null)) return false;
    	else {
    		if (root1.val == root2.val) return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    		return false;
    	}
    }
}

// Iterative
// Use a queue like BFS, enqueue the nodes in a symmetric way
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root1, root2;
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
        	root1 = queue.poll();
        	root2 = queue.poll();
        	if (root1 == null && root2 == null) continue;
        	else if ((root1 != null && root2 == null) || (root1 == null && root2 != null)) return false;
        	else {
        		if (root1.val == root2.val) {
        			// Must add the nodes symmetric
        			queue.add(root1.left);
        			queue.add(root2.right);
        			queue.add(root1.right);
        			queue.add(root2.left);
        		}
        		return false;
        	}
        }
        return true;
    }
}