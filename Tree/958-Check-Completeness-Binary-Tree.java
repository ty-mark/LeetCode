/*
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
	Input: [1,2,3,4,5,6]
	Output: true
	Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
	Input: [1,2,3,4,5,null,7]
	Output: false
	Explanation: The node with value 7 isn't as far left as possible.
*/

// level order traversal
public boolean isCompleteTree(TreeNode root) {
    if (root == null) return true;
    Queue<TreeNode> q = new LinkedList();
    q.offer(root);
    while (q.peek() != null) { // finish loop when meeting first null
        TreeNode curr = q.poll();
        q.offer(curr.left);
        q.offer(curr.right);
    }
    while (!q.isEmpty() && q.peek() == null) { // all subsequent nodes should be null for completeness
        q.poll();
    }
    return q.isEmpty();
}


// level order traversal with early stop
public boolean isCompleteTree(TreeNode root) {
    if (root == null) return true;
    Queue<TreeNode> q = new LinkedList();
    q.offer(root);
    while (q.peek() != null) {
        TreeNode curr = q.poll();
        if (curr.left == null) {
            if (curr.right != null) return false;
            break;
        } else {
            q.offer(curr.left);
        }
        if (curr.right == null) break;
        q.offer(curr.right);
    }
    while (!q.isEmpty()) {
        TreeNode curr = q.poll();
        if (curr.left != null || curr.right != null) return false;
    }
    return true;
}