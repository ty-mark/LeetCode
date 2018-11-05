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

// BFS with two stacks, keep a leftToRight direction
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<Integer> row = new ArrayList<>();
        Stack<TreeNode> curr = new Stack<>();
        Stack<TreeNode> next = new Stack<>();
        boolean leftToRight = true;
        curr.push(root);
        while (!curr.empty()) {
            root = curr.pop();
            row.add(root.val);
            if (leftToRight) {
                if (root.left != null) next.push(root.left);
                if (root.right != null) next.push(root.right);
            }
            if (!leftToRight) {
                if (root.right != null) next.push(root.right);
                if (root.left != null) next.push(root.left);
            }
            if (curr.empty()) {
                leftToRight = !leftToRight;
                list.add(row);
                row = new ArrayList<>();
                curr = next;
                next = new Stack<>();
            }
        }
        return list;
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