/*
    Given a binary tree, return the level order traversal of its nodes' values. 
    (ie, from left to right, level by level).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
*/

// BFS with a queue
// keep two counters, curr -> number of nodes in current level, 
//                    next -> number of nodes (not null) in next level.
class Solution {
    private List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int curr = 1;
        while (q.size() != 0) {
            // can actually save the counter "next" by letting
            // <next = q.size()>
            int next = 0;
            List<Integer> row = new ArrayList<>();
            while (curr > 0) {
                root = q.poll();
                row.add(root.val);
                if (root.left != null) {
                    q.add(root.left);
                    next += 1;
                }
                if (root.right != null) {
                    q.add(root.right);
                    next += 1;
                }
                curr -= 1;
            }
            list.add(row);
            curr = next;
        }
        return list;
    }
}

// Bonus DFS recursive solution
// Preorder traversal
// Add a sublist to the list when the current level exceeds the number of sublists
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        helper(list, root, 0);
        return list;
    }
    private void helper(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level >= list.size()) list.add(new ArrayList<Integer>());
        list.get(level).add(root.val);
        helper(list, root.left, level + 1);
        helper(list, root.right, level + 1);
    }
}