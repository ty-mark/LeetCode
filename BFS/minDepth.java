/*
    Given a binary tree, find its minimum depth.

    The minimum depth is the number of nodes along the shortest path 
    from the root node down to the nearest leaf node.

    Note: A leaf is a node with no children.

    Example:

    Given binary tree [3,9,20,null,null,15,7],

        3
       / \
      9  20
        /  \
       15   7
    return its minimum depth = 2.
*/

// Note the note above!!
// Use level order traversal
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(q.peek() != null) {
            int len = q.size();
            // the for loop is to retrieve all nodes in the same level
            for (int i = 0; i < len; i++) {
                root = q.poll();
                if (root.left == null && root.right == null) {
                    return depth;
                }
                if (root.left != null) {
                    q.add(root.left);
                }
                if (root.right != null) {
                    q.add(root.right);
                }
            }
            depth += 1;
        }
        return depth;
    }
}

// recursive dfs solution
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}