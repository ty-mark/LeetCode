/*
Given a binary tree, return the preorder traversal of its nodes' values.
Preorder traversal: root -> left -> right
*/

// only push right node to the stack
// not necessary to push left node to the stack
class Solution {
	private List<Integer> list = new ArrayList<>();
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null) return list;
		Stack<TreeNode> s = new Stack<>();
		while (root != null) {
			// add the current root value to the list (do something)
			list.add(root.val);
			// save the path to the right
			if (root.right != null) s.push(root.right);
			// go to the left
			root = root.left;
			// retrieve the path to the right if left path is dead
			if (root == null && !s.isEmpty()) root = s.pop();
		}
		return list;
	}
}

// save both left node and right node to the stack
// but clean
class Solution {
	private List<Integer> list = new ArrayList<>();
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null) return list;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		while (!s.isEmpty()) {
			TreeNode curr = s.pop();
			if (curr != null) {
				list.add(curr.val);
				s.push(curr.right);
				s.push(curr.left);
			}
		}
		return list;
	}
}

// save both to the stack
// not clean
class Solution {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return list;
        Stack<TreeNode> s = new Stack<>();
        while (!s.isEmpty() || root != null) {
            while (root != null) {
                list.add(root.val);
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            if (root.right != null) {
                root = root.right;
                continue;
            }
            root = null;
        }
        return list;
    }
}