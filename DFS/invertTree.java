/*Invert a binary tree*/

// DFS: invert the left tree and right tree and swap the left child and right child
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}

// BFS
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            TreeNode temp = curr.right;
            curr.right = curr.left;
            curr.left = temp;
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
        return root;
    }
}