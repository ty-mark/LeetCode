/*
    Given a binary tree, return the zigzag level order traversal of its nodes' values.
    (ie, from left to right, then right to left for the next level 
    and alternate between).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]
*/

class Solution {
    /* level order traversal */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList();
        boolean leftToRight = true;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (leftToRight) level.add(curr.val);
                if (!leftToRight) level.add(0, curr.val);
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            leftToRight = !leftToRight;
            res.add(level);
        }
        return res;
    }
}

// DFS recursive with preorder traversal
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        helper(list, root, 0);
        return list;
    }
    private void helper(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level >= list.size()) list.add(new ArrayList<Integer>());
        if (level % 2 == 0) {
            list.get(level).add(root.val);
        } else {
            // if right->left, then always insert the value to the first position!!!
            list.get(level).add(0, root.val);
        }
        helper(list, root.left, level + 1);
        helper(list, root.right, level + 1);
    }
}